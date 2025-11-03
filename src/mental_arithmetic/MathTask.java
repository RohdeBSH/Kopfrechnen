package mental_arithmetic;

import java.util.Random;

/**
 * Represents a mathematical task, consisting of two numbers and an operator.
 *
 * @author Daniel
 */
public class MathTask {

    private static final int MAX_MULTIPLICATION_FACTOR = 10;
    private static final int MAX_RESULT = 1000;
    private final int firstNumber;
    private final int secondNumber;
    private final Operator operator;
    private static final Random numberGenerator = new Random();

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
        int result = numberGenerator.nextInt(MAX_RESULT + 1);
        int secondNumber = numberGenerator.nextInt(MAX_MULTIPLICATION_FACTOR) + 1; // Avoid division by zero
        int firstNumber = result * secondNumber;

        return new MathTask(firstNumber, secondNumber, Operator.DIVISION);
    }

    /**
     * Creates a new multiplication task.
     *
     * @return a new multiplication task
     */
    public static MathTask createMultiplicationTask() {
        int smallNumber = numberGenerator.nextInt(MAX_MULTIPLICATION_FACTOR + 1);
        int largeNumber = numberGenerator.nextInt(MAX_RESULT + 1);

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
