/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grpc.chat;

import io.grpc.Server;
import io.grpc.ServerImpl;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import java.util.logging.Logger;

/**
 *
 * @author fauzanhilmi
 */
public class ChatterServer {
    private static final Logger logger = Logger.getLogger(ChatterServer.class.getName());
    
     /* The port on which the server should run */
    private int port = 50051;
    private ServerImpl server;
    
    private void start() throws Exception {
        server = NettyServerBuilder.forPort(port)
//                .addService(ChatterGrpc.bindService()) JANGAN LUPA!!
                .build().start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            ChatterServer.this.stop();
            System.err.println("*** server shut down");
            }
        });
    }
     
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
    
    /**
    * Await termination on the main thread since the grpc library uses daemon threads.
    */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    
    private class ChatterImpl implements ChatterGrpc.Chatter {
        public List<String> defaultUsernames;
        public List<String> activeUsers;
        public List<Channel> activeChannels;
        public List<User> broadcastChannels;
        public User currentUser;
        
        public ChatterImpl() {
            defaultUsernames = new ArrayList<>(
                Arrays.asList("Kucing", "Sapi", "Rusa", "Kambing", "Platipus")
            );
            activeUsers = new ArrayList<>();
            activeChannels = new ArrayList<Channel>();
            currentUser = new User();
            broadcastChannels = new ArrayList<User>();
        }
        
        @Override
        public void createNickname(GRPCChat.mName request, StreamObserver<GRPCChat.mString> responseObserver) {
            String name = request.getName();
            String finalname = "";
            System.out.println("calling nick from " + name);
            String finalName = "";
            if (name.equals("")) { //kasus random username, diasumsikan masih ada nama yang tersedia
                int rndIdx = new Random().nextInt((defaultUsernames.size() - 0));
                String potentialName = defaultUsernames.get(rndIdx);
                while (isUsernameExists(potentialName)) {
                    rndIdx = new Random().nextInt((defaultUsernames.size() - 0));
                    potentialName = defaultUsernames.get(rndIdx);
                }
                defaultUsernames.remove(rndIdx);
                finalName = potentialName;
            } else {
                if (isUsernameExists(name)) {
                    GRPCChat.mString fn = GRPCChat.mString.newBuilder().setValue(finalName).build();
                    responseObserver.onValue(fn);
                    responseObserver.onCompleted();
                    return;
                } else {
                    finalName = name;
                }
            }
            activeUsers.add(finalName);
            currentUser.setName(finalName);
            broadcastChannels.add(new User(finalName));
            GRPCChat.mString fn = GRPCChat.mString.newBuilder().setValue(finalName).build();
            responseObserver.onValue(fn);
            responseObserver.onCompleted();
        }

        @Override
            public void joinChannel(GRPCChat.mNameChannel request, StreamObserver<GRPCChat.mBoolean> responseObserver) {
            if (request.getName().isEmpty() || request.getChannel().isEmpty()) {
                GRPCChat.mBoolean resp = GRPCChat.mBoolean.newBuilder().setValue(false).build();
                responseObserver.onValue(resp);
                responseObserver.onCompleted();
                return;
            }
            String name = request.getName();
            String channel = request.getChannel();
            System.out.println("calling join from " + name);
            int i = 0;
            while (i < activeChannels.size()) {
                if (activeChannels.get(i).getName().compareToIgnoreCase(channel) == 0) {
                    System.out.println(name + " successfully joined " + channel);
                    activeChannels.get(i).addActiveUser(name);
                    GRPCChat.mBoolean resp = GRPCChat.mBoolean.newBuilder().setValue(true).build();
                    responseObserver.onValue(resp);
                    responseObserver.onCompleted();
                    return;
                } else {
                    i++;
                }
            }
            Channel c = new Channel(channel);
            c.addActiveUser(name);
            activeChannels.add(c);
            currentUser.addChannel(channel);
            System.out.println(name + " successfully joined " + channel);
            GRPCChat.mBoolean resp = GRPCChat.mBoolean.newBuilder().setValue(true).build();
            responseObserver.onValue(resp);
            responseObserver.onCompleted();
        }

        @Override
        public void leaveChannel(GRPCChat.mNameChannel request, StreamObserver<GRPCChat.mBoolean> responseObserver) {
            String name = request.getName();
            String channel = request.getChannel();
            if (name.isEmpty() || channel.isEmpty()) {
                GRPCChat.mBoolean resp = GRPCChat.mBoolean.newBuilder().setValue(false).build();
                responseObserver.onValue(resp);
                responseObserver.onCompleted();
                return;
            }
            System.out.println("calling leave from " + name);
            int i = 0;
            while (i < activeChannels.size()) {
                if (activeChannels.get(i).getName().compareToIgnoreCase(channel) == 0) {
                    activeChannels.get(i).removeActiveUser(name);
                    currentUser.removeChannel(channel);
                    System.out.println(name + " successfully left " + channel);
                    GRPCChat.mBoolean resp = GRPCChat.mBoolean.newBuilder().setValue(true).build();
                    responseObserver.onValue(resp);
                    responseObserver.onCompleted();
                    return;
                } else {
                    i++;
                }
            }
            System.out.println("Channel not found");
            GRPCChat.mBoolean resp = GRPCChat.mBoolean.newBuilder().setValue(false).build();
            responseObserver.onValue(resp);
            responseObserver.onCompleted();
        }

        @Override
        public void sendMessage(GRPCChat.mNameChannelMsg request, StreamObserver<GRPCChat.mBoolean> responseObserver) {
            String name = request.getName();
            String channel = request.getChannel();
            String message = request.getMsg();
            String mesString = new String("[" + channel + "] " + "(" + name + ") " + message);
            System.out.println(name + " sending " + message + " to " + channel);
            int i = 0;
            boolean foundChannel = false;
            System.out.println("channel msg "+channel);
            if (!channel.equals("broadcast")) {
                while (i < activeChannels.size()) {
                    if (activeChannels.get(i).getName().equals(channel)) {
                        foundChannel = true;
                        for (int j = 0; j < activeChannels.get(i).activeUser.size(); j++) {
                            if (!activeChannels.get(i).activeUser.get(j).getName().equals(name)) {
                                activeChannels.get(i).activeUser.get(j).addMessage(mesString);
                            }
                        }
                    }
                    i++;
                }
                GRPCChat.mBoolean resp = GRPCChat.mBoolean.newBuilder().setValue(foundChannel).build();
                responseObserver.onValue(resp);
                responseObserver.onCompleted();
            } else {
                for (int j = 0; j < broadcastChannels.size(); j++) {
                    if (!broadcastChannels.get(j).getName().equals(name)) {
                        System.out.println("broadcast msg "+mesString);
                        broadcastChannels.get(j).addMessage(mesString);
                    }
                }
                GRPCChat.mBoolean resp = GRPCChat.mBoolean.newBuilder().setValue(true).build();
                responseObserver.onValue(resp);
                responseObserver.onCompleted();
            }
        }

        @Override
        public void getMessage(GRPCChat.mName request, StreamObserver<GRPCChat.mString> responseObserver) {
            String name = request.getName();
            StringBuilder msgBuilder = new StringBuilder();
            for (int i = 0; i < activeChannels.size(); i++) {
                for (int j = 0; j < activeChannels.get(i).activeUser.size(); j++) {
                    if (activeChannels.get(i).activeUser.get(j).getName().equals(name)) {
                        if (!activeChannels.get(i).activeUser.get(j).getMessQueue().isEmpty()) {
                            msgBuilder.append(activeChannels.get(i).activeUser.get(j).getAllMessage());
                        }
                    }
                }
            }
            for (int j = 0; j < broadcastChannels.size(); j++) {
                if (broadcastChannels.get(j).getName().equals(name)) {
    //                    System.out.println("broad = "+broadcastChannels.get(j).getAllMessage());
                    if (!broadcastChannels.get(j).getMessQueue().isEmpty()) {
    //                    System.out.println("broad = "+broadcastChannels.get(j).getAllMessage());
                        msgBuilder.append(broadcastChannels.get(j).getAllMessage());
                    }
                }
            }
            GRPCChat.mString resp = GRPCChat.mString.newBuilder().setValue(msgBuilder.toString()).build();
        }
        
        //Utility functions
        private boolean isUsernameExists(String name) {
            for (int i = 0; i < activeUsers.size(); i++) {
                if (name.equals(activeUsers.get(i))) {
                    return true;
                }
            }
            return false;
        }
    }
}
