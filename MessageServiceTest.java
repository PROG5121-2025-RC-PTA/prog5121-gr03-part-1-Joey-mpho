package com.yourcompany;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class MessageServiceTest {

    @Test
    public void testCategorizeSentMessage() {
        MessageService service = new MessageService();
        Message msg = new Message(0, "+27834567896", "Did you get the cake?");
        service.categorizeMessage(msg, "send");

        List<Message> sent = service.getSentMessages();
        assertEquals(1, sent.size());
        assertEquals("Did you get the cake?", sent.get(0).getMessage());
    }

    @Test
    public void testLongestMessage() {
        MessageService service = new MessageService();
        service.categorizeMessage(new Message(1, "+27834567891", "Hi"), "send");
        service.categorizeMessage(new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time."), "send");

        Message longest = service.getSentMessages().stream()
                .max((m1, m2) -> m1.getMessage().length() - m2.getMessage().length())
                .orElse(null);

        assertNotNull(longest);
        assertTrue(longest.getMessage().contains("Where are you?"));
    }

    @Test
    public void testSearchByMessageID() {
        MessageService service = new MessageService();
        Message msg = new Message(3, "0838884567", "It is dinner time!");
        service.categorizeMessage(msg, "send");

        String id = msg.getMessageID();
        boolean found = service.getSentMessages().stream()
                .anyMatch(m -> m.getMessageID().equals(id));

        assertTrue(found);
    }

    @Test
    public void testDeleteMessageByHash() {
        MessageService service = new MessageService();
        Message msg = new Message(4, "+27838884567", "Where are you? You are late!");
        service.categorizeMessage(msg, "store");

        String hash = msg.getMessageHash();
        service.deleteByHash(hash);

        boolean stillExists = service.getStoredMessages().stream()
                .anyMatch(m -> m.getMessageHash().equals(hash));

        assertFalse(stillExists);
    }

    @Test
    public void testSearchMessagesByRecipient() {
        MessageService service = new MessageService();
        Message msg1 = new Message(5, "+27838884567", "Hello again!");
        Message msg2 = new Message(6, "+27838884567", "Did you get my message?");
        service.categorizeMessage(msg1, "send");
        service.categorizeMessage(msg2, "store");

        long count = service.getSentMessages().stream()
                .filter(m -> m.getRecipient().equals("+27838884567"))
                .count();

        assertEquals(1, count);
    }
}
