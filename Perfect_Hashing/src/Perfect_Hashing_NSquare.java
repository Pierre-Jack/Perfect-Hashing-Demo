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

    private int number_of_rehashing_times ;

    public Perfect_Hashing_NSquare() {
        this.sizeOfMaxInput = 4;        /* initial Max input */
        this.sizeOfHashTable = this.sizeOfMaxInput*this.sizeOfMaxInput;
        this.hasher = new UniversalHasher<T>(sizeOfHashTable);
        this.currentInputSize = 0;
        this.hashTable = (T[]) new Object[this.sizeOfHashTable];
        this.isOccupied = new boolean[this.sizeOfHashTable];
        Arrays.fill(this.isOccupied , false) ;
        this.load_factor = .75 ;
        this.number_of_rehashing_times = 0 ;
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
        this.number_of_rehashing_times =0 ;
    }

    @Override
    public boolean[] insert(T key) {
        int index = this.hasher.hash_code(key);
        boolean[] result = new boolean[2];
        if(this.isOccupied[index] && this.hashTable[index].equals(key)){ /* key is inserted already */
            result[0] = false;
            result[1] = false;
        }
        else{
            if ((double) this.currentInputSize / this.sizeOfHashTable > this.load_factor) {
                result[0] = true;
                result[1] = false;
                this.currentInputSize++;
                this.rehash(key , true);       /* increasing size of hashTable , load factor is broken */
            } else {
                if (!this.isOccupied[index]) {      /* no corner case , inserted normally */
                    this.hashTable[index] = key;
                    this.isOccupied[index] = true;
                    result[0] = true;
                    result[1] = true;
                    this.currentInputSize++;
                } else {
                    this.currentInputSize++;
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
            if(!load_factor_broken)
                this.number_of_rehashing_times ++ ;

            finished = true ;
            this.hashTable = (T[]) new Object[this.sizeOfHashTable];     /* clear hashTable and isOccupied array */
            this.isOccupied = new boolean[this.sizeOfHashTable];
            Arrays.fill(this.isOccupied, false);

            System.out.println("will re-build with N = " + this.sizeOfMaxInput +  " , load factor broken = " + load_factor_broken) ;

            this.hasher.regenerate();                       /* regenrate new hash function */
            int index = this.hasher.hash_code(key) ;     /* insert element value in the hashable */
            this.hashTable[index] = key ;
            this.isOccupied[index] = true ;

            for(int i=0 ; i<temp.length ; i++){         /* insert elements that are in the hashTable and if there is collision repeat with new function */
                if(occupied_temp[i]){
                    index = this.hasher.hash_code(temp[i]) ;
                    if(!this.isOccupied[index]){
                        this.hashTable[index] = temp[i] ;
                        this.isOccupied[index] = true ;
                    }
                    else {
                        finished = false ;          /* collision , create new hash function */
                        if(this.sizeOfMaxInput != this.currentInputSize){
                            this.sizeOfMaxInput = this.currentInputSize ;
                            m = this.sizeOfMaxInput * this.sizeOfMaxInput;
                            this.sizeOfHashTable = (int) Math.pow(2 , Math.ceil (Math.log(m) / Math.log(2))) ;
                            this.hasher = new UniversalHasher<T>(sizeOfHashTable);  /* creating new universal hash family */
                            load_factor_broken = false ;
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
        return this.isOccupied[index] && this.hashTable[index].equals(key);
    }

    @Override
    public boolean delete(T key) {
        int index = this.hasher.hash_code(key);
        if(this.isOccupied[index] && this.hashTable[index].equals(key)){
            currentInputSize--;
            this.isOccupied[index] = false;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int[] batchInsert(ArrayList<T> keys) {
        return batchRehash(keys);
    }
    private int[] batchRehash(ArrayList<T> keys){
        T[] temp = this.hashTable.clone();        /* copy hashTable and occupied function */
        boolean [] occupied_temp = this.isOccupied.clone() ;

        int oldSize = this.currentInputSize;
        this.sizeOfMaxInput = oldSize + keys.size() ;
        int m = this.sizeOfMaxInput * this.sizeOfMaxInput;
        this.sizeOfHashTable = (int) Math.pow(2 , Math.ceil (Math.log(m) / Math.log(2)));

        this.hasher = new UniversalHasher<T>(this.sizeOfHashTable);

        boolean finished ;  /* for determining if the rehash operation is done */
        int numOfCollisions = 0;

        int number_of_successful_insertions ;
        do{
            number_of_successful_insertions = 0 ;
            finished = true ;
            boolean finished_old = true ;       /* for check if the old elements are done */
            this.hashTable = (T[]) new Object[this.sizeOfHashTable];     /* clear hashTable and isOccupied array */
            this.isOccupied = new boolean[this.sizeOfHashTable];
            Arrays.fill(this.isOccupied, false);
            this.hasher.regenerate();

            for(int i=0 ; i<temp.length ; i++){         /* insert elements that are in the hashTable and if there is collision repeat with new function */
                if(occupied_temp[i]){
                    int index = this.hasher.hash_code(temp[i]) ;
                    if(!this.isOccupied[index]){
                        this.hashTable[index] = temp[i] ;
                        this.isOccupied[index] = true ;
                    }
                    else {
                        finished = false ;          /* collision , create new hash function */
                        numOfCollisions++;
                        finished_old = false ;
                        break ;
                    }
                }
            }
            if(finished_old) {
                for (T key : keys) {
                    int index = this.hasher.hash_code(key);   /* insert element value in the hashable */
                    if (this.isOccupied[index] && this.hashTable[index].equals(key)) {}
                    else if (this.isOccupied[index] && !this.hashTable[index].equals(key)) {
                        finished = false;
                        numOfCollisions++;
                        break;
                    } else {
                        this.hashTable[index] = key;
                        this.isOccupied[index] = true;
                        number_of_successful_insertions++;
                    }
                }
            }
        }while(!finished) ;
        this.number_of_rehashing_times += numOfCollisions ;
        this.currentInputSize += number_of_successful_insertions ;
        return new int[]{this.currentInputSize - oldSize, numOfCollisions};
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


    public int getNumber_of_rehashing_times() {
        return number_of_rehashing_times;
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
}
