import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = createCustomJFrame();
            frame.setVisible(true);
        });
    }

    private static JFrame createCustomJFrame() {
        JFrame frame = new JFrame("Custom JFrame Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Disable the title bar buttons (maximize, minimize, close)
        frame.setUndecorated(true);

        // You can still close the frame by pressing Alt+F4 or using a custom method
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Add your custom closing logic here
                int choice = JOptionPane.showConfirmDialog(frame, "Do you really want to close this application?",
                        "Confirm Close", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        return frame;
    }
}
