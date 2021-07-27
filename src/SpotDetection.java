import java.awt.Color;

/**
 * The SpotDetection class is used in the Animal program to count the visible
 * spots on a cheetah in images and includes the mask, findSpots, setSpots,
 * getSpots and getSpotsCounted methods.
 *
 * @author GF Engelbrecht
 * @version 1.0
 * @since 10-06-2021
 */
public class SpotDetection {

    private static int spotsCounted = 0;
    private static int startX = 0;
    private static int startY = 0;
    private static Picture spots;

    /**
     * This method creates the mask array used for spot detection.
     *
     * @param r This is the radius
     * @param w This is the width
     * @param d This is delta
     * @return int[][] This returns the mask array used for detecting and
     * counting spots.
     */
    public static int[][] mask(int r, int w, int d) {
        int[][] xx = new int[2 * r + 1][2 * r + 1];
        int[][] yy = new int[2 * r + 1][2 * r + 1];
        int[][] mask = new int[2 * r + 1][2 * r + 1];
        double[][] circle = new double[2 * r + 1][2 * r + 1];
        boolean[][] donut = new boolean[2 * r + 1][2 * r + 1];
        for (int i = 0; i < 2 * r + 1; i++) {
            for (int j = 0; j < 2 * r + 1; j++) {
                xx[i][j] = i;
                yy[i][j] = j;
            }
        }
        for (int i = 0; i < 2 * r + 1; i++) {
            for (int j = 0; j < 2 * r + 1; j++) {
                circle[i][j] = Math.pow(xx[i][j] - r, 2)
                        + Math.pow(yy[i][j] - r, 2);
                donut[i][j] = (circle[i][j] < (Math.pow(r - d, 2) + w))
                        && (circle[i][j] > (Math.pow(r - d, 2) - w));
                if (donut[i][j]) {
                    mask[i][j] = 255;
                } else {
                    mask[i][j] = 0;
                }
            }
        }
        /*for(int y = 0; y < mask.length; y++) {
            for (int x = 0; x < mask.length; x++) {
                System.out.print(mask[y][x] + " ");
            }
            System.out.println();
        }*/
        return mask;
    }

    /**
     * This method is used to take an image as input to detect and count the
     * spots present in the image.
     *
     * @param cheetahED This is the input image.
     * @param mask      This is the mask array
     * @param dif       This is the difference threshold
     */
    public static void findSpots(Picture cheetahED, int[][] mask, int dif) {
        int ciX = (int) Math.floor(mask.length / 2);
        int ciY = (int) Math.floor(mask.length / 2);
        int totalDif = 0;
        double[][] imBlock = new double[mask.length][mask.length];
        for (int i = ciX; i < cheetahED.width() - ciX; i++) {
            for (int j = ciY; j < cheetahED.height() - ciY; j++) {
                for (int x = 0; x < mask.length; x++) {
                    for (int y = 0; y < mask.length; y++) {
                        imBlock[y][x] = cheetahED.get(x + startX,
                                y + startY).getBlue();
                    }
                }
                boolean isSet = false;
                for (int x = 0; x < mask.length; x++) {
                    for (int y = 0; y < mask.length; y++) {
                        if (spots.get(x + startX,
                                y + startY).getBlue() == 255) {
                            isSet = true;
                        }
                    }
                }
                if (!isSet) {
                    int max = 0;
                    for (int x = 0; x < mask.length; x++) {
                        for (int y = 0; y < mask.length; y++) {
                            if (imBlock[y][x] > max) {
                                max = (int) imBlock[y][x];
                            }
                        }
                    }
                    //System.out.println(max);
                    if (max > 0) {
                        for (int x = 0; x < mask.length; x++) {
                            for (int y = 0; y < mask.length; y++) {
                                //imBlock[y][x] = imBlock[y][x] * 255/max;
                                totalDif += Math.abs(imBlock[y][x]
                                        - mask[y][x]);
                            }
                        }
                        if (totalDif < dif) {
                            spotsCounted++;
                            for (int x = 0; x < mask.length; x++) {
                                for (int y = 0; y < mask.length; y++) {
                                    if (imBlock[y][x] == 255) {
                                        spots.set(x + startX, y + startY,
                                                Color.WHITE);
                                    }
                                }
                            }
                        }
                        totalDif = 0;
                    }
                }
                startY++;
            }
            startX++;
            startY = 0;
        }
        startX = 0;
        startY = 0;
    }

    /**
     * This method is used to take an image as input and set pixels, that
     * form part of an edge in the image, to white and the rest of the
     * pixels to black. This method makes use of the detect method.
     *
     * @param cheetahED This is the image used to initialize the Picture
     *                  object spots.
     */
    public static void setSpots(Picture cheetahED) {
        spots = new Picture(cheetahED.width(), cheetahED.height());
    }

    /**
     * This method is used to return the final image after spot detection.
     *
     * @return Picture This returns the image after spot detection.
     */
    public static Picture getSpots() {
        return spots;
    }

    /**
     * This method is used to return amount of spots counted in the given
     * image.
     *
     * @return int This returns the amount of spots counted.
     */
    public static int getSpotsCounted() {
        return spotsCounted;
    }
}
