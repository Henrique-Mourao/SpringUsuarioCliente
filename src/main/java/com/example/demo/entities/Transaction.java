package com.example.demo.entities;

public class Transaction {
    private int consecutiveErrors;
    private boolean isCreditCard;
    private String usualChannel;
    private String currentChannel;

    public Transaction(int consecutiveErrors, boolean isCreditCard, String usualChannel, String currentChannel) {
        this.consecutiveErrors = consecutiveErrors;
        this.isCreditCard = isCreditCard;
        this.usualChannel = usualChannel;
        this.currentChannel = currentChannel;
    }

    public int getConsecutiveErrors() { return consecutiveErrors; }
    public boolean isCreditCard() { return isCreditCard; }
    public String getUsualChannel() { return usualChannel; }
    public String getCurrentChannel() { return currentChannel; }
}
