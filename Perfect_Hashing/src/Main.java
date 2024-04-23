import java.lang.* ;
import java.util.Scanner;

public class Main {
    public static void operations()
    {
        System.out.println();
        System.out.println("****OPERATION MENU****");
        System.out.println("1- Insert");
        System.out.println("2- Delete");
        System.out.println("3- Search for");
        System.out.println("4- Batch insert");
        System.out.println("5- Batch delete");
        System.out.println("6- back");
        System.out.println("7- Exit");
        System.out.print("Enter an operation: ");
    }

/*    public void print(boolean [][] arr){
        for(int k=0 ; k<arr.length ; k++){
            for(int j=0 ; j<arr[0].length ; j++){
                System.out.print(Boolean.compare(arr[k][j],false)+" ") ;
            }
            System.out.println() ;
        }
        System.out.println("*******************************************") ;
    }*/

    public static void main(String[] args) {


        //cli
        System.out.println("****HASHING METHODS****");
        System.out.println("1. O(N2) solution");
        System.out.println("2. O(N) solution");
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
            operations();
            int operation = scanner.nextInt();
            dictionary_control.perform(operation);
        }



/*
        Main x = new Main() ;
        int b = 3;
        Universal_Hash_Family<Character> s = new Universal_Hash_Family<>(Character.class , b);
        boolean arr [][] = s.hash_function();
        x.print(arr);

*/



/*
        Hashing<Integer> h = new Hashing<>(arr);
        int [] values = new int [] {10 , 18 , 25 , 39 , 65 , 82 , 111 , -100 , -2147483648 } ;
        for(int i= 0 ; i< values.length ; i++){
            int index = h.hash_code(values[i]);
            x.print(h.vector) ;
            System.out.println(index);
            System.out.println("-------------------------------------------");
        }

*/

/*
        Hashing<Byte> h = new Hashing<>(arr);
        byte [] values = new byte [] {10 , 18 , 25 , 39 , 65 , 82 , 111 , -100 , -128 } ;
        for(int i= 0 ; i< values.length ; i++){
            int index = h.hash_code(values[i]);
            x.print(h.vector) ;
            System.out.println(index);
            System.out.println("-------------------------------------------");
        }

*/


/*
        Hashing<Short> h = new Hashing<>(arr);
        short [] values = new short [] {10 , 18 , 25 , 39 , 65 , 82 , 111 , -100 , -32768} ;
        for(int i= 0 ; i< values.length ; i++){
            int index = h.hash_code(values[i]);
            x.print(h.vector) ;
            System.out.println(index);
            System.out.println("-------------------------------------------");
        }

*/


/*
        Hashing<Long> h = new Hashing<>(arr);
        long [] values = new long [] {1l , 16l , 128l , 1024l , 32768l ,  2147483648l , 9223372036854775807l , -9223372036854775808l} ;
        for(int i= 0 ; i< values.length ; i++){
            int index = h.hash_code(values[i]);
            x.print(h.vector) ;
            System.out.println(index);
            System.out.println("-------------------------------------------");
        }
*/



/*
        Hashing<Character> h = new Hashing<>(arr);
        char [] values = new char [] {'H' , 'E' , 'L' , 'L' , 'O' , ' '} ;
        */
/* H = 72
           E = 69
           L = 76
           L = 76
           O = 79
           SPACE = 32
         *//*


        for(int i= 0 ; i< values.length ; i++){
            int index = h.hash_code(values[i]);
            x.print(h.vector) ;
            System.out.println(index);
            System.out.println("-------------------------------------------");
        }

*/


/*
        Hashing<String> h = new Hashing<>(arr);

            int index = h.hash_code("HelloWorld");
            */
/*
             H = 72
             e = 101
             l = 108
             l = 108
             o = 111
             W = 87
             o = 111
             r = 114
             l = 108
             d = 100
             *//*

            x.print(h.vector) ;
            System.out.println(index);
            System.out.println("-------------------------------------------");
*/


/*
            Hashing<String> h = new Hashing<>(arr);

            int index = h.hash_code("Hello");
            */
/*
             H = 72
             e = 101
             l = 108
             l = 108
             o = 111
             *//*

            x.print(h.vector) ;
            System.out.println(index);
            System.out.println("-------------------------------------------");

*/

    }
}
