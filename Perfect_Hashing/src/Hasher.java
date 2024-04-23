public abstract class Hasher<T> {
    int m; // size of hash table

    abstract int hash_code(T key);

    public Hasher(int m) {
        this.m = m;
    }

    abstract void regenerate();

}
