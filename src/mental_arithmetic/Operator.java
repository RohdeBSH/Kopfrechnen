package mental_arithmetic;

/**
 * Represents the mathematical operators that can be used in a task.
 *
 * @author Daniel
 */
public enum Operator {
    /**
     * The multiplication operator.
     */
    MULTIPLICATION("x"),
    /**
     * The division operator.
     */
    DIVISION("/"),
    /**
     * The addition operator.
     */
    ADDITION("+"),
    /**
     * The subtraction operator.
     */
    SUBTRACTION("-");

    private final String operatorString;

    private Operator(final String operator) {
        this.operatorString = operator;
    }

    /**
     * Returns the string representation of the operator.
     *
     * @return the string representation of the operator
     */
    @Override
    public String toString() {
        return this.operatorString;
    }


}
