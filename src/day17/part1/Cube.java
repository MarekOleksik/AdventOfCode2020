package day17.part1;

public class Cube {
    private int x = 0;
    private int y = 0;
    private int z = 0;
    private boolean isActive = false;

    public Cube(int x, int y, int z, boolean isActive) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isActive = isActive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
