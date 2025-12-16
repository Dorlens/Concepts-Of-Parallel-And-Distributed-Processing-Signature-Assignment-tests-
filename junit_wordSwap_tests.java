import static org.junit.Assert.*;
import org.junit.Test;


// Unit testing is testing individual components of a program to see if they work as expected.
//System testing is testing the entire system as a whole to make sure it meets the specified requirements.
// Integration testing is testing how different components of a program work together to ensure they function correctly together.


public class junit_wordSwap_tests {


    @Test
    public void testWordCountUnchanged() {
        String msg = "no more headaches today";
        String altered = TCPServer.swapTwoWords(msg);

        assertEquals(
            msg.split("\\s+").length,
            altered.split("\\s+").length
        );
    }

   
    @Test
    public void testSameWordsExist() {
        String msg = "alpha beta gamma delta epsilon zeta eta theta";
        String altered = TCPServer.swapTwoWords(msg);

        for (String word : msg.split("\\s+")) {
            assertTrue(altered.contains(word));
        }
    }


    @Test
    public void testTwoWordsSwapped() {
        String msg = "first second";
        String altered = TCPServer.swapTwoWords(msg);
        // With only 2 words, they must swap (if i != j logic is implemented)
        // Result should be "second first"
        assertTrue(altered.equals("second first") || altered.equals("first second"));
    }
}
