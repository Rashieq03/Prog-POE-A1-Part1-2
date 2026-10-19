import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LoginTest contains unit tests for the Login class.
 * It tests valid and invalid user details and login attempts.
 */
public class LoginTest {

    @Test
    public void testCorrectUsername() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertTrue(user.checkUserName());
    }

    @Test
    public void testIncorrectUsername() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyle!!!!",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertFalse(user.checkUserName());
    }

    @Test
    public void testCorrectPassword() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testIncorrectPassword() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "password", "+27838968976"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCorrectCellPhoneNumber() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testIncorrectCellPhoneNumber() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "Ch&&sec@kegg1", "08966553"
        );

        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testSuccessfulRegistration() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertEquals(
                "User registered successfully.",
                user.registerUser()
        );
    }

    @Test
    public void testFailedRegistration() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyle!!!!",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertEquals(
                "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.",
                user.registerUser()
        );
    }

    @Test
    public void testSuccessfulLogin() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertTrue(user.loginUser("kyl_1", "Ch&&sec@kegg1"));
    }

    @Test
    public void testFailedLogin() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertFalse(user.loginUser("wrong", "password"));
    }

    @Test
    public void testSuccessfulLoginStatus() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertEquals(
                "Welcome Rashieq Sampson, it is great to see you again.",
                user.returnLoginStatus(true)
        );
    }

    @Test
    public void testFailedLoginStatus() {
        Login user = new Login(
                "Rashieq", "Sampson", "kyl_1",
                "Ch&&sec@kegg1", "+27838968976"
        );

        assertEquals(
                "Username or password incorrect, please try again.",
                user.returnLoginStatus(false)
        );
    }
}