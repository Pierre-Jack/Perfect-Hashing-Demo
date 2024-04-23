import java.util.Scanner;

public class Dictionary_control {
    Dictionary dic;
    Dictionary_control(int choice)
    {
        dic = new Dictionary(choice);
    }
    public void perform(int operation)
    {
        Scanner scanner = new Scanner(System.in);
        switch (operation) {
            case 1:
                System.out.print("Enter the word to insert: ");
                String word = scanner.next();
                if (dic.insert(word)[0]) {
                    System.out.println("Word inserted successfully");
                } else {
                    System.out.println("Word already exists");
                }
                break;

            case 2:
            System.out.print("Enter the word to delete: ");
            word = scanner.next();
            if (dic.delete(word)) {
                System.out.println("Word deleted successfully");
            } else {
                System.out.println("Word doesn't exist");
            }
            break;

            case 3:
            System.out.print("Enter the word to search for: ");
            word = scanner.next();
            if (dic.search(word)) {
                System.out.println("Word is found");
            } else {
                System.out.println("Word is not found");
            }
            break;

            case 4:
            System.out.print("Enter the path of the file to insert: ");
            String filePath = scanner.nextLine().trim();
            int count = dic.batchInsert(filePath);
            System.out.println(count + " words inserted successfully");
            break;
            
            case 5:
            System.out.print("Enter the path of the file to delete: ");
            filePath = scanner.nextLine().trim();
            count = dic.batchDelete(filePath);
            System.out.println(count + " words deleted successfully");
            break;

            case 6:
            Main.main(null);

            case 7:
            System.exit(0);


            default:
                System.out.println("Invalid choice");
                Main.operations();
                break;
        }
    }

}
