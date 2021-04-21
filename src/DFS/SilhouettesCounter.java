package DFS;

import acm.graphics.GImage;
import com.shpp.cs.a.graphics.WindowProgram;

import java.util.HashMap;
import java.util.Map;

public class SilhouettesCounter extends WindowProgram {

    private static final String FILE_NAME = "assets/0.png";

    //The constant that adjusts the smallest size of the silhouette
    //can be increased or decreased depending on the size of the image
    private static final int LIMIT = 200;

    private static int counter = 0;
    private static int[][] imageArray;
    private static boolean[][] visitsArray;

    public static void main(String[] args) {
        try {
            imageArray = readFile();
            System.out.println("On this image " + findSilhouettes(imageArray) + " silhouettes");
        } catch (Exception e) {
            System.out.println("FILE NOT FOUND!!!");
        }
    }

    /**
     * Count silhouettes in image.
     * @param imageArray array with image.
     * @return number of silhouettes.
     */
    private static int findSilhouettes(int[][] imageArray) {
        int backgroundRGB = findBackground(imageArray);
        //in this array we note the visited pixels
        visitsArray = new boolean[imageArray.length][imageArray[0].length];
        int silhouettes = 0;

        for (int i = 0; i < imageArray.length; i++) {
            for (int j = 0; j < imageArray[0].length; j++) {

                if (imageArray[i][j] != backgroundRGB && !visitsArray[i][j]) {
                    counter = 0;
                    checkPixel(i, j, backgroundRGB);

                    if (counter > LIMIT) {
                        System.out.println(counter);
                        silhouettes++;
                    }
                }
            }
        }
        return silhouettes;
    }

    /**
     * Кecursive method.
     * Сhecks if there is another pixel near the pixel that is not the background.
     * @param i y-coordinate of pixel
     * @param j x-coordinate of pixel
     * @param backgroundRGB RGB of background
     */
    private static void checkPixel(int i, int j, int backgroundRGB) {
        visitsArray[i][j] = true;
        counter++;

        if (j != imageArray[0].length - 1 && imageArray[i][j + 1] != backgroundRGB && !visitsArray[i][j + 1]) {
            checkPixel(i, j + 1, backgroundRGB);
        }

        if (j != 0 && imageArray[i][j - 1] != backgroundRGB && !visitsArray[i][j - 1]) {
            checkPixel(i, j - 1, backgroundRGB);
        }

        if (i != 0 && imageArray[i - 1][j] != backgroundRGB && !visitsArray[i - 1][j]) {
            checkPixel(i - 1, j, backgroundRGB);
        }

        if (i != imageArray.length - 1 && imageArray[i + 1][j] != backgroundRGB && !visitsArray[i + 1][j]) {
            checkPixel(i + 1, j, backgroundRGB);
        }
    }

    /**
     * Finds RGB of background
     * @param imageArray array with image.
     * @return RGB of background
     */
    private static int findBackground(int[][] imageArray) {
        HashMap<Integer, Integer> RGBs = new HashMap<>();

        for (int i = 0; i < imageArray.length; i++) {
            for (int j = 0; j < imageArray[0].length; j++) {

                if (!RGBs.containsKey(imageArray[i][j])) {
                    RGBs.put(imageArray[i][j], 1);
                } else {
                    RGBs.put(imageArray[i][j], RGBs.get(imageArray[i][j]) + 1);
                }
            }
        }

        int max = 0;
        int maxRGB = 0;

        for (Map.Entry<Integer, Integer> p : RGBs.entrySet()) {

            if (max < p.getValue()) {
                max = p.getValue();
                maxRGB = p.getKey();
            }
        }
        return maxRGB;
    }

    //just read image
    private static int[][] readFile() {
        GImage image = new GImage(FILE_NAME);
        return image.getPixelArray();
    }
}
