import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageIdIsGenerated() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertNotNull(message.getMessageId(),
                "Message ID should not be null.");

        assertFalse(message.getMessageId().isEmpty(),
                "Message ID should not be empty.");
    }

    @Test
    public void testMessageHashIsGenerated() {
        Message message = new Message(
                "+27838968976",
                "Hi Mike, can you join us tonight?"
        );

        assertNotNull(message.getMessageHash(),
                "Message hash should not be null.");

        assertEquals(64, message.getMessageHash().length(),
                "SHA-256 hash should contain 64 hexadecimal characters.");

        assertTrue(
                message.getMessageHash().matches("[0-9a-f]{64}"),
                "Message hash should contain hexadecimal characters."
        );
    }

    @Test
    public void testMessageHashRemainsConsistent() {
        Message message = new Message(
                "+27838968976",
                "Hello, this is a test message."
        );

        String originalHash = message.getMessageHash();

        assertEquals(originalHash, message.getMessageHash(),
                "Message hash should remain consistent for the same message.");
    }

    @Test
    public void testValidRecipient() {
        Message message = new Message(
                "+27838968976",
                "Hello"
        );

        assertTrue(message.checkRecipient(),
                "A correctly formatted South African number should be accepted.");
    }

    @Test
    public void testInvalidRecipient() {
        Message message = new Message(
                "0838968976",
                "Hello"
        );

        assertFalse(message.checkRecipient(),
                "A number without the international code should be rejected.");
    }

    @Test
    public void testRecipientWithIncorrectCountryCode() {
        Message message = new Message(
                "+12345678901",
                "Hello"
        );

        assertFalse(message.checkRecipient(),
                "A number with an incorrect country code should be rejected.");
    }

    @Test
    public void testValidMessageText() {
        Message message = new Message(
                "+27838968976",
                "Hi Mike, can you join us tonight?"
        );

        assertTrue(message.checkMessageText(),
                "A message containing text should be accepted.");
    }

    @Test
    public void testEmptyMessageText() {
        Message message = new Message(
                "+27838968976",
                ""
        );

        assertFalse(message.checkMessageText(),
                "An empty message should be rejected.");
    }

    @Test
    public void testMessageAtMaximumLength() {
        String text = "A".repeat(250);

        Message message = new Message(
                "+27838968976",
                text
        );

        assertTrue(message.checkMessageText(),
                "A message containing exactly 250 characters should be accepted.");
    }

    @Test
    public void testMessageExceedsMaximumLength() {
        String text = "A".repeat(251);

        Message message = new Message(
                "+27838968976",
                text
        );

        assertFalse(message.checkMessageText(),
                "A message exceeding 250 characters should be rejected.");
    }

    @Test
    public void testMessageDetailsAreStored() {
        Message message = new Message(
                "+27838968976",
                "Hello, this is a test message."
        );

        assertEquals("+27838968976", message.getRecipient());
        assertEquals("Hello, this is a test message.",
                message.getMessageText());
    }

    @Test
    public void testNullValuesAreHandled() {
        Message message = new Message(null, null);

        assertEquals("", message.getRecipient());
        assertEquals("", message.getMessageText());

        assertFalse(message.checkRecipient());
        assertFalse(message.checkMessageText());
    }

    @Test
    public void testMessageConvertsToJson() {
        Message message = new Message(
                "+27838968976",
                "Hello"
        );

        String json = message.toJson();

        assertTrue(json.contains("\"messageId\":"));
        assertTrue(json.contains("\"recipient\": \"+27838968976\""));
        assertTrue(json.contains("\"messageText\": \"Hello\""));
        assertTrue(json.contains("\"messageHash\":"));
    }

    @Test
    public void testJsonEscapesSpecialCharacters() {
        String escaped = Message.escapeJson(
                "Hello \"Mike\"\nWelcome"
        );

        assertEquals("Hello \\\"Mike\\\"\\nWelcome", escaped);
    }

    @Test
    public void testMessageToString() {
        Message message = new Message(
                "+27838968976",
                "Hello"
        );

        String output = message.toString();

        assertTrue(output.contains("Message ID:"));
        assertTrue(output.contains("Recipient: +27838968976"));
        assertTrue(output.contains("Message: Hello"));
        assertTrue(output.contains("Message Hash:"));
    }
}