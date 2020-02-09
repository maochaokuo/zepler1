package swing;

import javax.swing.*;
import java.awt.*;

public class TestFlowLayout {

    public static void main(String[] args) {
        new TestFlowLayout();
    }

    public TestFlowLayout() {
        EventQueue.invokeLater(() -> {
            JPanel master = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel left = new JPanel();
            left.setBackground(Color.RED);
            left.add(new JLabel("Lefty1"));
            left.add(new JTextField("Lefty2"));
            left.add(new JLabel("Lefty3"));

            JPanel right = new JPanel();
            right.setBackground(Color.BLUE);
            right.add(new JLabel("Righty1"));
            right.add(new JTextField("Righty2"));
            right.add(new JLabel("Righty3"));

            JPanel other1 = new JPanel();
            other1.setBackground(Color.CYAN);
            other1.add(new JLabel("other1a"));
            other1.add(new JTextField("other1b"));
            other1.add(new JLabel("other1c"));

            JPanel other2 = new JPanel();
            other2.setBackground(Color.DARK_GRAY);
            other2.add(new JLabel("other2a"));
            other2.add(new JTextField("other2b"));
            other2.add(new JLabel("other2c"));

            JPanel other3 = new JPanel();
            other3.setBackground(Color.GRAY);
            other3.add(new JLabel("other3a"));
            other3.add(new JTextField("other3b"));
            other3.add(new JLabel("other3c"));

            master.add(left);
            master.add(right);
            master.add(other1);
            master.add(other2);
            master.add(other3);

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(master);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}