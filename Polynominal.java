/**@author Burkay Tunçtürk ID: 22201710 Date: 13.02.2024
 * i created this algorithm in order to construct polynominals and calculate its values
*/
import java.util.ArrayList;

/**
 * Polynominal
 */

public class Polynominal {

    private ArrayList<Double> coefficientList = new ArrayList<Double>();

    Polynominal(int power , double coefficient ){
        for(int i = 0 ; i<power; i++){
            coefficientList.add(0.0);
        }
        coefficientList.add(coefficient);
    }
    /**
     * our most useful constructor
     * @param coef is a set of double numbers
     */
    Polynominal(double[] coef){
        for(double x : coef){
                coefficientList.add(x);
        }
    }    

    Polynominal(){ // its for null polynominals

        coefficientList.add(0.0);
    }
    /**
     * @return degree of polynominal
     */
    int getDegree(){
        return coefficientList.size() -1;
    }

    @Override
    public String toString() {
        String wholePol ="";

        if(coefficientList.get(0) != 0.0){
            wholePol = "" + coefficientList.get(0) ;
        }
        
        for(int i = 1; i < coefficientList.size(); i++){
            if(coefficientList.get(i) != 0.0){    
                if(coefficientList.get(i) >= 0){ // sign changer
                    wholePol +=" +";
                }
                else{
                    wholePol +=" -";
                }
                wholePol += Math.abs(coefficientList.get(i)) + "x^" + i;
            }
        }

        if(wholePol.equals("")){ //it handles exception for ex being null.
            return "0";
        }
        
        return wholePol;
    }
    /**
     * This is ordinary evaluation method 
     * @param x is the value that user entered
     * @return it returns evaluation of current equasion according to x value
     */
    
    double eval(double x){
        
        double result = 0.0;
        for(int i = 0; i < coefficientList.size(); i++){
            result += coefficientList.get(i) * Math.pow(x, i) ;
        }
        return result;
    }
    /**
     * This is a type of evaluation method that is called Horner’s method 
     * this is said to be an efficient way of evaluating equasions
     * @param x is the value that user entered
     * @return it returns evaluation of current equasion according to x value
     */
    double eval2(double x){
        double result = 0.0;
        for(int i = coefficientList.size()-1; i >= 0; i--){
            result += coefficientList.get(i) ;
            result = result* x;
        }
        return result / x ;
    }
    /**
     * this method calculates the sum of the given 2 polynominal
     * @param p2 is the polynominal that we have took from the user
     * @return the returning value is the sum of the 2 polynominal
     */
    Polynominal add(Polynominal p2){
        int max = Math.max(this.getDegree(), p2.getDegree());
        Polynominal result = new Polynominal(0,this.coefficientList.get(0) + p2.coefficientList.get(0));
        for (int i = 1; i <= max; i++) {
            if(i < this.coefficientList.size() && p2.coefficientList.size() > i){    
                result.coefficientList.add(this.coefficientList.get(i) + p2.coefficientList.get(i));
            }
            else if(i >= this.coefficientList.size()){
                result.coefficientList.add( p2.coefficientList.get(i) );
            }   
            else if(i >= p2.coefficientList.size()){
                result.coefficientList.add( this.coefficientList.get(i) );
            }  
        }
        return result;
    }
    /**
     * this method is subtracting the given polynominal from main polynominal
     * @param p2 is the polynominal that we have took from the user
     * @return the result of substraction
     */
    Polynominal sub(Polynominal p2){
        int max = Math.max(this.getDegree(), p2.getDegree());
        Polynominal result = new Polynominal(0,this.coefficientList.get(0) - p2.coefficientList.get(0));
        for (int i = 1; i <= max; i++) {
            if(i < this.coefficientList.size() && p2.coefficientList.size() > i){    
                result.coefficientList.add(this.coefficientList.get(i) - p2.coefficientList.get(i));
            }
            else if(i >= this.coefficientList.size()){
                result.coefficientList.add( -p2.coefficientList.get(i) );
            }   
            else if(i >= p2.coefficientList.size()){
                result.coefficientList.add( this.coefficientList.get(i) );
            }  
        }
        return result;
    }
    /**
     * this method calculates multiple of given 2 polynominal
     * @param p2 is the polynominal that we have took from the user
     * @return multiple of given 2 polynominal
     */
    Polynominal mul(Polynominal p2){
        Polynominal result = new Polynominal( this.getDegree() + p2.getDegree() , 0); 
        
        for(int i = 0; i< this.coefficientList.size(); i++){
            for(int k = 0; k< p2.coefficientList.size(); k++){
                if(result.coefficientList.get(i+k) == 0){ //this checks whether this degree is already calculated or not.
                    result.coefficientList.set(i+k, this.coefficientList.get(i) * p2.coefficientList.get(k));
                }
                //If its calculated, then we add additional coefficients on it
                else{ 
                    double temp = (this.coefficientList.get(i) * p2.coefficientList.get(k)) + result.coefficientList.get(i+k);
                    result.coefficientList.set(i+k, temp);    
                }
            }
        }
        return result;
    }
    
    /**
     * composes the polynominal
     * @param p2 is the polynominal that we have took from the user
     * @return composed polynominal
     */
    Polynominal compose(Polynominal p2){
        int max = Math.max(this.getDegree(), p2.getDegree());
        Polynominal result = new Polynominal( max, 0);
        Polynominal temp2 = new Polynominal( 0, this.coefficientList.get(0) );
        result.coefficientList.set(0, this.coefficientList.get(0));
        
        for(int i = 1 ; i < this.coefficientList.size(); i++){
            
            if( i == 1){ //if there are coefficient with degree 1
                temp2 = p2;
            }

            for(int k = 1; k< i ; k++){ //this multiplies the p2 polynominal according to degree of main polynominal
                temp2 = p2.mul(p2);
            }

            Polynominal temp3 = new Polynominal( 0, this.coefficientList.get(i) ); // this is for constants
            result = result.add(temp2.mul(temp3));            
        }
        return result;
    }

    /**
     * 
     * @param p2 is the polynominal that we have took from the user
     * @return quotient of the division of two polynominal
     */
    Polynominal div(Polynominal p2){ 
        double[] k = {3, 4.0, 1.0, 3, 0, 2};
        Polynominal main = new Polynominal(k);
        Polynominal result = new Polynominal();
        int bigLeading = main.getDegree();
        int smallLeading = p2.getDegree();
        boolean isTerminated = false;

        while(!isTerminated){ //this will repeated until bigLeading become smaller than smallLeading
            Polynominal temp = new Polynominal(bigLeading-smallLeading, 
            (int)(main.coefficientList.get(bigLeading)/ p2.coefficientList.get(smallLeading))); 
            main = main.sub(p2.mul(temp));
            result = result.add(temp);
            bigLeading--;
            if(bigLeading < smallLeading){
                isTerminated = true;
            }
        }
        return result;
    }

    /**
     * 
     * @param p2 is the polynominal that we have took from the user
     * @return this method returns the common roots of polynominals
     */
    int[] findEqual(Polynominal p2){

        ArrayList<Integer> temp = new ArrayList<Integer>();

        for(int i = 1 ; i <= 200; i++){
            if(this.eval(i) == p2.eval(i)){
                temp.add(i);
            }
        }
        int[] result = new int[temp.size()];
        for (int k = 0; k< temp.size(); k++) {
            result[k] = temp.get(k);
        }
        return result;
    }
}