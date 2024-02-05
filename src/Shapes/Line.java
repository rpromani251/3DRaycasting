package src.Shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line {
    private final int SIZE = 1;
    public static int POINT_SIZE = Point.size;
    private int POINT_BUFFER = POINT_SIZE / 2;

    private Point point1, point2;

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine((int) point1.x + POINT_BUFFER , (int) point1.y + POINT_BUFFER, (int) point2.x + POINT_BUFFER, (int) point2.y + POINT_BUFFER);
    }
}
