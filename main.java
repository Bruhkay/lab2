import java.util.Scanner;

/**
 * main file. To test polynominal class. 
 */
public class main {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        double[] k = {1.0, 2.0, 3.0, 0.0, 5};
        Polynominal poll = new Polynominal(k);
        double[] m = {1.0, 2.0, 3.0, 0.0, 5, 6, 7, 8};
        Polynominal poll2 = new Polynominal(m);
        //System.out.println(poll.getDegree());
        System.out.println(poll.toString());
        //System.out.println(poll2);
        System.out.println(poll.add(poll2).toString());
        
        System.out.println(poll.sub(poll2).toString());

    }
    
}