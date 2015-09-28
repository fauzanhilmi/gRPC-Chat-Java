#gRPC-Chat-Java
A simple chat application using gRPC with Java
Aplikasi chat sederhana yang dibangun memakai gRPC dengan Java

###Authors
Fauzan Hilmi Ramadhian - 13512003 & 
Tegar Aji Pangestu - 13512061

###Requirements
```java 1.8```

###Cara melakukan instalasi
Program telah dibuat sedemikian hingga tidak perlu melakukan instalasi

###Cara melakukan test

 1. Pada terminal, cd ke ```dist/```
 2.  Jalankan server dengan menjalankan file ```Chat.jar``` dengan :
```java
java -jar Chat.jar
```
 3.  Jalankan beberapa client dengan menjalankan file ```Chat.jar``` dengan :
```java
java -jar Chat.jar
``` 
untuk tiap client
 4. Ketik ```/NICK <username>``` untuk mendaftarkan username. Jika username kosong maka akan dibangkitkan secara otomatis
 5. Ketik ```/JOIN <channelname>``` untuk mendaftarkan diri ke sebuah channel. Jika nama channel kosong maka akan terdaftar otomatis ke  ```channelname```
 6. Kirimkan chat dengan mengetik ```@<channelname> <chatmessage>``` untuk mengetik pesan
 7. Voila!

 
