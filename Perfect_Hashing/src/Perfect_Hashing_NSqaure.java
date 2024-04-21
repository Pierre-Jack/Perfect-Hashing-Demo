import java.util.Arrays;

public class Perfect_Hashing_NSqaure {
    private int sizeOfMaxInput;
    private int sizeOfHashTable;
    private int[] hashTable;
    private boolean[][] func ;
    private int b ;
    private int u ;
    Universal_Hash_Family s = new Universal_Hash_Family(3,32); //Universal_Hash_Family s = new Universal_Hash_Family(b
    private int currentInputSize;
    private boolean[] isOccupied;
    private Hashing h ;

    public Perfect_Hashing_NSqaure(int sizeOfMaxInput) {
        this.sizeOfMaxInput = sizeOfMaxInput;
        this.sizeOfHashTable = sizeOfMaxInput*sizeOfMaxInput;
        this.func = s.hash_function();
        this.h = new Hashing(func);
        this.currentInputSize = 0;
        this.hashTable = new int[sizeOfHashTable];
        this.isOccupied = new boolean[sizeOfHashTable];
    }

    public void insert(int element){
        currentInputSize++;
        double loadFactor = 0.75;
        if((double) currentInputSize /sizeOfHashTable > loadFactor){
            rehash(element);
        }
        else{
            int index = h.hash_code(element);
            if(!isOccupied[index]){
                hashTable[index] = element;
                isOccupied[index] = true;
            }
            else{
                rebuildIfCollision(element);
            }

        }
        System.out.println("element:" + element + " index:" + h.hash_code(element) );
    }

    public void rebuildIfCollision(int element){
        func = s.hash_function();
        h = new Hashing(func);
        Arrays.fill(isOccupied, false);
        int[] temp = hashTable.clone(); // Copy the existing hash table
        //hashTable = new int[sizeOfHashTable];
        for(int i = 0; i < temp.length; i++){
            int index = h.hash_code(element);
            if(!isOccupied[index]){
                hashTable[index] = element;
                isOccupied[index] = true;
            }
            else{
                rebuildIfCollision(temp[i]);
            }
        }
        insert(element); // Insert the new element
    }

    public void delete(int element){
        currentInputSize--;
        int index = h.hash_code(element);
        if(hashTable[index] == element){
            hashTable[index] = 0;
            isOccupied[index] = false;
        }
        else{
            //element not found
            System.out.println("Element not found");
        }
    }

    public boolean search(int element){
        int index = h.hash_code(element);
        return hashTable[index] == element;
    }

    public void rehash(int element){
//        int[] temp = hashTable;
//        sizeOfMaxInput = sizeOfMaxInput*2;
//        sizeOfHashTable = sizeOfMaxInput*sizeOfMaxInput;
//        hashTable = new int[sizeOfHashTable];
//        isOccupied = new boolean[sizeOfHashTable];
//        Arrays.fill(isOccupied, false);
//        for(int i = 0; i < temp.length; i++){
//                insert(temp[i]);
//        }
        //insert(element);
        int[] temp = hashTable.clone();
        sizeOfMaxInput = sizeOfMaxInput*2;
        sizeOfHashTable = sizeOfMaxInput*sizeOfMaxInput;
        hashTable = new int[sizeOfHashTable];
        isOccupied = new boolean[sizeOfHashTable];
        Arrays.fill(isOccupied, false);
        for(int i = 0; i < temp.length; i++){
            int index = h.hash_code(element);
            if(!isOccupied[index]){
                hashTable[index] = element;
                isOccupied[index] = true;
            }
            else{
                rebuildIfCollision(temp[i]);
            }
        }
        insert(element);
    }

    public int getCurrentInputSize() {
        return currentInputSize;
    }

    public void setCurrentInputSize(int currentInputSize) {
        this.currentInputSize = currentInputSize;
    }

    public boolean[][] getFunc() {
        return func;
    }

    public void setFunc(boolean[][] func) {
        this.func = func;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getSizeOfMaxInput() {
        return sizeOfMaxInput;
    }

    public void setSizeOfMaxInput(int sizeOfMaxInput) {
        this.sizeOfMaxInput = sizeOfMaxInput;
    }

    public int getSizeOfHashTable() {
        return sizeOfHashTable;
    }

    public void setSizeOfHashTable(int sizeOfHashTable) {
        this.sizeOfHashTable = sizeOfHashTable;
    }

    public static void main(String[] args){
        Perfect_Hashing_NSqaure perfectHashing = new Perfect_Hashing_NSqaure(10);
        perfectHashing.insert(10);
     //   System.out.println(perfectHashing.search(10));
        perfectHashing.insert(20);
        //System.out.println(perfectHashing.search(10));
        perfectHashing.insert(30);
       // System.out.println(perfectHashing.search(10));
        perfectHashing.insert(40);
        //System.out.println(perfectHashing.search(10));
        perfectHashing.insert(50);
        //System.out.println(perfectHashing.search(10));
        perfectHashing.insert(60);
       // System.out.println(perfectHashing.search(10));
        perfectHashing.insert(70);
       // System.out.println(perfectHashing.search(10));
        perfectHashing.insert(80);
       // System.out.println(perfectHashing.search(10));
        perfectHashing.insert(90);
      //  System.out.println(perfectHashing.search(10));
        perfectHashing.insert(100);
////        System.out.println(perfectHashing.search(10));
//        perfectHashing.insert(110);
////        System.out.println(perfectHashing.search(10));
//        perfectHashing.insert(120);
////        System.out.println(perfectHashing.search(10));
//        perfectHashing.insert(130);
////        System.out.println(perfectHashing.search(10));
//        perfectHashing.insert(140);
////        System.out.println(perfectHashing.search(10));
//        perfectHashing.insert(150);
        System.out.println(perfectHashing.search(10));

//        perfectHashing.delete(90);
//
//        System.out.println(perfectHashing.search(90));
//        System.out.println(perfectHashing.getSizeOfHashTable());
//        for(int i = 0; i < perfectHashing.getSizeOfHashTable(); i++){
//            System.out.println(perfectHashing.hashTable[i]);
//        }



    }

}
