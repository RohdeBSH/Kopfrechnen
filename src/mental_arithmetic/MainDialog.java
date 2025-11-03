package mental_arithmetic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main dialog of the Mental Arithmetic application.
 * It provides the user interface for the arithmetic game.
 */
public class MainDialog extends JDialog {

    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JPanel componentsPanel = new JPanel(new GridBagLayout());
    private final JLabel formulaLabel = new JLabel("A    x    B    =");
    private final JTextField resultTextField = new JTextField(10);
    private final JButton startButton = new JButton("Start");
    private JLabel timerLabel = new JLabel("10:00");
    private MathTask currentTask;
    private Timer timer = new Timer(1000, null);
    private int countdown = 10 * 60;
    private JLabel taskCountLabel = new JLabel("0/0");
    private int tasksCorrect = 0;
    private int tasksTotal = 0;

    /**
     * Constructs a new MainDialog.
     */
    public MainDialog() {
        super();
        initComponents();
        initActionListeners();
    }

    /**
     * Runs the dialog by setting its title, packing it, and making it visible.
     */
    public void run() {
        this.setTitle("Mental Arithmetic");
        this.pack();
        this.setVisible(true);
    }

    private void initActionListeners() {
        this.resultTextField.addActionListener(this::checkResult);
        this.startButton.addActionListener(this::startAction);
    }

    private void initComponents() {
        //timer
        timer.addActionListener(new ActionListener() {
            int secondsLeft = countdown;

            public void actionPerformed(ActionEvent e) {
                int min = secondsLeft / 60;
                int sec = secondsLeft % 60;
                timerLabel.setText(String.format("%02d:%02d", min, sec));
                secondsLeft--;
                if (secondsLeft < 0) {
                    timer.stop();
                    timerLabel.setText("Time's up!");
                }
            }
        });

        //GUI        
        JPanel content = new JPanel(new BorderLayout(10, 10));

        this.gbc.insets = new Insets(4, 4, 4, 4);
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        //first
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.gridwidth = 1;
        this.componentsPanel.add(this.formulaLabel, gbc);
        //result
        this.resultTextField.setEditable(false);
        this.resultTextField.setEnabled(false);
        this.gbc.gridx++;
        this.componentsPanel.add(this.resultTextField, gbc);

        content.add(this.componentsPanel, BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.add(this.startButton);
        top.add(timerLabel);
        top.add(taskCountLabel);
        content.add(top, BorderLayout.NORTH);

        setContentPane(content);

        // Close operation
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void checkResult(ActionEvent e) {
        if (!this.timer.isRunning()) {
            this.startButton.setEnabled(true);
            return;
        }
        //check result
//        if (this.currentTask.getResult() != Integer.parseUnsignedInt(this.resultTextField.getText().trim())) {
//            //mark wrong
//            this.resultTextField.setBackground(Color.red);
//        } else {
        final String inputText = this.resultTextField.getText().trim();
        if (!inputText.isEmpty() && this.currentTask.getResult() == Integer.parseUnsignedInt(this.resultTextField.getText().trim())) {
            this.taskCountLabel.setText(String.format("%03d%03d", this.tasksCorrect++, this.tasksTotal));
        }
        //news task
        this.taskCountLabel.setText(String.format("%03d/%03d", this.tasksCorrect, this.tasksTotal++));
        this.resultTextField.setBackground(Color.WHITE);
        this.resultTextField.setText("");
        if (Math.random() >= 0.5) {
            this.currentTask = MathTask.createDivisionTask();
        } else {
            this.currentTask = MathTask.createMultiplicationTask();
        }
        this.formulaLabel.setText(this.currentTask.getFormula());
//        }
    }

    private void startAction(ActionEvent e) {
        this.tasksCorrect = 0;
        this.tasksTotal = 0;
        this.tasksTotal++;
        this.taskCountLabel.setText(String.format("%03d/%03d", this.tasksCorrect, this.tasksTotal));
        this.startButton.setEnabled(false);
        this.timerLabel.setText("0");
        this.timer.restart();
        this.currentTask = MathTask.createMultiplicationTask();
        this.formulaLabel.setText(currentTask.getFormula());
        this.resultTextField.setEditable(true);
        this.resultTextField.setEnabled(true);
    }
}
