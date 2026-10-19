/**
 * Login class handles user registration, username validation,
 * password complexity, cellphone validation, and login authentication.
 */
public class Login {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;

    public Login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    /**
     * Checks whether the username contains an underscore
     * and is no more than five characters long.
     */
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    /**
     * Checks whether the password has at least eight characters,
     * one uppercase letter, one number, and one special character.
     */
    public boolean checkPasswordComplexity() {

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasCapital = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Validates a South African cellphone number using a regular expression.
     * The pattern ^\\+27[0-9]{9}$ requires +27 followed by exactly
     * nine digits, with no extra characters.
     */
    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber.matches("^\\+27[0-9]{9}$");
    }

    /**
     * Registers the user only when all details are valid.
     */
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter and a number and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code, please correct the number and try again.";
        }

        return "User registered successfully.";
    }

    /**
     * Checks whether the entered username and password match
     * the registered details.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(username)
                && enteredPassword.equals(password);
    }

    /**
     * Returns a message based on whether login was successful.
     */
    public String returnLoginStatus(boolean loginSuccessful) {

        if (loginSuccessful) {
            return "Welcome " + firstName + " " + lastName
                    + ", it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}