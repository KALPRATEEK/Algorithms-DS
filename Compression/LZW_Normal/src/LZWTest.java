import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LZWTest {

    @Test
    public void testCompression() {


        // Test Case 1
        String input1 = "ABABABABCA";
        String expectedCompressedResult1 = "65 66 1024 1026 66 67 65";
        assertEquals(expectedCompressedResult1, LZWCompression.compressLZWWithBinarySearchTree(input1));

        // Test Case 2
        String input2 = "TOBEORNOTTOBEORTOBEORNOT";
        String expectedCompressedResult2 = "84 79 66 69 79 82 78 79 84 1024 1026 1028 1033 1027 1029 1031";
        assertEquals(expectedCompressedResult2, LZWCompression.compressLZWWithBinarySearchTree(input2));

        // Test Case 3
        String input3 = "ABRACADABRABRACADABRA";
        String expectedCompressedResult3 = "65 66 82 65 67 65 68 1024 1026 1025 1027 1029 1031 65";
        assertEquals(expectedCompressedResult3, LZWCompression.compressLZWWithBinarySearchTree(input3));

        // Test Case 4
        String input4 = "MISSISSIPPI";
        String expectedCompressedResult4 = "77 73 83 83 1025 1027 80 80 73";
        assertEquals(expectedCompressedResult4, LZWCompression.compressLZWWithBinarySearchTree(input4));
    }
}
