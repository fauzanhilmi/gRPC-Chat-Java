/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grpc.chat;

import io.grpc.ChannelImpl;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fauzanhilmi
 */
public class ChatterClient {
    private final ChannelImpl implChannel;
    private final ChatterGrpc.ChatterBlockingStub blockingStub;
    public static String messString = new String();

    public ChatterClient(String host, int port) {
      implChannel = NettyChannelBuilder.forAddress(host, port).negotiationType(NegotiationType.PLAINTEXT).build();
      blockingStub = ChatterGrpc.newBlockingStub(implChannel);
    }

    public void shutdown() throws InterruptedException {
      implChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    //Stub functions
    public String createNickname(String name) {
        GRPCChat.mName req = GRPCChat.mName.newBuilder().setName(name).build();
        GRPCChat.mString resp = blockingStub.createNickname(req);
        return resp.getValue();
    }

    public boolean joinChannel(String name, String channel) {
        GRPCChat.mNameChannel req = GRPCChat.mNameChannel.newBuilder().setName(name).setChannel(channel).build();
        GRPCChat.mBoolean resp = blockingStub.joinChannel(req);
        return resp.getValue();
    }

    public boolean leaveChannel(String name, String channel) {
        GRPCChat.mNameChannel req = GRPCChat.mNameChannel.newBuilder().setName(name).setChannel(channel).build();
        GRPCChat.mBoolean resp = blockingStub.leaveChannel(req);
        return resp.getValue();
    }

    public boolean sendMessage(String name, String channel, String message) {
        GRPCChat.mNameChannelMsg req = GRPCChat.mNameChannelMsg.newBuilder().setName(name).setChannel(channel).setMsg(message).build();
        GRPCChat.mBoolean resp = blockingStub.sendMessage(req);
        return resp.getValue();
    }

    public String getMessage(String name) {
        GRPCChat.mName req = GRPCChat.mName.newBuilder().setName(name).build();
        GRPCChat.mString resp = blockingStub.getMessage(req);
        return resp.getValue();
    }

    //main
    public static void main (String[] args) throws InterruptedException {
        final ChatterClient client = new ChatterClient("localhost",50051);
        final User u = new User();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                if (!u.isEmpty())
                {
                    messString =  client.getMessage(u.getName());
                    if (!messString.isEmpty()) {
                        System.out.print(messString);                                                     
                    }
                }
            }
        };
        timer.schedule(doAsynchronousTask, 0, 1000); //execute in every 100 ms
        
        while (!command.equals("/EXIT")) { 
            if (command.length() >= 5 && command.substring(0, 5).equals("/NICK")) {
                if (command.length() == 5) { //default username
                    u.setName(client.createNickname(""));
                    System.out.println("Successfully created nickname " + u.getName());
                } else if (command.length() >= 7 && command.charAt(5) == ' ') {
                    String name = client.createNickname(command.substring(6, command.length()));
                    if (!name.equals("")) {
                        u.setName(name);
                        System.out.println("Successfully created nickname " + u.getName());
                    } else {
                        System.out.println("Name was taken. Choose another name");
                    }

                }
            } else if (command.length() >= 5 && command.substring(0, 5).equals("/JOIN") && !u.isEmpty()) {
                if (command.length() == 5) { //default username
                    if (client.joinChannel(u.getName(), "channelname")) {
                        u.addChannel("channelname");
                        System.out.println("Successfully joined channelname");
                    }
                } else if (command.length() >= 7 && command.charAt(5) == ' ') {
                    if (client.joinChannel(u.getName(), command.substring(6, command.length()))) {
                        u.addChannel(command.substring(6, command.length()));
                        System.out.println("Successfully joined " + command.substring(6, command.length()));
                    } else {
                        System.out.println("Join channel failed");
                    }

                }
                else
                {
                    System.out.println("Wrong format");
                }
            } else if (command.length() >= 6 && command.substring(0, 6).equals("/LEAVE") && !u.isEmpty()) {
                if (command.length() >= 8 && command.charAt(6) == ' ') {
                    if (client.leaveChannel(u.getName(), command.substring(7, command.length()))) {
                        u.removeChannel(command.substring(7, command.length()));
                        System.out.println("Successfully left " + command.substring(7, command.length()));
                    } else {
                        System.out.println("Leave channel failed");
                    }

                }
            }
             else if (command.length() >= 4 && command.charAt(0) == ('@') && !u.isEmpty()) {
                if (command.contains(" "))
                {
                    String channelname = command.substring(1,command.indexOf(' '));
                    String message = command.substring(command.indexOf(' ')+1,command.length());
                    if (!client.sendMessage(u.getName(),channelname,message))
                    {
                        System.out.println("No channel found");
                    }
                }
                else
                {
                    System.out.println("You didn't type the message");
                }
            }
             else if (!u.isEmpty()) {
                 client.sendMessage(u.getName(),"broadcast",command);
            }

            command = sc.nextLine();
        }
        timer.cancel();
        timer.purge();
        client.shutdown();
    }
}
