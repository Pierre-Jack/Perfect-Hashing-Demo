import java.lang.* ;

    /*
        this class calculates the hash code of given integer according to hash function defined to it
    */
    public class Hashing {
        private boolean func[][] ;
        private int b ;
        private int u ;

        /* construcor for intializing hash function and its dims */
        Hashing(boolean [][] arr){
            this.func = arr ;
            this.b = arr.length ;
            this.u = arr[0].length ;
        }

        /* function for calculating hash code for given integer */
        public int hash_code(int element){
            boolean[][] vector = this.evaluate_X_vector(element) ;
            return this.evaluate_index_in_hash_table(vector) ;
        }

        /* converting element to its binary vector
        elements of vector-array could only take 2 values {0 , 1} ,
        for saving space we used boolean array instead of other data types ,
        true refer to 1
        false refer to 0
        */
        private boolean[][] evaluate_X_vector(int element){
            boolean [][] vector = new boolean [this.u][1];
            for (int i=0 ; i<this.u ; i++){
                vector[i][0] = this.getBit(element , i) ;
            }
            return vector ;
        }

        /* getting value of each bit in the number in the binary representation
        true for 1
        false for 0
        */
        private boolean getBit(int number, int position){
            int x ;
            if(position==31)
                x = number | (int)Math.pow(-2,31);
            else
                x =  number | (int)Math.pow(2,position);
            return x==number ;
        }

    /*  Boolean.compare(x ,false) return 1 if x == true
                                  return 0 if x == false
        this function is used for converting boolean to int value
    */

        /* evaluating value of hash code by multiplying hash matrix by element vector */
        private int evaluate_index_in_hash_table (boolean[][] vector){
            int dummy = 0;
            int index =0 ;
            for(int i=0 ; i<this.b ; i++){
                for(int j=0 ; j<this.u ; j++){
                    dummy = dummy + Boolean.compare(this.func[i][j],false) * Boolean.compare(vector[j][0] ,false);
                }
                dummy %= 2 ;
                index += dummy*Math.pow(2,i) ;  /* converting binary representation of index to decimal number */
                dummy = 0 ;
            }
            return index ;
        }
    }
