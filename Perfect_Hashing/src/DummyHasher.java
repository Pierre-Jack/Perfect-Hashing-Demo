import java.util.Random;

public class DummyHasher<T> extends Hasher<T> {
    int random;

    @Override
    public int hash_code(T key) {
        return Math.abs((key.hashCode()*random)) % m;
    }


    public DummyHasher(int m) {
        super(m);
        Random rand = new Random();
    }
    public void regenerate(){
        Random rand = new Random();
    }

}
