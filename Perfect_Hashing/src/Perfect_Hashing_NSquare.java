import java.util.ArrayList;
import java.util.Arrays;

public class Perfect_Hashing_NSquare<T> implements PerfectHashTable<T>{
    private int sizeOfMaxInput;     /* size of max input , increasing dynamically with insertion operations */
    private int sizeOfHashTable;    /* size of hash table = M = N^2 */
    private T[] hashTable;        /* hash table */
    private Hasher<T> hasher;
    private int currentInputSize;       /* keep track of number of elements inserted */
    private boolean[] isOccupied;       /* keep track for indexes that is used in hashTable */
    private double load_factor ;        /* load factor for percentage of filling hashTable */

    public Perfect_Hashing_NSquare() {
        this.sizeOfMaxInput = 4;        /* initial Max input */
        this.sizeOfHashTable = this.sizeOfMaxInput*this.sizeOfMaxInput;
        this.hasher = new UniversalHasher<T>(sizeOfHashTable);
        this.currentInputSize = 0;
        this.hashTable = (T[]) new Object[this.sizeOfHashTable];
        this.isOccupied = new boolean[this.sizeOfHashTable];
        Arrays.fill(this.isOccupied , false) ;
        this.load_factor = .75 ;
    }

    public Perfect_Hashing_NSquare(int sizeOfMaxInput) {        /* constructor with N passed from user */
        this.sizeOfMaxInput = sizeOfMaxInput;        /* initial Max input */
        this.sizeOfHashTable = this.sizeOfMaxInput*this.sizeOfMaxInput;
        this.hasher = new UniversalHasher<T>(sizeOfHashTable);
        this.currentInputSize = 0;
        this.hashTable = (T[]) new Object[this.sizeOfHashTable];
        this.isOccupied = new boolean[this.sizeOfHashTable];
        Arrays.fill(this.isOccupied , false) ;
        this.load_factor = .75 ;
    }

    @Override
    public boolean[] insert(T key) {
        int index = this.hasher.hash_code(key);
        boolean[] result = new boolean[2];
        if(this.isOccupied[index] && this.hashTable[index] == key){
            result[0] = false;
            result[1] = false;
        }                                                       /* key is inserted already */
        else{
            if ((double) this.currentInputSize / this.sizeOfHashTable > this.load_factor) {
                /*System.out.println("the size will increase") ;*/
                result[0] = true;
                result[1] = true;
                currentInputSize++;
                this.rehash(key , true);       /* increasing size of hashTable , load factor is broken */
            } else {
                if (!this.isOccupied[index]) {      /* no corner case , inserted normally */
                    this.hashTable[index] = key;
                    this.isOccupied[index] = true;
                    /*System.out.println("no problem , will insert") ;*/
                    result[0] = true;
                    result[1] = true;
                    currentInputSize++;
                } else {
                    /*System.out.println("collision , will re-build") ;*/
                    currentInputSize++;
                    this.rehash(key , false);       /* collision , re-build and choosing new hash function */
                    result[0] = true;
                    result[1] = false;
                }
            }
        }
        return result;
    }


    private void rehash(T key , boolean load_factor_broken){
        T[] temp = this.hashTable.clone();        /* copy hashTable and occupied array */
        boolean [] occupied_temp = this.isOccupied.clone() ;


        if(load_factor_broken) {
            this.sizeOfMaxInput = this.currentInputSize * 2;
        }
        else {
            this.sizeOfMaxInput = this.currentInputSize;
        }

        int m = this.sizeOfMaxInput * this.sizeOfMaxInput;
        this.sizeOfHashTable = (int) Math.pow(2 , Math.ceil (Math.log(m) / Math.log(2))) ;      /* rounding size to nearist power of 2 */ 
        this.hasher = new UniversalHasher<T>(sizeOfHashTable);  /* creating new universal hash family */ 

        boolean finished ;  /* for determining if the rehash operation is done */
        do{
            finished = true ;
            this.hashTable = (T[]) new Object[this.sizeOfHashTable];     /* clear hashTable and isOccupied array */
            this.isOccupied = new boolean[this.sizeOfHashTable];
            Arrays.fill(this.isOccupied, false);

            this.hasher.regenerate();                       /* regenrate new hash function */
            int index = this.hasher.hash_code(key) ;     /* insert element value in the hashable */
            this.hashTable[index] = key ;
            this.isOccupied[index] = true ;
            /*System.out.println("element = " + key + " , index = " + index ) ;*/

            for(int i=0 ; i<temp.length ; i++){         /* insert elements that are in the hashTable and if there is collision repeat with new function */
                if(occupied_temp[i]){
                    index = this.hasher.hash_code(temp[i]) ;
                    /*System.out.println("elemnt = " + temp[i] + " , index = " + index ) ;*/
                    if(!this.isOccupied[index]){
                        this.hashTable[index] = temp[i] ;
                        this.isOccupied[index] = true ;
                    }
                    else {
                        finished = false ;          /* collision , create new hash function */
                        /*System.out.println("collision , will re-build") ;*/
                        if(this.sizeOfMaxInput != this.currentInputSize){
                            this.sizeOfMaxInput = this.currentInputSize ;
                            m = this.sizeOfMaxInput * this.sizeOfMaxInput;
                            this.sizeOfHashTable = (int) Math.pow(2 , Math.ceil (Math.log(m) / Math.log(2))) ;
                            this.hasher = new UniversalHasher<T>(sizeOfHashTable);  /* creating new universal hash family */
                        }
                        break ;
                    }
                }
            }
        }while(!finished) ;
    }

