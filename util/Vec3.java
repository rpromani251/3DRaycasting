package util;

public class Vec3 {
    public double x, y, z;
    
    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(double[] arr) {
        this.set(arr);
    }

    // "Overload" Math Operators for vector addition/subtraction/multiplication/division
    public Vec3 add(Vec3 v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        return this;
    }
    public Vec3 sub(Vec3 v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        return this;
    }
    public Vec3 mult(Vec3 v) {
        this.x *= v.x;
        this.y *= v.y;
        this.z *= v.z;
        return this;
    }
    public Vec3 div(Vec3 v) {
        this.x /= v.x;
        this.y /= v.y;
        this.z /= v.z;
        return this;
    }

    public Vec3 scale(double s) {
        scale(s, s, s);
        return this;
    }
    
    public void scale(double sx, double sy, double sz) {
        this.x *= sx;
        this.y *= sy;
        this.z *= sz;
    }

    public void set(double[] arr) {
        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
    }

    public double[] toArray() {
        return new double[] {this.x, this.y, this.z};
    }

    public boolean equals(Vec3 pos1, Vec3 pos2) {
        return pos1.x == pos2.x && (pos1.y == pos2.y && pos1.z == pos2.z);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")"; // Return (x, y, z)
    }

    // Rotation about x-axis:
    public void RotateX(double angle) {
        this.set(Matrix.matmul(RotationMatrices.rotationX(angle), this));
    }

    // Rotation about y-axis:
    public void RotateY(double angle) {
        this.set(Matrix.matmul(RotationMatrices.rotationY(angle), this));
    }

    // Rotation about z-axis:
    public void RotateZ(double angle) {
        this.set(Matrix.matmul(RotationMatrices.rotationZ(angle), this));
    }

    public static boolean isWithinBounds(Vec3 bounds, Vec3 position) {
        return bounds.x >= position.x && bounds.y >= position.y;
    }
}
