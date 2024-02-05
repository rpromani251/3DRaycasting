package src;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize JFrame and instance of Application class
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create JFrame
                JFrame frame = new JFrame("Raycasting");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create instance of Application and add it to the main frame, making it focusable
                Application app = new Application();
                frame.add(app);
                app.setFocusable(true);
                app.Setup();
                app.setupWindowFocusListener(frame);
                app.requestFocusInWindow();

                // Pack, make visible, and set the main frame to be unresizable
                frame.pack();
                frame.setVisible(true);
                frame.setResizable(false);

                // Loop:
                Timer timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.repaint();
                    }
                });
                timer.start();
            }
        });
    }
}
