package kopfrechnen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Einfacher Dialog mit zwei Eingabefeldern und einem "Berechne"-Button.
 * Standardverhalten: die beiden Werte werden als double gelesen und addiert. Du
 * kannst die Berechnung in computeResult(...) anpassen.
 */
public class MainDialog extends JDialog {

    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JPanel componentsPanel = new JPanel(new GridBagLayout());
    private final JLabel formular = new JLabel("A    x    B    =");
    private final JTextField result = new JTextField(10);
    private final JButton start = new JButton("Start");
    private JLabel timerLabel = new JLabel("10:00");
    private MathTask currentTask;
    private Timer timer = new Timer(1000, null);
    private int countdown = 10 * 60;
    private JLabel countTask = new JLabel("0/0");
    private int taskCorrect = 0;
    private int taskTotal = 0;

    public MainDialog() {
        super();
        initComponents();
        initConnections();
    }

    public void run() {
        this.setTitle("Kopfrechnen");
        this.pack();
        this.setVisible(true);
    }

    private void initConnections() {
        this.result.addActionListener(this::checkResult);
        this.start.addActionListener(this::clickStart);
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
                    timerLabel.setText("Zeit abgelaufen!");
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
        this.componentsPanel.add(this.formular, gbc);
        //result
        this.result.setEditable(false);
        this.result.setEnabled(false);
        this.gbc.gridx++;
        this.componentsPanel.add(this.result, gbc);

        content.add(this.componentsPanel, BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.add(this.start);
        top.add(timerLabel);
        top.add(countTask);
        content.add(top, BorderLayout.NORTH);

        setContentPane(content);

        // Close operation
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void checkResult(ActionEvent e) {
        if (!this.timer.isRunning()) {
            this.start.setEnabled(true);
            return;
        }
        //check result
//        if (this.currentTask.getResult() != Integer.parseUnsignedInt(this.result.getText().trim())) {
//            //mark wrong
//            this.result.setBackground(Color.red);
//        } else {
        final String inputText = this.result.getText().trim();
        if (!inputText.isEmpty() && this.currentTask.getResult() == Integer.parseUnsignedInt(this.result.getText().trim())) {
            this.countTask.setText(String.format("%03d%03d", this.taskCorrect++, this.taskTotal));
        }
        //news task
        this.countTask.setText(String.format("%03d/%03d", this.taskCorrect, this.taskTotal++));
        this.result.setBackground(Color.WHITE);
        this.result.setText("");
        if (Math.random() >= 0.5) {
            this.currentTask = MathTask.createDivisionTask();
        } else {
            this.currentTask = MathTask.createMultiplicationTask();
        }
        this.formular.setText(this.currentTask.getFormular());
//        }
    }

    private void clickStart(ActionEvent e) {
        this.taskCorrect = 0;
        this.taskTotal = 0;
        this.taskTotal++;
        this.countTask.setText(String.format("%03d/%03d", this.taskCorrect, this.taskTotal));
        this.start.setEnabled(false);
        this.timerLabel.setText("0");
        this.timer.restart();
        this.currentTask = MathTask.createMultiplicationTask();
        this.formular.setText(currentTask.getFormular());
        this.result.setEditable(true);
        this.result.setEnabled(true);
    }
}
