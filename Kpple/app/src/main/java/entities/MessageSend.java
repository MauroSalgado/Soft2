package entities;

import java.util.Map;

import entities.Message;

public class MessageSend extends Message {
    private Map time;

    public MessageSend() {
    }

    public MessageSend(String message, String name, String profilePhoto, String type_message, Map time) {
        super(message, name, profilePhoto, type_message);
        this.time = time;
    }

    public MessageSend(String message, String urlPhoto, String name, String profilePhoto, String type_message, Map time) {
        super(message, urlPhoto, name, profilePhoto, type_message);
        this.time = time;
    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }
}
