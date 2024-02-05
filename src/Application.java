package src;

import javax.swing.*;

import util.Vec2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Application extends JPanel implements MouseMotionListener {
    // Window: 
    private final int FRAME_WIDTH = 1024;
    private final int FRAME_HEIGHT = 512;

    // Class Declarations
    private Map map;
    private Player player;

    // Mouse Control Fields:
    private Robot robot;
    private boolean isMouseControlEnabled = true;
    private boolean ignoreRecentering = false;
    private Vec2 center;
    private double sensitivity = 0.0005;
    private long lastRecenterTimeMillis;

    private int lastMouseX=-1, lastMouseY=-1;

    // Constructor: 
    public Application() {
        // Set Default Values:
        setBackground(new Color(50, 50, 50));
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        // Mouse Stuff:
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0,0), "blank cursor" 
        );
        setCursor(blankCursor);
        addMouseMotionListener(this);

        // Initialize Robot for recentering mouse and get the center
        try {
            robot = new Robot();
            center = new Vec2(FRAME_WIDTH/2, FRAME_HEIGHT/2);
            robot.mouseMove((int)center.x, (int)center.y);
        } catch (Exception e) {
            e.printStackTrace();
            isMouseControlEnabled = false;
        }


        // 
        map = new Map(8, 8, 64);
        player = new Player(304,304);
        player.theta = 0;
        player.dx = Math.cos(player.theta) * 5;
        player.dy = Math.sin(player.theta) * 5;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (ignoreRecentering) { ignoreRecentering = false; return; }
        if (isMouseControlEnabled) {
            if (lastMouseX==-1 ||lastMouseY==-1) { lastMouseX = e.getX(); lastMouseY = e.getY(); }

            // Calculate deltaX
            int deltaX = e.getX() - (int)center.x;
            System.out.println(deltaX);

            // Update the player's direction based on mouse movements
            player.theta += deltaX * sensitivity;
            player.theta = (player.theta + 2*Math.PI) % (2*Math.PI);  
            // Update the player's direction vector
            player.dx = Math.cos(player.theta)*5;
            player.dy = Math.sin(player.theta)*5; 
        }
            // Update last position

        // Recenter the mouse
        ignoreRecentering = true;
        SwingUtilities.invokeLater(() -> {
            if (isFocusOwner() && isMouseControlEnabled && ignoreRecentering) {
                lastRecenterTimeMillis = 0;
                // When recentering
                lastRecenterTimeMillis = System.currentTimeMillis();
                // In mouseMoved
                long currentTimeMillis = System.currentTimeMillis();
                // if (currentTimeMillis - lastRecenterTimeMillis < 10) { // Ignore events for 50ms after recentering
                //     return;
                // }
                robot.mouseMove((int)center.x, (int)center.y);
                ignoreRecentering = false;
            }
        });
        
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        return;
    }

    public void setupWindowFocusListener(JFrame frame) {
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                isMouseControlEnabled = true;
            }
            @Override
            public void windowLostFocus(WindowEvent e) {
                isMouseControlEnabled = false;
            }
        });
    }
    

    public void Setup() {
        // Add Component Listener:
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                updateCenter();
            }
            @Override
            public void componentResized(ComponentEvent e) {
                updateCenter();
            }
            private void updateCenter() {
                // Point location = this.getLocationOnScreen();
                // center.x = location.x + this.getWidth()/2;
                // center.y = location.y/2;
            }
        });
        
        // Key Listeners:
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W: 
                        player.y += player.dy; 
                        player.x += player.dx; 
                        break;
                    case KeyEvent.VK_S: 
                        player.y -= player.dy; 
                        player.x -= player.dx; 
                        break;
                    case KeyEvent.VK_A: 
                        player.theta -= 0.1;
                        if (player.theta <= 0) player.theta += 2*Math.PI;
                            player.dx = Math.cos(player.theta) * 5;
                            player.dy = Math.sin(player.theta) * 5;
                            System.out.println(player.theta);
                        break;
                    case KeyEvent.VK_D: 
                        player.theta += 0.1;
                        if (player.theta >= 2*Math.PI) player.theta -= 2*Math.PI;
                            player.dx = Math.cos(player.theta) * 5;
                            player.dy = Math.sin(player.theta) * 5;
                            System.out.println(player.theta);
                        break;
                }
            }
        });
    }

    // Override paintComponent method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (map != null) {
            map.draw(g);
            player.draw(g);
        }
    }
}

