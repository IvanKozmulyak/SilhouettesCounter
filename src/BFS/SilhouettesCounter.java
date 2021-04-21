package BFS;

import acm.graphics.GImage;

import java.util.HashMap;
import java.util.Map;

public class SilhouettesCounter {

    public static void main(String[] args) {
        try {
            int[][] imageArray = readFile(args[0]);
            int backgroundRGB = findBackground(imageArray);
            Pixel[][] pixelsArray = convert(imageArray);
            System.out.println(findSilhouettes(backgroundRGB, pixelsArray));
        } catch (Exception e) {
            System.out.println("File not found!!!");
        }
    }

    /**
     * Convert from array with RGB's to array with RGB's and i, j position
     *
     * @param imageArray array with RGB's each pixels of image.
     * @return array with Pixel's
     */
    private static Pixel[][] convert(int[][] imageArray) {
        Pixel[][] pixelsArray = new Pixel[imageArray.length][imageArray[0].length];
        for (int i = 0; i < imageArray.length; i++) {
            for (int j = 0; j < imageArray[0].length; j++) {
                pixelsArray[i][j] = new Pixel(imageArray[i][j], i, j);
            }
        }
        return pixelsArray;
    }

    /**
     * Count silhouettes in image.
     *
     * @param backgroundRGB RGB of background
     * @param pixelsArray   array with Pixel's
     * @return number of silhouettes.
     */
    private static int findSilhouettes(int backgroundRGB, Pixel[][] pixelsArray) {
        //in this array we note the visited pixels
        boolean[][] visitsArray = new boolean[pixelsArray.length][pixelsArray[0].length];
        SimpleQueue<Pixel> queue = new SimpleQueue<>();

        int silhouettes = 0;

        //count Pixel's of silhouettes
        int counter = 0;

        //adjusts the smallest size of the silhouette
        int limit = pixelsArray.length * pixelsArray[0].length / 1000;

        for (int i = 0; i < pixelsArray.length; i++) {
            for (int j = 0; j < pixelsArray[0].length; j++) {

                if (pixelsArray[i][j].getRGB() != backgroundRGB && !visitsArray[i][j]) {

                    if (counter > limit) {
                        silhouettes++;
                    }

                    visitsArray[i][j] = true;
                    queue.add(pixelsArray[i][j]);
                    counter = 0;

                    while (!queue.isEmpty()) {
                        Pixel pixel = queue.remove();

                        if (
                                pixel.getJ() != pixelsArray[0].length - 1
                                        && pixelsArray[pixel.getI()][pixel.getJ() + 1].getRGB() != backgroundRGB
                                        && !visitsArray[pixel.getI()][pixel.getJ() + 1]
                        ) {
                            queue.add(pixelsArray[pixel.getI()][pixel.getJ() + 1]);
                            visitsArray[pixel.getI()][pixel.getJ() + 1] = true;
                            counter++;
                        }

                        if (
                                pixel.getJ() != 0
                                        && pixelsArray[pixel.getI()][pixel.getJ() - 1].getRGB() != backgroundRGB
                                        && !visitsArray[pixel.getI()][pixel.getJ() - 1]
                        ) {
                            queue.add(pixelsArray[pixel.getI()][pixel.getJ() - 1]);
                            visitsArray[pixel.getI()][pixel.getJ() - 1] = true;
                            counter++;
                        }

                        if (
                                pixel.getI() != 0
                                        && pixelsArray[pixel.getI() - 1][pixel.getJ()].getRGB() != backgroundRGB
                                        && !visitsArray[pixel.getI() - 1][pixel.getJ()]
                        ) {
                            queue.add(pixelsArray[pixel.getI() - 1][pixel.getJ()]);
                            visitsArray[pixel.getI() - 1][pixel.getJ()] = true;
                            counter++;
                        }

                        if (
                                pixel.getI() != pixelsArray.length - 1
                                        && pixelsArray[pixel.getI() + 1][pixel.getJ()].getRGB() != backgroundRGB
                                        && !visitsArray[pixel.getI() + 1][pixel.getJ()]
                        ) {
                            queue.add(pixelsArray[pixel.getI() + 1][pixel.getJ()]);
                            visitsArray[pixel.getI() + 1][pixel.getJ()] = true;
                            counter++;
                        }
                    }
                }
            }
        }
        return silhouettes;
    }

    /**
     * Finds RGB of background
     *
     * @param imageArray array with RGB's each pixels of image.
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
    private static int[][] readFile(String fileName) {
        GImage image = new GImage(fileName);
        return image.getPixelArray();
    }
}
