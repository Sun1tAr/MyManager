package ru.proj3ct5.network;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;

@Slf4j
public class Publisher {

    private DatagramSocket socket;
    private int port;
    private InetAddress ip;

    public void create(String ip, int port) {
        this.port = port;
        try {
            this.ip = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            log.error("Cannot create IP address: {}", e.getMessage());
        }

        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            log.error("Cannot create socket: {}", e.getMessage());
        }
        log.debug("Publisher created");
    }

    public void send(String data) {
        DatagramPacket datagramPacket = new DatagramPacket(data.getBytes(), data.getBytes().length, ip, port);
        try {
            socket.send(datagramPacket);
        } catch (IOException e) {
            log.error("Cannot send data: {}", e.getMessage());
        }
    }

}
