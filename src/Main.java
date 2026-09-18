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

        System.out.println(user.registerUser());

        if (user.checkUserName()
                && user.checkPasswordComplexity()
                && user.checkCellPhoneNumber()) {

            System.out.print("Enter your username to login: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter your password to login: ");
            String loginPassword = scanner.nextLine();

            boolean loginSuccessful =
                    user.loginUser(loginUsername, loginPassword);

            System.out.println(
                    user.returnLoginStatus(loginSuccessful)
            );
        }

        scanner.close();
    }
}