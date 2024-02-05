package util;

import java.util.function.BiConsumer;

public class Vec2 {

    public double x;
    public double y;

    public static int windowWidth;
    public static int windowHeight;

    // Overloaded Constructors
    public Vec2() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void set(double x, double y ) {
        this.x = x;
        this.y = y;
    }

    // "Overload" Math Operators for vector addition/subtraction/multiplication/division
    public Vec2 add(Vec2 v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }
    public Vec2 sub(Vec2 v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }
    public Vec2 mult(Vec2 v) {
        this.x *= v.x;
        this.y *= v.y;
        return this;
    }
    public Vec2 div(Vec2 v) {
        this.x /= v.x;
        this.y /= v.y;
        return this;
    }

    public Vec2 scale(double s) {
        scale(s, s);
        return this;
    }

    public double dist(Vec2 v1, Vec2 v2) {
        return Math.sqrt( (v2.x-v1.x)*(v2.x-v1.x) + (v2.y-v1.y)*(v2.y-v1.y) );
    }

    public double dist(double ax, double ay, double bx, double by) {
        return Math.sqrt( (bx-ax)*(bx-ax) + (by-ay)*(by-ay) );
    }
    
    public void scale(double sx, double sy) {
        this.x *= sx;
        this.y *= sy;
    }

    public boolean equals(Vec2 pos1, Vec2 pos2) {
        return pos1.x == pos2.x && pos1.y == pos2.y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")"; // Return (x, y)
    }

    public static boolean isWithinBounds(Vec2 bounds, Vec2 position) {
        return bounds.x >= position.x && bounds.y >= position.y;
    }

    public static Vec2 nCoord(int x, int y) {
        return new Vec2(x, windowHeight - y);
    }

    public static void iterateAndApplyMethodBetweenTwoPoints(Vec2 pos1, Vec2 pos2, BiConsumer<Integer, Integer> function) {
        // If the two points are the same no need to iterate. Just run the provided function
        if (pos1.equals(pos2)) {
            function.accept((int) pos1.x, (int) pos1.y);
            return;
        }

        // Create Variables to store the x and y values of each position
        int matrixX1 = (int) pos1.x;
        int matrixY1 = (int) pos1.y;
        int matrixX2 = (int) pos2.x;
        int matrixY2 = (int) pos2.y;

        // Find both the x and y differences and find which difference is larger
        int xDiff = matrixX1 - matrixX2;
        int yDiff = matrixY1 - matrixY2;
        boolean xDiffIsLarger = Math.abs(xDiff) > Math.abs(yDiff);

        // If diff is less than zero, that indicates a positive change; greater than zero, negative change; make positive accordingly
        int xModifier = xDiff < 0 ? 1 : -1;
        int yModifier = yDiff < 0 ? 1 : -1;

        // Find the longer and shorter sides
        int longerSideLength = Math.max(Math.abs(xDiff), Math.abs(yDiff));
        int shorterSideLength = Math.min(Math.abs(xDiff), Math.abs(yDiff));
        float slope = (shorterSideLength == 0 || longerSideLength == 0) ? 0 : ((float) (shorterSideLength) / (longerSideLength));
    
        int shorterSideIncrease;
        for (int i = 1; i <= longerSideLength; i++) {
            shorterSideIncrease = Math.round(i * slope);
            int yIncrease, xIncrease;
            if (xDiffIsLarger) {
                xIncrease = i;
                yIncrease = shorterSideIncrease;
            } 
            else {
                yIncrease = i;
                xIncrease = shorterSideIncrease;
            }
            int currentY = matrixY1 + (yIncrease * yModifier);
            int currentX = matrixX1 + (xIncrease * xModifier);

            if (isWithinBounds(new Vec2(400, 500), new Vec2(currentX, currentY))) {
                function.accept(currentX, currentY);
            }
        }
    }
}
