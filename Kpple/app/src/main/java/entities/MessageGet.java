package entities;

import entities.Message;

public class MessageGet extends Message {
    private Long time;

    public MessageGet() {
    }

    public MessageGet(Long time) {
        this.time = time;
    }

    public MessageGet(String message, String urlPhoto, String name, String profilePhoto, String type_message, Long time) {
        super(message, urlPhoto, name, profilePhoto, type_message);
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
