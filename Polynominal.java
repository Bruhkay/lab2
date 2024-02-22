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
     * 
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
    Polynominal mul(Polynominal p2){
        Polynominal result = new Polynominal( 10 + 1 , 0); //should be recover again
        
        for(int i = 0; i< this.coefficientList.size(); i++){
            for(int k = 0; k< p2.coefficientList.size(); k++){
                if(result.coefficientList.get(i+k) == 0){
                    result.coefficientList.set(i+k, this.coefficientList.get(i) * p2.coefficientList.get(k));
                }
                else{
                    double temp = (this.coefficientList.get(i) * p2.coefficientList.get(k)) + result.coefficientList.get(i+k);
                    result.coefficientList.set(i+k, temp);    
                }
            }
        }
        return result;
    }
    
    /**
     * 
     * @param p2
     * @return
     */
    Polynominal compose(Polynominal p2){
        int max = Math.max(this.getDegree(), p2.getDegree());
        Polynominal result = new Polynominal( max, 0);
        Polynominal temp2 = new Polynominal( 0, this.coefficientList.get(0) );
        result.coefficientList.set(0, this.coefficientList.get(0));
        
        for(int i = 1 ; i < this.coefficientList.size(); i++){
            
            if( i == 1){
                temp2 = p2;
            }
            for(int k = 1; k< i ; k++){
                temp2 = p2.mul(p2);
            }

            Polynominal temp3 = new Polynominal( 0, this.coefficientList.get(i) );
            result = result.add(temp2.mul(temp3));            
        }
        return result;
    }
    
    Polynominal div(Polynominal p2){
        Polynominal result = new Polynominal();


        return result;
    }
}