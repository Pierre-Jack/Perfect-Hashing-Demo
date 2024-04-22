import java.util.ArrayList;

public interface PerfectHashTable<T> {
    int getAllocatedSize(); // returns the size of the allocated hash table in sizeOf(T)
    boolean[] insert(T key); // returns an array of two booleans:
                             // the first one is true if the key is inserted successfully (didn't exist before)
                             // the second one is true if the key is inserted without collision
    boolean search(T key); // returns true if the key is found
    boolean delete(T key); // returns true if the key is deleted (existent one)

    int[] batchInsert(ArrayList<T> keys); // returns an array of two numbers:
                                            //[0] number of successful insertions (didn't exist before)
                                            //[1] and number of trials until no collision (or sum of buket collisions)
                                            
    int batchDelete(ArrayList<T> keys); // returns number of successful deletions(the existent ones)
    ArrayList<T> getKeys();
}
