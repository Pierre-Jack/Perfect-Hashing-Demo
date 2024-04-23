import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Perfect_Hashing_N<T> implements PerfectHashTable<T>{

    private int m; // size of hash table
    private int size; // number of elements in the hash table
    private final int c = 10; // c such that c*m is the maximum size of the allocated hash table, to maintain O(N) space
    private Hasher<T> hasher;
    private ArrayList<PerfectHashTable<T>> hashTable;
    private final float load_factor = 0.75f;

    public Perfect_Hashing_N() {
        this.m = 16;    // default value
        this.hasher = new UniversalHasher<>(m);
        this.hashTable = new ArrayList<>(Collections.nCopies(m, null)); // initialize with null

    }
    public Perfect_Hashing_N(int m) {
        this.m = m;
        this.hasher = new UniversalHasher<>(m);
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

        ArrayList<T> oldKeys = this.getKeys();
        int oldSize = size;
        int collisions = 0;

        size += newKeys.size();
        while (m < size) m = 2*m;
        hashTable = new ArrayList<>(Collections.nCopies(m, null));
        hasher = new UniversalHasher<>(m);

        ArrayList<T> allKeys = new ArrayList<>();
        allKeys.addAll(oldKeys);
        allKeys.addAll(newKeys);

        do{
        size = 0;
        collisions = 0;
        for(T key : allKeys) {
            int index = hasher.hash_code(key);
            if (hashTable.get(index) == null) {
                hashTable.set(index, new Perfect_Hashing_NSquare<T>(4));
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
           if(table != null) result.addAll(table.getKeys());
        }
        return result;
    }








    public static void main(String[] args) {
        PerfectHashTable<Integer> perfect_hashing = new Perfect_Hashing_N<>() ;
//        perfect_hashing_nSquare.insert(10);
//        perfect_hashing_nSquare.insert(18);
//        perfect_hashing_nSquare.insert(25);
//        perfect_hashing_nSquare.insert(39);
//        perfect_hashing_nSquare.insert(65);
//        perfect_hashing_nSquare.insert(82);
//        perfect_hashing_nSquare.insert(111);
//        System.out.println(perfect_hashing_nSquare.getCurrentInputSize());
//        System.out.println(perfect_hashing_nSquare.getAllocatedSize());
//        System.out.println(perfect_hashing_nSquare.getKeys());
//        perfect_hashing_nSquare.delete(10);
//        perfect_hashing_nSquare.delete(18);
//        perfect_hashing_nSquare.delete(25);
//        perfect_hashing_nSquare.delete(39);
//        perfect_hashing_nSquare.delete(65);
//        perfect_hashing_nSquare.delete(82);
//        System.out.println(perfect_hashing_nSquare.getCurrentInputSize());
//        System.out.println(perfect_hashing_nSquare.getAllocatedSize());
//        System.out.println(perfect_hashing_nSquare.getKeys());
//        System.out.println(perfect_hashing_nSquare.search(111));
//        System.out.println(perfect_hashing_nSquare.search(10));
        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(10);
        keys.add(18);
        keys.add(25);
        keys.add(39);
        keys.add(65);
        keys.add(82);
        keys.add(111);
        keys.add(82);
        System.out.println(perfect_hashing.batchInsert(keys)[0]);
        //     System.out.println(perfect_hashing_nSquare.getAllocatedSize());
        System.out.println(perfect_hashing.getKeys());
        ArrayList<Integer> keys3 = new ArrayList<>();

        keys3.add(65);
        keys3.add(82);
        keys3.add(111);
        keys3.add(82);
        keys3.add(100);
        System.out.println(perfect_hashing.batchInsert(keys3)[0]);
        System.out.println(perfect_hashing.getKeys());

        ArrayList<Integer> keys2 = new ArrayList<>();
        keys2.add(10);
        keys2.add(10);
        keys2.add(111);
        keys2.add(25);
        keys2.add(30);
        perfect_hashing.batchDelete(keys2);
        System.out.println();
        System.out.println(perfect_hashing.getAllocatedSize());
        System.out.println(perfect_hashing.getKeys());
        System.out.println(perfect_hashing.search(30));
        System.out.println(perfect_hashing.search(111));
        System.out.println(perfect_hashing.search(39));

//        Perfect_Hashing_NSquare<String> perfect_hashing_nSquare = new Perfect_Hashing_NSquare<>() ;
//        perfect_hashing_nSquare.insert("hello");
//        perfect_hashing_nSquare.insert("world");
//        perfect_hashing_nSquare.insert("this");
//        perfect_hashing_nSquare.insert("is");
//        perfect_hashing_nSquare.insert("a");
//        perfect_hashing_nSquare.insert("test");
//
//        System.out.println(perfect_hashing_nSquare.getCurrentInputSize());
//System.out.println(perfect_hashing_nSquare.getAllocatedSize());
//System.out.println(perfect_hashing_nSquare.getKeys());
//perfect_hashing_nSquare.delete("hello");
//perfect_hashing_nSquare.delete("world");
//        perfect_hashing_nSquare.delete("world");
//        perfect_hashing_nSquare.delete("ff");
//System.out.println(perfect_hashing_nSquare.getCurrentInputSize());
//System.out.println(perfect_hashing_nSquare.getAllocatedSize());
//System.out.println(perfect_hashing_nSquare.getKeys());
//
//System.out.println(perfect_hashing_nSquare.search("this"));



    }
}