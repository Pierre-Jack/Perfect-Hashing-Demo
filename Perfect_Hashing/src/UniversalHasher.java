public class UniversalHasher<T> extends Hasher<T>{
    private int m;
    private int b;
    private Universal_Hash_Family<T> family;
    private Hashing<T> hashing;
    private boolean [][] func ;

    public UniversalHasher(int m) {
        super(m);
        this.b = (int) (Math.log(m) /Math.log(2) ) ;
        //System.out.println("b= " + this.b) ;
    }

    @Override
    int hash_code(T key) {
        if(family == null || hashing == null) {
            family = new Universal_Hash_Family<T>(key, b);
            this.func = family.hash_function();
            hashing = new Hashing<T>(this.func);
        }
        return hashing.hash_code(key);
    }
    public void regenerate(){
        this.func = family.hash_function();
        hashing = new Hashing<T>(this.func);
    }

    public boolean[][] getFunc() {
        return func;
    }
}
