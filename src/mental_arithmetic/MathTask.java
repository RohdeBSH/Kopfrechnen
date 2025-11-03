/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mental_arithmetic;

/**
 * Represents a mathematical task, consisting of two numbers and an operator.
 *
 * @author Daniel
 */
public class MathTask {

    private final int firstNumber;
    private final int secondNumber;
    private final Operator operator;

    /**
     * Constructs a new MathTask.
     *
     * @param firstNumber  the first number in the task
     * @param secondNumber the second number in the task
     * @param operator     the operator to be applied to the numbers
     */
    public MathTask(int firstNumber, int secondNumber, Operator operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
    }

    /**
     * Returns the formula of the task as a string.
     *
     * @return the formula as a string
     */
    public final String getFormula() {
        return String.format("%s    %s    %s    =", this.firstNumber, this.operator, this.secondNumber);
    }

    /**
     * Returns the result of the task.
     *
     * @return the result of the task
     */
    public final int getResult() {
        switch (this.operator) {
            case MULTIPLICATION:
                return this.firstNumber * this.secondNumber;
            case DIVISION:
                return this.firstNumber / this.secondNumber;
            case ADDITION:
                return this.firstNumber + this.secondNumber;
            case SUBTRACTION:
                return this.firstNumber - this.secondNumber;
        }
        return -1;
    }

    /**
     * Creates a new division task.
     *
     * @return a new division task
     */
    public static MathTask createDivisionTask() {
        int firstNumber;
        int secondNumber;

        do {
            firstNumber = Math.round((float) (Math.random() * 1000));
            secondNumber = Math.round((float) (Math.random() * 10));
        } while (!isNaturalNumber((double) firstNumber / secondNumber));

        return new MathTask(firstNumber, secondNumber, Operator.DIVISION);
    }

    private static boolean isNaturalNumber(double numberToCheck) {
        return numberToCheck > 1 && numberToCheck == Math.floor(numberToCheck);
    }

    /**
     * Creates a new multiplication task.
     *
     * @return a new multiplication task
     */
    public static MathTask createMultiplicationTask() {
        int smallNumber = Math.round((float) (Math.random() * 10));
        int largeNumber = Math.round((float) (Math.random() * 1000));

        int firstNumber;
        int secondNumber;
        if (Math.random() >= 0.5) {
            firstNumber = smallNumber;
            secondNumber = largeNumber;
        } else {
            firstNumber = largeNumber;
            secondNumber = smallNumber;
        }

        return new MathTask(firstNumber, secondNumber, Operator.MULTIPLICATION);
    }
}
