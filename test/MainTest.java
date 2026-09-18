import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testSuccessfulLogin() {

        Login user = new Login(
                "Rashieq",
                "Sampson",
                "kyl_1",
                "Ch&&sec@kegg1",
                "+27838968976"
        );

        boolean result = user.loginUser(
                "kyl_1",
                "Ch&&sec@kegg1"
        );

        assertTrue(result);
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

        boolean result = user.loginUser(
                "wrong",
                "wrong"
        );

        assertFalse(result);
    }
}
