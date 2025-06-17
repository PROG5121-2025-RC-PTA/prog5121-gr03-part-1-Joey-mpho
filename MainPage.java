package com.yourcompany;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MainPage {
    private static int messageCount = 1;
    private static final List<Message> messages = new ArrayList<>();
    private static final Scanner scan = new Scanner(System.in);
    private static final MessageService service = new MessageService();

    public static void main(String[] args) {
        GetStarted();
    }

    public static void GetStarted() {
        String[] options = {"Register", "Login"};
        int option = JOptionPane.showOptionDialog(null, "Select an option:", "Welcome",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (option == 0) {
            String firstName = JOptionPane.showInputDialog("Enter First Name:");
            String lastName = JOptionPane.showInputDialog("Enter Last Name:");
            String username = JOptionPane.showInputDialog("Enter Username:");
            String password = JOptionPane.showInputDialog("Enter Password:");
            JOptionPane.showMessageDialog(null, "Registration successful. Please login.");
            GetStarted();
        } else if (option == 1) {
            String userName = JOptionPane.showInputDialog("Enter Username:");
            String password = JOptionPane.showInputDialog("Enter Password:");

            if (checkLoginDetails(userName, password)) {
                JOptionPane.showMessageDialog(null, "Login successful.");
                loadTestData();
                menuLoop();
            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Try again.");
                GetStarted();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid option selected. Restarting.");
            GetStarted();
        }
    }

    public static boolean checkLoginDetails(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    public static void loadTestData() {
        Message m1 = new Message(messageCount++, "+27834557896", "Did you get the cake?");
        service.categorizeMessage(m1, "Sent");

        Message m2 = new Message(messageCount++, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        service.categorizeMessage(m2, "Stored");

        Message m3 = new Message(messageCount++, "+27834484567", "Yohoooo, I am at your gate.");
        service.categorizeMessage(m3, "Disregard");

        Message m4 = new Message(messageCount++, "0838884567", "It is dinner time !");
        service.categorizeMessage(m4, "Sent");

        Message m5 = new Message(messageCount++, "+27838884567", "Ok, I am leaving without you.");
        service.categorizeMessage(m5, "Stored");

        System.out.println("Test messages loaded.");
    }

    public static void menuLoop() {
        while (true) {
            System.out.println("\n==== Message System Menu ====");
            System.out.println("1. Send a New Message");
            System.out.println("2. View Sent Messages (Sender & Recipient)");
            System.out.println("3. Display Longest Sent Message");
            System.out.println("4. Search Message by ID");
            System.out.println("5. Search Messages by Recipient");
            System.out.println("6. Delete Message by Hash");
            System.out.println("7. Display Full Sent Report");
            System.out.println("8. Save Sent Messages to JSON");
            System.out.println("9. Exit");
            System.out.print("Enter option: ");

            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    sendNewMessage();
                    break;
                case 2:
                    service.displaySentRecipients();
                    break;
                case 3:
                    service.displayLongestSentMessage();
                    break;
                case 4: {
                    String id = JOptionPane.showInputDialog("Enter Message ID:");
                    service.searchByMessageID(id);
                    break;
                }
                case 5: {
                    String recipient = JOptionPane.showInputDialog("Enter recipient number:");
                    service.searchByRecipient(recipient);
                    break;
                }
                case 6: {
                    String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
                    service.deleteByHash(hash);
                    break;
                }
                case 7:
                    service.displaySentReport();
                    break;
                case 8:
                    saveSentMessagesToJson();
                    break;
                case 9:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    public static void sendNewMessage() {
        String recipient = JOptionPane.showInputDialog("Enter recipient number:");
        String messageText = JOptionPane.showInputDialog("Enter message:");
        String[] flags = {"Send", "Store", "Disregard"};
        String flag = (String) JOptionPane.showInputDialog(
            null,
            "Select flag:",
            "Message Flag",
            JOptionPane.QUESTION_MESSAGE,
            null,
            flags,
            flags[0]
        );
        if (flag == null) {
            JOptionPane.showMessageDialog(null, "No flag selected. Cancelling message.");
            return;
        }

        Message newMessage = new Message(messageCount++, recipient, messageText);
        service.categorizeMessage(newMessage, flag);
        JOptionPane.showMessageDialog(null, "Message processed and added to " + flag + " list.");
    }

    public static void saveSentMessagesToJson() {
        List<Message> sent = service.getSentMessages();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("sent_messages.json")) {
            gson.toJson(sent, writer);
            JOptionPane.showMessageDialog(null, "Sent messages saved to sent_messages.json");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to file: " + e.getMessage());
        }
    }
}
