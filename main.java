import java.util.Scanner;

/**
 * main file. To test polynominal class. 
 */
public class main {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        double[] k = {3, 4.0, 5.0, 2.0};
        Polynominal poll = new Polynominal(k);
        double[] m = {2.0, 4.0, 1.0};
        Polynominal poll2 = new Polynominal(m);
        //System.out.println(poll.getDegree());
        //System.out.println(poll);
        //System.out.println(poll2);
        System.out.println(poll.add(poll2).toString());
        System.out.println(poll.sub(poll2).toString());
        System.out.println(poll.mul(poll2));

    }
    
}