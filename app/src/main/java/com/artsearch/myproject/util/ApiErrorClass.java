package com.artsearch.myproject.util;

/**
 * Created by kmema on 9/9/2017.
 */

public class ApiErrorClass {
    private int statusCode;
    private String endPoint;
    private String messageError = "Unknown error.";

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
