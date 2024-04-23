import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

    /*
        this class define the universal hash family ,
        generate different random hash functions from the hash family
    */
    public class Universal_Hash_Family {

    /* elements of func-array could only take 2 values {0 , 1} ,
    for saving space we used boolean array instead of other data types ,
    true refer to 1
    false refer to 0
    */

        private int row ;
        private int col ;
        private boolean[][] func ;
        private ArrayList<boolean[][]> track = new ArrayList<>() ;

        /* constructor for intializing dim of hash array */
        Universal_Hash_Family(int b , int u){
            this.row = b ;
            this.col = u ;
            this.track.clear(); /* clear the path of hash functions when generating new hash family */
        }

        /* function for returning a different random hash function and saving its value for keeping track of all previous hash functions */
        public boolean[][] hash_function(){
            this.generate_func();
            this.check_repetition();
            this.track.add(this.func.clone());
            return this.func ;
        }


        /* function for generating random hash function */
        private void generate_func(){
            Random random = new Random();
            this.func = new boolean [this.row][this.col] ;
            for(int i=0 ; i< this.row ; i++){
                for(int j=0 ; j< this.col ; j++){
                    this.func[i][j] = random.nextBoolean() ;
                }
            }
        }

        /* function for checking repetition of hash function and re-generate a new hash function if it is repeated */
        private void check_repetition(){
            boolean repeated ;
            do{
                repeated = false;
                for(int i=0; i<this.track.size();i++){
                    if(Arrays.equals(this.track.get(i) , this.func)){
                        repeated = true ;
                        this.generate_func();
                        break ;
                    }
                }
            }while(repeated);
        }

        /* getters */
        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public boolean[][] getFunc(){
            return this.func ;
        }

        public ArrayList<boolean[][]> getTrack (){
            return this.track ;
        }
    }
