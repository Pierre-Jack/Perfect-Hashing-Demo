public class UniversalHasher<T> extends Hasher<T> {
    private int m;
    private int b;
    private Universal_Hash_Family<T> family;
    private Hashing<T> hashing;

    public UniversalHasher(int m) {
        super(m);
        b = 0;
        while((1 << b) < m) b += 1;

    }

    @Override
    int hash_code(T key) {
        if(family == null || hashing == null) {
            family = new Universal_Hash_Family<T>(key, b);
            hashing = new Hashing<T>(family.hash_function());
        }
        return hashing.hash_code(key);
    }
    public void regenerate(int m){
        this.m = m;
        b = 0;
        while((1 << b) < m) b += 1;
        hashing = new Hashing<T>(family.hash_function());
    }
}
