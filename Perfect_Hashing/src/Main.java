import java.lang.* ;
import java.util.Scanner;

public class Main {

//    public void print(boolean [][] arr){
//        for(int k=0 ; k<arr.length ; k++){
//            for(int j=0 ; j<arr[0].length ; j++){
//                System.out.print(Boolean.compare(arr[k][j],false)+" ") ;
//            }
//            System.out.println() ;
//        }
//        System.out.println("*******************************************") ;
//    }


    public static void main(String[] args) {


        //cli
        System.out.println("1. O(N2) solution");
        System.out.println("2. O(N) solution");
        System.out.println("3. Exit");
        System.out.print("Choose your hashing method: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        Dictionary_control dictionary_control= new Dictionary_control(choice);
        if(dictionary_control != null)
        {
            System.out.println("Table created successfully");
        }

        while(true)
        {
            System.out.println("Choices");
            System.out.println("1- Insert");
            System.out.println("2- Delete");
            System.out.println("3- Search for");
            System.out.println("4- Batch insert");
            System.out.println("5- Batch delete");
            System.out.println("7. Exit");
            System.out.print("Enter an operation: ");
            int operation = scanner.nextInt();
            dictionary_control.perform(operation);
        }



//        Main x = new Main() ;
//        //int M = 8;
//        int b = 3;
//        int u = 32;
//        Universal_Hash_Family s = new Universal_Hash_Family(b,u);
//        boolean arr [][] = s.hash_function();
//        x.print(arr);
////        Universal_Hash_Family s2 = new Universal_Hash_Family(b,5);
////        boolean arr2 [][] = s2.hash_function();
////        x.print(arr2);
////        for(int i=0 ; i<10 ; i++){
////            boolean arr [][] = s.hash_function();
////            x.print(arr);
////        }
//
//        Hashing h = new Hashing(arr);
//        int [] values = new int [] {10 , 18 , 25 , 39 , 65 , 82 , 111 , -100 } ;
//        for(int i= 0 ; i< values.length ; i++){
//            int index = h.hash_code(values[i]);
//            x.print(h.vector) ;
//            System.out.println(index);
//            System.out.println("-------------------------------------------");
//        }
        Perfect_Hashing_NSquare perfectHashing = new Perfect_Hashing_NSquare();
        perfectHashing.insert(10);
        System.out.println(perfectHashing.search(10));
        perfectHashing.insert(20);
        System.out.println(perfectHashing.search(20));
        perfectHashing.insert(30);
        System.out.println(perfectHashing.search(30));
        perfectHashing.insert(40);
        System.out.println(perfectHashing.search(40));
        perfectHashing.insert(50);
        System.out.println(perfectHashing.search(50));
        perfectHashing.insert(60);
        System.out.println(perfectHashing.search(60));
        perfectHashing.insert(70);
        System.out.println(perfectHashing.search(70));
        perfectHashing.insert(80);
        System.out.println(perfectHashing.search(80));
        perfectHashing.insert(90);
        System.out.println(perfectHashing.search(90));
        perfectHashing.insert(100);
        System.out.println(perfectHashing.search(100));


        perfectHashing.delete(90);
        System.out.println(perfectHashing.search(90));

        perfectHashing.delete(90);
        perfectHashing.insert(80) ;


        System.out.println(perfectHashing.getSizeOfHashTable());
        System.out.println(perfectHashing.getCurrentInputSize());

    }
}