    @Override
    public boolean search(T key) {
        int index = this.hasher.hash_code(key);
        return this.isOccupied[index] && this.hashTable[index] == key;
    }

    @Override
    public boolean delete(T key) {
        int index = this.hasher.hash_code(key);
        if(this.isOccupied[index] && this.hashTable[index] == key){
            currentInputSize--;
            this.isOccupied[index] = false;
            return true;
        }
        else{
            return false;
        }
    }
    
    private int[] batchRehash(ArrayList<T> keys){
        T[] temp = this.hashTable.clone();        /* copy hashTable and occupied function */
        boolean [] occupied_temp = this.isOccupied.clone() ;
        int oldSize = currentInputSize;
        boolean finished ;  /* for determining if the rehash operation is done */
        sizeOfHashTable = (oldSize + keys.size()) * (oldSize + keys.size());

        int tmp = 1;
        while(tmp < sizeOfHashTable){
            tmp *= 2;
        }
        sizeOfHashTable = tmp;

        this.hashTable = (T[]) new Object[this.sizeOfHashTable];     /* clear hashTable and isOccupied array */
        this.isOccupied = new boolean[this.sizeOfHashTable];

//        this.b = (int) (Math.log(this.sizeOfHashTable) / Math.log(2));
//        this.s = new Universal_Hash_Family(this.b, this.u);       /* generating new hash family */
        this.hasher = new UniversalHasher<T>(sizeOfHashTable);

        int numOfCollisions = 0;
        do{
            finished = true ;
            Arrays.fill(this.isOccupied, false);
//            this.func = this.s.hash_function();         /* generate new hash function */
//            this.h = new Hashing(this.func);
            hasher.regenerate();


            for(int i=0 ; i<temp.length ; i++){         /* insert elements that are in the hashTable and if there is collision repeat with new function */
                if(occupied_temp[i]){
                    int index = this.hasher.hash_code(temp[i]) ;

                    System.out.println("elemnt = " + temp[i] + " , index = " + index ) ;

                    if(!this.isOccupied[index]){
                        this.hashTable[index] = temp[i] ;
                        this.isOccupied[index] = true ;
                    }
                    else {
                        finished = false ;          /* collision , create new hash function */
                        numOfCollisions++;
                        System.out.println("collision , will re-build") ;
                        break ;
                    }
                }
            }

            for(T key : keys){
                int index = this.hasher.hash_code(key) ;   /* insert element value in the hashable */
                if(this.isOccupied[index] && this.hashTable[index] == key){
                    continue;
                }
                if(this.isOccupied[index] && this.hashTable[index] != key){
                    finished = false;
                    numOfCollisions++;
                    break;
                }
                this.hashTable[index] = key ;
                this.isOccupied[index] = true ;
                currentInputSize++;
            }

        }while(!finished) ;

        return new int[]{currentInputSize - oldSize, numOfCollisions};
    }

    public int getCurrentInputSize() {
        return currentInputSize;
    }
    public int getSizeOfMaxInput() {
        return sizeOfMaxInput;
    }
    public int getSizeOfHashTable() {
        return sizeOfHashTable;
    }

    @Override
    public int getAllocatedSize() {
        return sizeOfHashTable;
    }




    @Override
    public int[] batchInsert(ArrayList<T> keys) {
        return batchRehash(keys);
    }

    @Override
    public int batchDelete(ArrayList<T> keys) {
        int numOfDeletion = 0;
        for(T key : keys){
            if(this.delete(key) == true){
                numOfDeletion += 1;
            }
        }
        return numOfDeletion;
    }

    @Override
    public ArrayList<T> getKeys() {
        ArrayList<T> keys = new ArrayList<>();
        for(int i=0 ; i<this.sizeOfHashTable ; i++){
            if(this.isOccupied[i]){
                keys.add(this.hashTable[i]);
            }
        }
        return keys;
    }


    public static void main(String[] args) {
        Perfect_Hashing_NSquare<Integer> perfect_hashing_nSquare = new Perfect_Hashing_NSquare<>() ;
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
        System.out.println(perfect_hashing_nSquare.batchInsert(keys)[0]);
        //     System.out.println(perfect_hashing_nSquare.getAllocatedSize());
        System.out.println(perfect_hashing_nSquare.getKeys());
        ArrayList<Integer> keys3 = new ArrayList<>();

        keys3.add(65);
        keys3.add(82);
        keys3.add(111);
        keys3.add(82);
        keys3.add(100);
        System.out.println(perfect_hashing_nSquare.batchInsert(keys3)[0]);
        System.out.println(perfect_hashing_nSquare.getKeys());

        ArrayList<Integer> keys2 = new ArrayList<>();
        keys2.add(10);
        keys2.add(10);
        keys2.add(111);
        keys2.add(25);
        keys2.add(30);
        perfect_hashing_nSquare.batchDelete(keys2);
        System.out.println();
        System.out.println(perfect_hashing_nSquare.getAllocatedSize());
        System.out.println(perfect_hashing_nSquare.getKeys());
        System.out.println(perfect_hashing_nSquare.search(30));
        System.out.println(perfect_hashing_nSquare.search(111));
        System.out.println(perfect_hashing_nSquare.search(39));

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
