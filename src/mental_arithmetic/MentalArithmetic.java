/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mental_arithmetic;

/**
 * The main class of the Mental Arithmetic application.
 *
 * @author Daniel
 */
public class MentalArithmetic {

    /**
     * The main method, which starts the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainDialog test = new MainDialog();
        test.setSize(600, 600);
        test.run();
//        for (int i = 1; i <= 120; i++) {
//            System.out.println(String.format("%s.\t%s", i, createMultiplicationTask()));
//            System.out.println(String.format("%s.\t%s", ++i, createDivisionTask()));
//        }
    }
}
