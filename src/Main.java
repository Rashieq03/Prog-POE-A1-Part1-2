
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your cell phone number: ");
        String cellPhoneNumber = scanner.nextLine();

        Login user = new Login(
                firstName,
                lastName,
                username,
                password,
                cellPhoneNumber
        );

        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        if (!registrationMessage.equals("User registered successfully.")) {
            scanner.close();
            return;
        }

        System.out.print("Enter your username to login: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter your password to login: ");
        String loginPassword = scanner.nextLine();

        boolean loginSuccessful =
                user.loginUser(loginUsername, loginPassword);

        System.out.println(user.returnLoginStatus(loginSuccessful));

        if (!loginSuccessful) {
            scanner.close();
            return;
        }

        System.out.print("How many messages would you like to send? ");

        int messageLimit;

        while (true) {
            String input = scanner.nextLine();

            try {
                messageLimit = Integer.parseInt(input);

                if (messageLimit >= 0) {
                    break;
                }

                System.out.print("Please enter a valid whole number: ");

            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid whole number: ");
            }
        }

        ArrayList<Message> sentMessages = new ArrayList<>();
        ArrayList<Message> storedMessages = new ArrayList<>();

        int messagesProcessed = 0;

        while (messagesProcessed < messageLimit) {

            System.out.println("\n===== QUICKCHAT =====");
            System.out.println("1. Create a message");
            System.out.println("2. Show sent messages");
            System.out.println("3. Show messages stored for later");
            System.out.println("4. Quit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1": {
                    System.out.print("Enter recipient cell phone number: ");
                    String recipient = scanner.nextLine();

                    System.out.print("Enter a message: ");
                    String messageText = scanner.nextLine();

                    if (messageText.length() > 250) {
                        System.out.println(
                                "Please enter a message of less than 250 characters."
                        );
                        break;
                    }

                    Message message = new Message(recipient, messageText);

                    if (!message.checkRecipient()) {
                        System.out.println(
                                "Invalid recipient number. Use +27 followed by 9 digits."
                        );
                        break;
                    }

                    if (!message.checkMessageText()) {
                        System.out.println("Please enter a message.");
                        break;
                    }

                    System.out.println("\nChoose what to do with this message:");
                    System.out.println("1. Send message");
                    System.out.println("2. Disregard message");
                    System.out.println("3. Store message to send later");
                    System.out.print("Choose an option: ");

                    String messageChoice = scanner.nextLine();

                    switch (messageChoice) {

                        case "1":
                            sentMessages.add(message);
                            messagesProcessed++;

                            System.out.println("Message successfully sent.");
                            displayMessage(message);
                            break;

                        case "2":
                            messagesProcessed++;
                            System.out.println("Message disregarded.");
                            break;

                        case "3":
                            storedMessages.add(message);
                            messagesProcessed++;

                            System.out.println("Message successfully stored.");
                            break;

                        default:
                            System.out.println(
                                    "Invalid option. The message was not sent or stored."
                            );
                    }

                    break;
                }

                case "2":
                    if (sentMessages.isEmpty()) {
                        System.out.println("No messages have been sent yet.");
                    } else {
                        for (Message sentMessage : sentMessages) {
                            displayMessage(sentMessage);
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case "3":
                    if (storedMessages.isEmpty()) {
                        System.out.println("No messages are stored for later.");
                    } else {
                        for (Message storedMessage : storedMessages) {
                            displayMessage(storedMessage);
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case "4":
                    System.out.println(
                            "Thank you for using QuickChat. Goodbye!"
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Invalid option. Please choose 1, 2, 3, or 4."
                    );
            }
        }

        System.out.println(
                "\nYou have finished processing your message limit."
        );

        scanner.close();
    }

    public static void displayMessage(Message message) {
        System.out.println("Message ID: " + message.getMessageId());
        System.out.println("Message Hash: " + message.getMessageHash());
        System.out.println("Recipient: " + message.getRecipient());
        System.out.println("Message: " + message.getMessageText());
    }
}
