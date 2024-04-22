import java.util.ArrayList;

public interface PerfectHashTable<T> {
    boolean[] insert(T key); // returns an array of two booleans:
                             // the first one is true if the key is inserted successfully (didn't exist before)
                             // the second one is true if the key is inserted without collision
    boolean search(T key); // returns true if the key is found
    boolean delete(T key); // returns true if the key is deleted (existent one)

    int[] batchInsert(ArrayList<T> keys); // returns an array of two numbers:
                                            // number of successful insertions (didn't exist before)
                                            // and number of trials until no collision

    int batchDelete(ArrayList<T> keys); // returns number of successful deletions(the existent ones)
}
