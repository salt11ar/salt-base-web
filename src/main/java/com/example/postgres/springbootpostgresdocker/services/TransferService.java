package com.example.postgres.springbootpostgresdocker.services;

public interface TransferService {

    public boolean transfer( String fromEmail, String toEmail, long cents);
}
