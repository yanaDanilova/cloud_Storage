package com.geekbrains;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileMessage extends AbstractMessage {

    private String filename;
    private byte[] file;

    public FileMessage( String filename) throws IOException {
        this.filename = filename;
        this.file = Files.readAllBytes(Paths.get(filename));
    }


    public String getFilename() {
        return filename;
    }

    public byte[] getFile() {
        return file;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
