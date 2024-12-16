
public class Block {
    private int x, y;

    public final int width; // Dimensions of the field

    public static final int ALTITUDE_GAP = 80; // Gap between blocks
    public static final int START_ALTITUDE = 100; // Le premier bloc commence plus haut

    private static final int MAX_BLOCK_WIDTH = 100;
    private static final int MIN_BLOCK_WIDTH = 50;

    public Block(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public int getX() {
        return this.x;
    }

    public int setX(int x) {
        return this.x=x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean collidesWith(int axelX, int axelY, int axelWidth, int axelHeight) {
        return axelX < x + width && axelX + axelWidth > x &&
               axelY < y + Field.getBlockHeight() && axelY + axelHeight > y;
    }

    

}