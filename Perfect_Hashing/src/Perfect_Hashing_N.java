import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Perfect_Hashing_N<T> implements PerfectHashTable<T>{

    private int m; // size of hash table
    private int size; // number of elements in the hash table
    private final int c = 2; // c such that c*m is the maximum size of the allocated hash table, to maintain O(N) space
    private Hasher<T> hasher;
    private ArrayList<PerfectHashTable<T>> hashTable;
    private final float load_factor = 0.75f;

    public Perfect_Hashing_N() {
        this.m = 10;    // default value
        this.hasher = new DummyHasher<>(m);
        this.hashTable = new ArrayList<>(Collections.nCopies(m, null)); // initialize with null

    }
    public Perfect_Hashing_N(int m) {
        this.m = m;
        this.hasher = new DummyHasher<>(m);
        this.hashTable = new ArrayList<>(Collections.nCopies(m, null)); // initialize with null

    }

    @Override
    public int getAllocatedSize() {
        int result = 0;
        for(PerfectHashTable<T> table : hashTable){
            if(table != null) result += table.getAllocatedSize();
        }
        return result;
    }
    private int[] rehash(ArrayList<T> newKeys){

        int[] result = new int[2];

        ArrayList<PerfectHashTable<T>> oldHashTable = hashTable;
        int oldSize = size;
        int collisions = 0;

        size += newKeys.size();
        while (m<=2*size) m = 2*m;
        hashTable = new ArrayList<>(Collections.nCopies(m, null));
        hasher = new DummyHasher<>(m);

        ArrayList<T> allKeys = new ArrayList<>();
        allKeys.addAll(this.getKeys());
        allKeys.addAll(newKeys);

        do{
        size = 0;
        collisions = 0;
        for(T key : allKeys) {
            int index = hasher.hash_code(key);
            if (hashTable.get(index) == null) {
                hashTable.set(index, new Perfect_Hashing_NSquare<T>());
            }

            boolean[] temp = hashTable.get(index).insert(key);
            if (temp[0] == true) size++;
            if (temp[0] == true && temp[1] == false) collisions++;
        }
        } while(this.getAllocatedSize() > c*m);
        return new int[]{size - oldSize, collisions};
    }

    private int[] rehash(){
        return rehash(new ArrayList<T>(0));
    }
    @Override
    public boolean[] insert(T key) {
        boolean[] result = new boolean[2];
        int index = hasher.hash_code(key);
        if (hashTable.get(index) == null) {  hashTable.set(index, new Perfect_Hashing_NSquare<T>()); }

        result = hashTable.get(index).insert(key);
        if (result[0] == true) size++;
        if(size > load_factor*m) rehash();
        if (result[1] == false && result[0] == true) result[1] = (this.getAllocatedSize() < c*m);
        if (result[1] == false && result[0] == true) rehash(); // rehashes if size != O(m)
        return result;
    }

    @Override
    public boolean search(T key) {
        int index = hasher.hash_code(key);
        if (hashTable.get(index) == null) return false;
        return hashTable.get(index).search(key);
    }

    @Override
    public boolean delete(T key) {
        int index = hasher.hash_code(key);
        if (hashTable.get(index) == null) return false;
        boolean result = hashTable.get(hasher.hash_code(key)).delete(key);
        if (result == true) size--;
        return result;
    }

    @Override
    public int[] batchInsert(ArrayList<T> keys) {
        return rehash(keys);
    }

    @Override
    public int batchDelete(ArrayList<T> keys) {
        int result = 0;
        for(T key : keys){
            if(this.delete(key) == true) result += 1;
        }
        return result;
    }

    @Override
    public ArrayList<T> getKeys() {
        ArrayList<T> result  = new ArrayList<>();
        for(PerfectHashTable<T> table : hashTable){
            result.addAll(table.getKeys());
        }
        return result;
    }
}
