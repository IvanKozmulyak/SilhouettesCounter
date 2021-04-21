package BFS;
//one pixel from image
public class Pixel {
    //Pixel's RGB
    private int RGB;

    //y-coordinate
    private int i;

    //x-coordinate
    private int j;

    public Pixel(int RGB, int i, int j) {
        this.RGB = RGB;
        this.i = i;
        this.j = j;
    }

    public int getRGB() {
        return RGB;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
