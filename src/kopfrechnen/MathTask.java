/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kopfrechnen;

/**
 *
 * @author Daniel
 */
public class MathTask {

    private final int firstNumber;
    private final int secondNumber;
    private final TaskMark taskMark;

    public MathTask(int firstNumber, int secondNumber, TaskMark taskMark) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.taskMark = taskMark;
    }

    public final String getFormular() {
        return String.format("%s    %s    %s    =", this.firstNumber, this.taskMark, this.secondNumber);
    }

    public final int getResult() {
        switch (this.taskMark) {
            case MULTIPLICATION:
                return this.firstNumber*this.secondNumber;
            case DIVISION:
                return this.firstNumber/this.secondNumber;
            case ADDITION:
                return this.firstNumber+this.secondNumber;
            case SUBTRACTION:
                return this.firstNumber-this.secondNumber;
        }
        return -1;
    }

    public static MathTask createDivisionTask() {
        int firstNumber;
        int secondNumber;

        do {
            firstNumber = Math.round((float) (Math.random() * 1000));
            secondNumber = Math.round((float) (Math.random() * 10));
        } while (!checkNaturalNumber((double) firstNumber / secondNumber));

        return new MathTask(firstNumber, secondNumber, TaskMark.DIVISION);
    }

    private static boolean checkNaturalNumber(double number2check) {
        return number2check > 1 && number2check == Math.floor(number2check);
    }

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

        return new MathTask(firstNumber, secondNumber, TaskMark.MULTIPLICATION);
    }
}
