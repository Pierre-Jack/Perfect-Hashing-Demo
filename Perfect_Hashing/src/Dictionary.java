import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Dictionary {
    PerfectHashTable<String> pf;

    Dictionary(int choice)
    {
        if (choice==1)
        {
            pf = new Perfect_Hashing_NSquare();
        }
        else if (choice==2)
        {
             pf = new Perfect_Hashing_N();
        }
    }
    

    public boolean[] insert(String word)
    {
        return this.pf.insert(word);
    }

    public boolean delete(String word)
    {
        return this.pf.delete(word);
    }

    public boolean search(String word)
    {
        return this.pf.search(word);
    }
    void batchInsert(String filePath){
        int count = 0 ;
        ArrayList<String> stringList = new ArrayList<>();
        try
        {
            File file = new File(filePath);
            Scanner scanFile = new Scanner(file);
            while(scanFile.hasNextLine())
            {
                stringList.add(scanFile.nextLine());
            }
            count = this.pf.batchInsert(stringList)[0];
            System.out.println(count + " words inserted successfully");
            System.out.println((stringList.size()-count) +" words are already exist");

        }catch (FileNotFoundException e){
            System.out.println(filePath + "is not found");
        }
        
    }

    void batchDelete(String filePath){
        int count = 0 ;
        ArrayList<String> stringList = new ArrayList<>();
        try
        {
            File file = new File(filePath);
            Scanner scanFile = new Scanner(file);
            while(scanFile.hasNextLine())
            {
                stringList.add(scanFile.nextLine());
            }
            count = this.pf.batchDelete(stringList);
            System.out.println(count + " words deleted successfully");
            System.out.println((stringList.size()-count) +" words don't exist");
        }catch (FileNotFoundException e){
            System.out.println("not found");
        }
        
    }
}
