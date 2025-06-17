/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yourcompany;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author RC_Student_lab
 */

public class MessageService {
    private final List<Message> sentMessages = new ArrayList<>();
    private final List<Message> storedMessages = new ArrayList<>();
    private final List<Message> disregardedMessages = new ArrayList<>();

    public void categorizeMessage(Message msg, String flag) {
        switch (flag.toLowerCase()) {
            case "send":
                sentMessages.add(msg);
                break;
            case "store":
                storedMessages.add(msg);
                break;
            case "disregard":
                disregardedMessages.add(msg);
                break;
            default:
                System.out.println("Invalid flag: " + flag);
                break;
        }
    }

    public void displaySentRecipients() {
        for (Message msg : sentMessages) {
            System.out.println("Sender ID: " + msg.getMessageID() + ", Recipient: " + msg.getRecipient());
        }
    }

    public void displayLongestSentMessage() {
        Message longest = sentMessages.stream()
                .max(Comparator.comparingInt(m -> m.getMessage().length()))
                .orElse(null);
        if (longest != null) {
            System.out.println("Longest Message: " + longest.getMessage());
        }
    }

    public void searchByMessageID(String id) {
        for (Message msg : sentMessages) {
            if (msg.getMessageID().equals(id)) {
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getMessage());
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    public void searchByRecipient(String recipient) {
        List<String> found = new ArrayList<>();
        for (Message msg : sentMessages) {
            if (msg.getRecipient().equals(recipient)) {
                found.add(msg.getMessage());
            }
        }
        for (Message msg : storedMessages) {
            if (msg.getRecipient().equals(recipient)) {
                found.add(msg.getMessage());
            }
        }
        System.out.println("Messages for recipient " + recipient + ":");
        found.forEach(System.out::println);
    }

    public void deleteByHash(String hash) {
        Iterator<Message> it = storedMessages.iterator();
        while (it.hasNext()) {
            Message msg = it.next();
            if (msg.getMessageHash().equals(hash)) {
                it.remove();
                System.out.println("Message '" + msg.getMessage() + "' successfully deleted.");
                return;
            }
        }
        System.out.println("Hash not found.");
    }

    public void displaySentReport() {
        System.out.println("Sent Message Report:");
        for (Message msg : sentMessages) {
            System.out.println("-------------------------");
            System.out.println("Message Hash: " + msg.getMessageHash());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getMessage());
        }
    }

    // Getters for unit tests
    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
}
