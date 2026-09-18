import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testCorrectUsername() {

        Login user = new Login(
                "Rashieq",
                "Sampson",
                "kyl_1",
                "Ch&&sec@kegg1",
                "+27838968976"
        );

        assertTrue(user.checkUserName());
    }

    @Test
    public void testIncorrectUsername() {

        Login user = new Login(
                "Rashieq",
                "Sampson",
                "kyle!!!!",
                "Ch&&sec@kegg1",
                "+27838968976"
        );

        assertFalse(user.checkUserName());
    }

    @Test
    public void testCorrectPassword() {

        Login user = new Login(
                "Rashieq",
                "Sampson",
                "kyl_1",
                "Ch&&sec@kegg1",
                "+27838968976"
        );

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testIncorrectPassword() {

        Login user = new Login(
                "Rashieq",
                "Sampson",
                "kyl_1",
                "password",
                "+27838968976"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCorrectCellPhoneNumber() {

        Login user = new Login(
                "Rashieq",
                "Sampson",
                "kyl_1",
                "Ch&&sec@kegg1",
                "+27838968976"
        );

        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testIncorrectCellPhoneNumber() {

        Login user = new Login(
                "Rashieq",
                "Sampson",
                "kyl_1",
                "Ch&&sec@kegg1",
                "08966553"
        );

        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testSuccessfulLogin() {

        Login user = new Login(
                "Rashieq",
                "Sampson",
                "kyl_1",
                "Ch&&sec@kegg1",
                "+27838968976"
        );

        assertTrue(user.loginUser("kyl_1", "Ch&&sec@kegg1"));
    }

    @Test
    public void testFailedLogin() {

        Login user = new Login(
                "Rashieq",
                "Sampson",
                "kyl_1",
                "Ch&&sec@kegg1",
                "+27838968976"
        );

        assertFalse(user.loginUser("wrong", "password"));
    }
}
