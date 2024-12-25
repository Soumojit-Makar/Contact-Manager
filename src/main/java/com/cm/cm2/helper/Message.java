package com.cm.cm2.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Message {

    private String contant;
    private MessageType type = MessageType.blue;
    public Message(String contant, MessageType type) {
        this.contant = contant;
        this.type = type;
    }
    public String getContant() {
        return contant;
    }
    public void setContant(String contant) {
        this.contant = contant;

    }
    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }

}
