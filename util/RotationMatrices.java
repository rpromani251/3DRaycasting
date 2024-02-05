package util;

public class RotationMatrices {

    public static double[][] rotationX(double angle) {
        return new double[][] {
            { 1, 0, 0},
            { 0, Math.cos(angle), -Math.sin(angle)},
            { 0, Math.sin(angle), Math.cos(angle)}
        };
    }
    public static double[][] rotationY(double angle) {
        return new double[][] {
            { Math.cos(angle), 0, Math.sin(angle)},
            { 0, 1, 0},
            { -Math.sin(angle), 0, Math.cos(angle)}
        };
    }
    public static double[][] rotationZ(double angle) {
        return new double[][] {
            { Math.cos(angle), -Math.sin(angle), 0},
            { Math.sin(angle), Math.cos(angle), 0},
            { 0, 0, 1}
        };
    }
}
