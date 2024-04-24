import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class BatchTests {
    static long averageInsertionN = 0;  //Average time taken for insertion in O(N) solution
    static long averageDeletionN = 0;      //Average time taken for deletion in O(N) solution
    static double numOfRebuildsN = 0;   //Average number of rebuilds in O(N) solution
    static long avgAllocatedSizeN = 0;     //Average allocated size in O(N) solution
    static long averageInsertionNSquare = 0;    //Average time taken for insertion in O(N^2) solution
    static long averageDeletionNSquare = 0;    //Average time taken for deletion in O(N^2) solution
    static double numOfRebuildsNSquare = 0;     //Average number of rebuilds in O(N^2) solution
    static long avgAllocatedSizeNSquare = 0;       //Average allocated size in O(N^2) solution
    static int testcases = 17;      //Number of test cases
    static int n = 20000;      //Size of the hash table (N)

    /* Generates a stream of random lists of integers */
    private static Stream<ArrayList<Integer>> listProvider() {
        Random rand = new Random();
        return Stream.generate(() -> {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(rand.nextInt(n));
            }
            return list;
        }).limit(testcases);
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("listProvider")
    void testBatchInsert(ArrayList<Integer> list) {
        Perfect_Hashing_N perfect_hashing_n = new Perfect_Hashing_N();
        Perfect_Hashing_NSquare perfect_hashing_n_square = new Perfect_Hashing_NSquare();
        /* Inserting the same list in both solutions */
        long startTime = System.nanoTime();
        int[] resN = perfect_hashing_n.batchInsert(list);
        long endTime = System.nanoTime();
        /* Calculating the average time taken for insertion */
        averageInsertionN += (endTime - startTime);
        numOfRebuildsN += resN[1];
        /*Same for the O(N^2) solution */
        startTime = System.nanoTime();
        int[] resNSquare = perfect_hashing_n_square.batchInsert(list);
        endTime = System.nanoTime();
        averageInsertionNSquare += (endTime - startTime);
        numOfRebuildsNSquare += resNSquare[1];

        /* Checking if the allocated size is within the limits */
        assertTrue(perfect_hashing_n.getAllocatedSize() <= n*20 && perfect_hashing_n_square.getAllocatedSize() <= n*n*400);

        /*Add the allocated size of the hash table in this iteration*/
        avgAllocatedSizeN += perfect_hashing_n.getAllocatedSize();
        avgAllocatedSizeNSquare += perfect_hashing_n_square.getAllocatedSize();

        /*Calculate the average time taken for deletion */
        startTime = System.nanoTime();
        perfect_hashing_n.batchDelete(list);
        endTime = System.nanoTime();
        averageDeletionN += (endTime - startTime);
        /*Same for the O(N^2) solution */
        startTime = System.nanoTime();
        perfect_hashing_n_square.batchDelete(list);
        endTime = System.nanoTime();
        averageDeletionNSquare += (endTime - startTime);
    }

    @AfterAll
    static void average(){
        System.out.print("At N= " + n + ":\n");
        System.out.println("Batch Insertion in O(N) Solution took on average:  " + format(averageInsertionN/testcases));
        System.out.println("Average Number of Rebuilds in O(N) Solution:  " + numOfRebuildsN/testcases);
        System.out.println("Average Allocated Size in O(N) Solution:  " + avgAllocatedSizeN/testcases);
        System.out.println("Batch Deletion in O(N) Solution took on average:  " + format(averageDeletionN/testcases));
        System.out.println("Batch Insertion in O(N^2) Solution took on average:  " +  format(averageInsertionNSquare/testcases));
        System.out.println("Average Number of Rebuilds in O(N^2) Solution:  " + numOfRebuildsNSquare/testcases);
        System.out.println("Average Allocated Size in O(N^2) Solution:  " + avgAllocatedSizeNSquare/testcases);
        System.out.println("Batch Deletion in O(N^2) Solution took on average:  " + format(averageDeletionNSquare/testcases));
    }
    static String format(long nanoseconds){
        if (nanoseconds < 1000) return String.format("%d nanoseconds", nanoseconds);
        if (nanoseconds < 1000000) return String.format("%d microseconds", nanoseconds / 1000);
        if (nanoseconds < 1000000000) return String.format("%d milliseconds", nanoseconds / 1000000);
        return String.format("%.2f seconds", nanoseconds / 1000000000.0);
    }
}
