public class UniversalHasher<T> extends Hasher<T> {
    private int m;
    private Universal_Hash_Family<T> family;
    private Hashing<T> hashing;

    public UniversalHasher(int m) {
        super(m);
    }

    @Override
    int hash_code(T key) {
        if(family == null || hashing == null) {
            family = new Universal_Hash_Family<T>(key, (int) Math.log(m));
            hashing = new Hashing<T>(family.hash_function());
        }
        return hashing.hash_code(key);
    }
}
