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
    
    public static void main(String[] args) {


        //cli
        System.out.println("****HASHING METHODS****");
        System.out.println("1. O(N2) solution");
        System.out.println("2. O(N) solution");
        System.out.print("Choose your hashing method: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice==1 || choice==2)
        {
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
        }
        else
        {
            System.out.println("invalid choice");
            Main.main(args);
        }    
    }
}
