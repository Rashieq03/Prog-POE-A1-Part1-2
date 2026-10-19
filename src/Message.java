import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Represents a message sent through QuickChat.
 */
public class Message {

    private final String messageId;
    private final String recipient;
    private final String messageText;
    private final String messageHash;

    private static final int MAX_MESSAGE_LENGTH = 250;

    public Message(String recipient, String messageText) {
        this.messageId = UUID.randomUUID().toString();
        this.recipient = recipient == null ? "" : recipient.trim();
        this.messageText = messageText == null ? "" : messageText.trim();
        this.messageHash = createMessageHash();
    }

    public String getMessageId() {
        return messageId;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    /**
     * Validates a South African cellphone number in international format.
     */
    public boolean checkRecipient() {
        return recipient.matches("\\+27[0-9]{9}");
    }

    /**
     * A message must contain text and may not exceed 250 characters.
     */
    public boolean checkMessageText() {
        return !messageText.isEmpty()
                && messageText.length() <= MAX_MESSAGE_LENGTH;
    }

    /**
     * Generates a SHA-256 hash for the message ID and message text.
     */
    private String createMessageHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String combined = messageId + ":" + messageText;
            byte[] hashBytes = digest.digest(
                    combined.getBytes(java.nio.charset.StandardCharsets.UTF_8)
            );

            StringBuilder result = new StringBuilder();

            for (byte b : hashBytes) {
                result.append(String.format("%02x", b & 0xff));
            }

            return result.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "SHA-256 is not available.", e
            );
        }
    }

    /**
     * Escapes text so that it can be written safely as JSON string values.
     */
    public static String escapeJson(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            switch (c) {
                case '"':
                    result.append("\\\"");
                    break;
                case '\\':
                    result.append("\\\\");
                    break;
                case '\n':
                    result.append("\\n");
                    break;
                case '\r':
                    result.append("\\r");
                    break;
                case '\t':
                    result.append("\\t");
                    break;
                default:
                    if (c < 32) {
                        result.append(String.format("\\u%04x", (int) c));
                    } else {
                        result.append(c);
                    }
            }
        }

        return result.toString();
    }

    /**
     * Converts a message to a JSON object.
     */
    public String toJson() {
        return "{\n"
                + "  \"messageId\": \"" + escapeJson(messageId) + "\",\n"
                + "  \"recipient\": \"" + escapeJson(recipient) + "\",\n"
                + "  \"messageText\": \"" + escapeJson(messageText) + "\",\n"
                + "  \"messageHash\": \"" + escapeJson(messageHash) + "\"\n"
                + "}";
    }

    @Override
    public String toString() {
        return "Message ID: " + messageId
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText
                + "\nMessage Hash: " + messageHash;
    }
}