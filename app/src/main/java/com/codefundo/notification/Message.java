package com.codefundo.notification;

public class Message {
    CharSequence text;
    long timeStamp;
    CharSequence sender;

    public CharSequence getText() {
        return text;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public CharSequence getSender() {
        return sender;
    }

    public void setSender(CharSequence sender) {
        this.sender = sender;
    }

    public Message(CharSequence text,  CharSequence sender) {
        this.text = text;
        timeStamp = System.currentTimeMillis();
        this.sender = sender;
    }

    public Message() {
    }
}
