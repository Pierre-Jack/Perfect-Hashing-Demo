import java.util.Random;

public class DummyHasher<T> extends Hasher<T> {
    int random;

    @Override
    public int hash_code(T key) {
        return (key.hashCode()*random) % m;
    }


    public DummyHasher(int m) {
        super(m);
        Random rand = new Random();
        this.random = rand.nextInt();
    }
    public void regenerate(){
        Random rand = new Random();
        this.random = rand.nextInt();
    }

}
