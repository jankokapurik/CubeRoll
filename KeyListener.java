import javax.swing.*;
import java.awt.event.*;

public class KeyListener extends JFrame {
    public KeyListener() {
        JPanel panel = new JPanel();
        panel.setFocusable(true); // Ensure the panel can gain focus to receive key events

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        System.out.println("Up arrow pressed");
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("Down arrow pressed");
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("Left arrow pressed");
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("Right arrow pressed");
                        break;
                }
            }
        });

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }
}
