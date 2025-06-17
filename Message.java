package com.yourcompany;

import java.util.UUID;

public class Message {
    private String messageId;
    private int messageCount;
    private String recipient;
    private String message;
    private String messageHash;

    // Constructor
    public Message(int messageCount, String recipient, String message) {
        this.messageId = generateMessageID();
        this.messageCount = messageCount;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    // Generates a unique ID
    private String generateMessageID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    // Message hash generation
    public String createMessageHash() {
        String first2 = messageId.substring(0, 2).toUpperCase();
        String firstWord = message.split(" ")[0].toUpperCase();
        String lastWord = message.substring(message.lastIndexOf(" ") + 1).toUpperCase();
        return first2 + ":" + messageCount + ":" + firstWord + lastWord;
    }

    // Getter methods
    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageID() {
        return messageId;
    }

    public String getMessageHash() {
        return messageHash;
    }

    // Validator methods
    public boolean checkMessageID() {
        return messageId.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipient.matches("^(\\+27)(6|7|8)[0-9]{8}$") && recipient.length() <= 12;
    }

    // Print method
    public void printMessage() {
        System.out.println("Message ID: " + messageId);
        System.out.println("Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
    }
}
