import java.lang.* ;

public class Main {

    public void print(boolean [][] arr){
        for(int k=0 ; k<arr.length ; k++){
            for(int j=0 ; j<arr[0].length ; j++){
                System.out.print(Boolean.compare(arr[k][j],false)+" ") ;
            }
            System.out.println() ;
        }
        System.out.println("*******************************************") ;
    }


//    public static void main(String[] args) {
//
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
//    }
}

