package com.geekbrains;

public class RequestMessage extends AbstractMessage {

    private String username;
    private String filename;

    public RequestMessage(String username, String filename) {
        this.username = username;
        this.filename = filename;
    }

    public String getUsername() {
        return username;
    }

    public String getFilename() {
        return filename;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
