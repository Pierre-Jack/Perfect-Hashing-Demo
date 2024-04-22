import java.util.Arrays;

public class Perfect_Hashing_NSquare {
    private int sizeOfMaxInput;     /* size of max input , increasing dynamically with insertion operations */
    private int sizeOfHashTable;    /* size of hash table = M = N^2 */
    private int[] hashTable;        /* hash table */
    private boolean[][] func ;      /* array of hash func */
    private int b ;                 /* M = 2 ^ b  , so b = log (M) to the base 2 */
    private int u ;                 /* length of hashed element , 32bit for integer , and so on ...... */
    private Universal_Hash_Family s ;   /* object from the universal hash family */
    private Hashing h ;                 /* object from the Hashing class */
    private int currentInputSize;       /* keep track of number of elements inserted */
    private boolean[] isOccupied;       /* keep track for indexes that is used in hashTable */
    private double load_factor ;        /* load factor for percentage of filling hashTable */

    public Perfect_Hashing_NSquare() {
        this.sizeOfMaxInput = 8;        /* initial Max input */
        this.sizeOfHashTable = this.sizeOfMaxInput*this.sizeOfMaxInput;
        this.b = (int) (Math.log(this.sizeOfHashTable) /Math.log(2) ) ;
        this.u = 32 ;
        this.s = new Universal_Hash_Family(this.b,this.u) ; /* creating universal hash family */
        this.func = this.s.hash_function();
        this.h = new Hashing(this.func);
        this.currentInputSize = 0;
        this.hashTable = new int[this.sizeOfHashTable];
        this.isOccupied = new boolean[this.sizeOfHashTable];
        Arrays.fill(this.isOccupied , false) ;
        this.load_factor = .75 ;
    }

    public void insert(int element){
        int index = this.h.hash_code(element) ;

        System.out.println("elemnt = " + element + " , index = " + index ) ;

        if(this.isOccupied[index] && this.hashTable[index] == element){     /* error is the element is already inserted */
            System.out.println("The element is inserted already") ;
        }
        else {
            this.currentInputSize++;
            if ((double) this.currentInputSize / this.sizeOfMaxInput > this.load_factor) {

                System.out.println("the size will increase") ;

                this.rehash(element , true);       /* increasing size of hashTable */
            } else {
                if (!this.isOccupied[index]) {      /* no corner case , inserted normally */
                    this.hashTable[index] = element;
                    this.isOccupied[index] = true;

                    System.out.println("no problem , will insert") ;

                } else {

                    System.out.println("collision , will re-build") ;

                    this.rehash(element , false);       /* collision , re-build and choosing new hash function */
                }
            }
        }
    }
    private void rehash(int element , boolean load_factor_broken){
        int[] temp = this.hashTable.clone();        /* copy hashTable and occupied function */
        boolean [] occupied_temp = this.isOccupied.clone() ;

        if(load_factor_broken) {
            this.sizeOfMaxInput = this.sizeOfMaxInput * 2;        /* increasing max elements am=nd size of Table */
            this.sizeOfHashTable = this.sizeOfMaxInput * this.sizeOfMaxInput;
            this.b = (int) (Math.log(this.sizeOfHashTable) / Math.log(2));

            this.s = new Universal_Hash_Family(this.b, this.u);       /* generating new hash family */
        }

        boolean finished ;  /* for determining if the rehash operation is done */

        do{
            finished = true ;
            this.hashTable = new int[this.sizeOfHashTable];     /* clear hashTable and isOccupied array */
            this.isOccupied = new boolean[this.sizeOfHashTable];
            Arrays.fill(this.isOccupied, false);

            this.func = this.s.hash_function();         /* generate new hash function */
            this.h = new Hashing(this.func);

            int index = this.h.hash_code(element) ;     /* insert element value in the hashable */
            this.hashTable[index] = element ;
            this.isOccupied[index] = true ;

            System.out.println("elemnt = " + element + " , index = " + index ) ;

            for(int i=0 ; i<temp.length ; i++){         /* insert elements that are in the hashTable and if there is collision repeat with new function */
                if(occupied_temp[i]){
                    index = this.h.hash_code(temp[i]) ;

                    System.out.println("elemnt = " + temp[i] + " , index = " + index ) ;

                    if(!this.isOccupied[index]){
                        this.hashTable[index] = temp[i] ;
                        this.isOccupied[index] = true ;
                    }
                    else {
                        finished = false ;          /* collision , create new hash function */
                        System.out.println("collision , will re-build") ;
                        break ;
                    }
                }
            }
        }while(!finished) ;
    }
    public void delete(int element){
        int index = this.h.hash_code(element);
        if(this.isOccupied[index] && this.hashTable[index] == element){
            this.currentInputSize--;
            this.isOccupied[index] = false;
        }
        else{
            System.out.println("Element not found");    /* error , element not found */
        }
    }

    public boolean search(int element){
        int index = this.h.hash_code(element);
        return this.isOccupied[index];
    }
    public int getCurrentInputSize() {
        return currentInputSize;
    }
    public boolean[][] getFunc() {
        return func;
    }
    public int getB() {
        return b;
    }
    public int getU() {
        return u;
    }
    public int getSizeOfMaxInput() {
        return sizeOfMaxInput;
    }
    public int getSizeOfHashTable() {
        return sizeOfHashTable;
    }
}
