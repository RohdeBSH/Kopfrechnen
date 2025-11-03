package kopfrechnen;

/**
 *
 * @author Daniel
 */
public enum TaskMark {
    MULTIPLICATION("x"),
    DIVISION("/"),
    ADDITION("+"),
    SUBTRACTION("-");

    private final String taskMarkString;

    private TaskMark(final String taskMark) {
        this.taskMarkString = taskMark;
    }

    @Override
    public String toString() {
        return this.taskMarkString;
    }
    
    
}
