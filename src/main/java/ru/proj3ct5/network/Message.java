package ru.proj3ct5.network;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.proj3ct5.service.Configurator;

@Slf4j
public class Message {

    private final String MY_NAME = "TimeTracker";


    Configurator config = new Configurator();
    private int port = config.getPort();
    private String ip = config.getIp();

    @Getter
    protected String sender, receiver, data;
    @Getter
    String serializedMessage;

    public Message(String sender, String data, String receiver) {
        this.sender = sender;
        this.data = data;
        this.receiver = receiver;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    private void serialize() {
        String message = ":" + receiver + ":" + data + ":" + sender;
        int length = message.length() + 2;
        serializedMessage = length + message;
        log.debug("Message was serialized: {}", serializedMessage);
    }

    public boolean send() {
        serialize();
        boolean result = false;
        try {
            Publisher publisher = new Publisher();
            publisher.create(ip, port);
            publisher.send(serializedMessage);
            result = true;
            log.debug("Message sent successfully");
        } catch (RuntimeException e) {
            log.error("Error sending message: {}", e.getMessage());
        }
        return result;
    }

    public static Message deserialize(String serializedMessage) {
        if (serializedMessage == null || serializedMessage.isEmpty()) {
            log.error("Message is null or empty");
            throw new IllegalArgumentException("Message cannot be empty");
        }
        Message message;
        String[] split = serializedMessage.split(":");
        message = Message.builder().
                receiver(split[1])
                .sender(split[3])
                .data(split[2])
                .build();
        log.debug("Deserialized message: {}", message);
        return message;
    }

    public static boolean isMyMessage(String serializedMessage) {
        Message receivedMessage = deserialize(serializedMessage);
        return receivedMessage.receiver.equals(receivedMessage.MY_NAME);
    }

    public static void resendMessage(String serializedMessage) {
        Message sendingMessage = deserialize(serializedMessage);
        log.debug("Message will be resend: {}", sendingMessage);
        sendingMessage.send();
    }

    public static class MessageBuilder {

        Message message;

        public MessageBuilder() {
            message = new Message(null, null, null);
        }

        public Message build() {
            if (message.sender == null) {
                message.sender = message.MY_NAME;
            }
            if (message.receiver == null || message.data == null) {
                log.error("Message wasn't built because receiver and/or data is null");
                throw new IllegalArgumentException("Message cannot be empty");
            }
            return message;
        }

        public MessageBuilder receiver(String receiver) {
            message.receiver = receiver;
            return this;
        }

        public MessageBuilder sender(String sender) {
            message.sender = sender;
            return this;
        }

        public MessageBuilder data(String data) {
            message.data = data;
            return this;
        }


    }

}
