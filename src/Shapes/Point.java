package src.Shapes;

import java.awt.Color;
import java.awt.Graphics;

import util.Vec3;

public class Point extends Vec3 {
    public static int size = 2;
    public static final int maxSize = 20, minSize = 0;
    
    public Point(double x, double y, double z) {
        super(x, y, z);
    }

    public Point(double[] arr){
        super(arr);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x, (int) y, size, size);
    }

}
