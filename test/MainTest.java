
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testMainWithValidRegistrationAndLogin() {

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        String input = String.join("\n",
                "Rashieq",
                "Sampson",
                "kyl_1",
                "Ch&&sec@kegg1",
                "+27838968976",
                "kyl_1",
                "Ch&&sec@kegg1",
                "0"
        ) + "\n";

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)
            ));

            System.setOut(new PrintStream(output));

            Main.main(new String[0]);

            String result = output.toString();

            assertTrue(result.contains(
                    "User registered successfully."
            ));

            assertTrue(result.contains(
                    "Welcome Rashieq Sampson, it is great to see you again."
            ));

            assertTrue(result.contains(
                    "How many messages would you like to send?"
            ));

            assertTrue(result.contains(
                    "You have finished processing your message limit."
            ));

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
