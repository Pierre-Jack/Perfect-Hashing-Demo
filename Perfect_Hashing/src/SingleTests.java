import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SingleTests {

    static long averageInsertionN = 0;      //Average time taken for insertion in O(N) solution
    static long averageSearchN = 0;     //Average time taken for search in O(N) solution
    static long averageDeletionN = 0;       //Average time taken for deletion in O(N) solution
    static long averageInsertionNSquare = 0;        //Average time taken for insertion in O(N^2) solution
    static long averageSearchNSquare = 0;       //Average time taken for search in O(N^2) solution
    static long averageDeletionNSquare = 0;     //Average time taken for deletion in O(N^2) solution
    static int testcases = 17;      //Number of test cases

    /* Generates a stream of random integers */
    private static Stream<Integer> intProvider() {
        Random rand = new Random();
        return Stream.generate(() -> rand.nextInt()).limit(testcases);
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("intProvider")
    void testSingles(int key) {
        Perfect_Hashing_N perfect_hashing_n = new Perfect_Hashing_N();
        Perfect_Hashing_NSquare perfect_hashing_nSquare = new Perfect_Hashing_NSquare();
        /* Inserting the same integer in both solutions */
        long startTime = System.nanoTime();
        boolean[] resultN = perfect_hashing_n.insert(key);
        long endTime = System.nanoTime();
        averageInsertionN += (endTime - startTime);
        /*Same for the O(N^2) solution */
        startTime = System.nanoTime();
        boolean[] resultNSquare = perfect_hashing_nSquare.insert(key);
//            assertEquals(true, perfect_hashing_nSquare.insert(key)[0]);
        endTime = System.nanoTime();
        averageInsertionNSquare += (endTime - startTime);

        /* Searching for the integer in both solutions */
        startTime = System.nanoTime();
        boolean searchResultN = perfect_hashing_n.search(key);
        endTime = System.nanoTime();
        averageSearchN += (endTime - startTime);
        startTime = System.nanoTime();
        boolean searchResultNSquare = perfect_hashing_nSquare.search(key);
        endTime = System.nanoTime();
        averageSearchNSquare += (endTime - startTime);

        /* Deleting the integer in both solutions */
        startTime = System.nanoTime();
        boolean deleteResultN = perfect_hashing_n.delete(key);
        endTime = System.nanoTime();
        averageDeletionN += (endTime - startTime);
        startTime = System.nanoTime();
        boolean deleteResultNSquare = perfect_hashing_nSquare.delete(key);
        endTime = System.nanoTime();
        averageDeletionNSquare += (endTime - startTime);

        /* Checking if the results are consistent */
        assertEquals(resultN[0], searchResultN);
        assertEquals(resultNSquare[0], searchResultNSquare);
        assertEquals(resultN[0], deleteResultN);
        assertEquals(resultNSquare[0], deleteResultNSquare);
    }

    @AfterAll
    static void average(){
        System.out.println("Insertion in O(N) Solution took on average:  " + format(averageInsertionN/testcases));
        System.out.println("Insertion in O(N^2) Solution took on average:  " +  format(averageInsertionNSquare/testcases));
        System.out.println("Search in O(N) Solution took on average:  " + format(averageSearchN/testcases));
        System.out.println("Search in O(N^2) Solution took on average:  " +  format(averageSearchNSquare/testcases));
        System.out.println("Deletion in O(N) Solution took on average:  " + format(averageDeletionN/testcases));
        System.out.println("Deletion in O(N^2) Solution took on average:  " +  format(averageDeletionNSquare/testcases));
    }
    static String format(long nanoseconds){
        if (nanoseconds < 1000) return String.format("%d nanoseconds", nanoseconds);
        if (nanoseconds < 1000000) return String.format("%d microseconds", nanoseconds / 1000);
        if (nanoseconds < 1000000000) return String.format("%d milliseconds", nanoseconds / 1000000);
        return String.format("%.2f seconds", nanoseconds / 1000000000.0);
    }
}
