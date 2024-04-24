
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Perfect_Hashing_NTest {

    @Test
    void testInsert() {
        Perfect_Hashing_N perfect_hashing_n = new Perfect_Hashing_N();
        boolean[] result = perfect_hashing_n.insert("hello");
        boolean[] expected = {true, true};
        assertArrayEquals(expected, result);
    }

    @Test
    void testSearch() {
        Perfect_Hashing_N perfect_hashing_n = new Perfect_Hashing_N();
        perfect_hashing_n.insert("hello");
        boolean result = perfect_hashing_n.search("hello");
        assertTrue(result);
    }

    @Test
    void testDelete() {
        Perfect_Hashing_N perfect_hashing_n = new Perfect_Hashing_N();
        perfect_hashing_n.insert("hello");
        boolean result = perfect_hashing_n.delete("hello");
        assertTrue(result);
    }

    @Test
    void testBatchInsert() {
        Perfect_Hashing_N perfect_hashing_n = new Perfect_Hashing_N();
//        perfect_hashing_n.insert("hello");
//        perfect_hashing_n.insert("world");
//        perfect_hashing_n.insert("java");
//        perfect_hashing_n.insert("python");
//        perfect_hashing_n.insert("c++");
//        perfect_hashing_n.insert("c#");
//        perfect_hashing_n.insert("ruby");
//        perfect_hashing_n.insert("javascript");
//        perfect_hashing_n.insert("html");
//        perfect_hashing_n.insert("css");
//        perfect_hashing_n.insert("php");
//        perfect_hashing_n.insert("sql");
//        perfect_hashing_n.insert("mysql");
//        perfect_hashing_n.insert("mongodb");
//        perfect_hashing_n.insert("oracle");
//        perfect_hashing_n.insert("postgresql");
        ArrayList<String> keys = new ArrayList<>();
        keys.add("hello");
        keys.add("world");
        keys.add("java");
        keys.add("python");
        keys.add("c++");
        keys.add("c#");
        keys.add("ruby");
        keys.add("javascript");
        keys.add("html");
        keys.add("css");
        keys.add("php");
        keys.add("sql");
        keys.add("mysql");
        keys.add("mongodb");
        keys.add("oracle");
        keys.add("postgresql");
        int[] result = perfect_hashing_n.batchInsert(keys);
        int[] expected = {keys.size(), 0};
        assertArrayEquals(expected, result);
    }

    @Test
    void testBatchDelete() {
        Perfect_Hashing_N perfect_hashing_n = new Perfect_Hashing_N();
        perfect_hashing_n.insert("hello");
        perfect_hashing_n.insert("world");
        perfect_hashing_n.insert("java");
        perfect_hashing_n.insert("python");
        perfect_hashing_n.insert("c++");
        perfect_hashing_n.insert("c#");
        perfect_hashing_n.insert("ruby");
        perfect_hashing_n.insert("javascript");
        perfect_hashing_n.insert("html");
        perfect_hashing_n.insert("css");
        perfect_hashing_n.insert("php");
        perfect_hashing_n.insert("sql");
        perfect_hashing_n.insert("mysql");
        perfect_hashing_n.insert("mongodb");
        perfect_hashing_n.insert("oracle");
        perfect_hashing_n.insert("postgresql");
        ArrayList<String> keys = new ArrayList<>();
        keys.add("hello");
        keys.add("world");
        keys.add("java");
        keys.add("python");
        keys.add("c++");
        keys.add("c#");
        keys.add("ruby");
        keys.add("javascript");
        keys.add("html");
        keys.add("css");
        keys.add("php");
        keys.add("sql");
        keys.add("mysql");
        keys.add("mongodb");
        keys.add("oracle");
        keys.add("postgresql");
        int result = perfect_hashing_n.batchDelete(keys);
        int expected = keys.size();
        assertEquals(expected, result);
    }

    @Test
    void testAllocatedSize() {
        Perfect_Hashing_N perfect_hashing_n = new Perfect_Hashing_N();
        ArrayList<String> keys = new ArrayList<>();
        keys.add("hello");
        keys.add("world");
        keys.add("java");
        keys.add("python");
        keys.add("c++");
        keys.add("c#");
        keys.add("ruby");
        keys.add("javascript");
        keys.add("html");
        keys.add("css");
        keys.add("php");
        keys.add("sql");
        keys.add("mysql");
        keys.add("mongodb");
        keys.add("oracle");
        keys.add("postgresql");
        perfect_hashing_n.batchInsert(keys);
        int result = perfect_hashing_n.getAllocatedSize();
        int expected = keys.size();
        assertTrue(result <= 10*expected);
    }

}