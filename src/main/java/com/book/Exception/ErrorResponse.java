package com.book.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final LocalDateTime timeStamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    public ErrorResponse(int status, String error, String message, String path) {
    
    this.timeStamp = LocalDateTime.now();
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
    }

    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    public int getStatus() {
        return this.status;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }

}
