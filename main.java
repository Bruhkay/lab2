import java.util.Scanner;

/**
 * main file. To test polynominal class. 
 */
public class main {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        double[] k = {10};
        Polynominal poll = new Polynominal(k);
        double[] m = {2,1};
        Polynominal poll2 = new Polynominal(m);
        //System.out.println(poll.getDegree());
        //System.out.println(poll);
        //System.out.println(poll2);
        //System.out.println(poll.add(poll2).toString());
        //System.out.println(poll.sub(poll2).toString());
        //System.out.println(poll.mul(poll2));
        //System.out.println(poll.compose(poll2));
        System.out.println("result: " + poll.div(poll2));
        for(int x: poll.findEqual(poll2)){
            System.out.print(x + " ");
        }


    }
    
}