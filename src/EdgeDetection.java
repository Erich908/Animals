import java.awt.Color;

/**
 * The EdgeDetection class is used in the Animal program to perform edge
 * detection on images and includes the detectBorder and detect methods.
 *
 * @author GF Engelbrecht
 * @version 1.0
 * @since 10-05-2021
 */
public class EdgeDetection {
    static int epsilon = 0;

    /**
     * This method is used to take an image as input and set pixels, that
     * form part of an edge in the image, to white and the rest of the
     * pixels to black. This method makes use of the detect method.
     * @param cheetahNR This is the input image
     * @param eps This is the epsilon value to determine the edge
     *            detection sensitivity.
     * @return Picture This returns the image after edge detection.
     */
    public static Picture detectBorder(Picture cheetahNR, int eps) {
    epsilon = eps;
    Picture cheetahED = new Picture(cheetahNR.width(),
            cheetahNR.height());
    int w = cheetahED.width();
    int h = cheetahED.height();

    for (int x = 0; x < w; x++) {
        for (int y = 0; y < h; y++) {
         if (x == 0) {

            if (y == 0) {
            Integer[] neigh = new Integer[3];
            neigh[0] = cheetahNR.get(0, 0).getBlue();
            neigh[1] = cheetahNR.get(0, 1).getBlue();
            neigh[2] = cheetahNR.get(1, 0).getBlue();
            int mode = detect(neigh, neigh[0], 0);
            cheetahED.set(x, y, new Color(mode, mode, mode));

            } else if (y == h - 1) {
            Integer[] neigh = new Integer[3];
            neigh[0] = cheetahNR.get(0, h - 2).getBlue();
            neigh[1] = cheetahNR.get(0, h - 1).getBlue();
            neigh[2] = cheetahNR.get(1, h - 1).getBlue();
            int mode = detect(neigh, neigh[1], 1);
            cheetahED.set(x, y, new Color(mode, mode, mode));

            } else {
            Integer[] neigh = new Integer[4];
            neigh[0] = cheetahNR.get(x + 1, y).getBlue();
            neigh[1] = cheetahNR.get(x, y - 1).getBlue();
            neigh[2] = cheetahNR.get(x, y).getBlue();
            neigh[3] = cheetahNR.get(x, y + 1).getBlue();
            int mode = detect(neigh, neigh[2], 2);
            cheetahED.set(x, y, new Color(mode, mode, mode));

                    }

         } else if (x == w - 1) {

            if (y == 0) {
            Integer[] neigh = new Integer[3];
            neigh[0] = cheetahNR.get(w - 2, 0).getBlue();
            neigh[1] = cheetahNR.get(w - 1, 0).getBlue();
            neigh[2] = cheetahNR.get(w - 1, 1).getBlue();
            int mode = detect(neigh, neigh[1], 1);
            cheetahED.set(x, y, new Color(mode, mode, mode));

            } else if (y == h - 1) {
            Integer[] neigh = new Integer[3];
            neigh[0] = cheetahNR.get(w - 2, h - 1).getBlue();
            neigh[1] = cheetahNR.get(w - 1, h - 2).getBlue();
            neigh[2] = cheetahNR.get(w - 1, h - 1).getBlue();
            int mode = detect(neigh, neigh[2], 2);
            cheetahED.set(x, y, new Color(mode, mode, mode));

            } else {
            Integer[] neigh = new Integer[4];
            neigh[0] = cheetahNR.get(x - 1, y).getBlue();
            neigh[1] = cheetahNR.get(x, y - 1).getBlue();
            neigh[2] = cheetahNR.get(x, y).getBlue();
            neigh[3] = cheetahNR.get(x, y + 1).getBlue();
            int mode = detect(neigh, neigh[2], 2);
            cheetahED.set(x, y, new Color(mode, mode, mode));

                    }

         } else {

            if (y == 0) {
            Integer[] neigh = new Integer[4];
            neigh[0] = cheetahNR.get(x - 1, y).getBlue();
            neigh[1] = cheetahNR.get(x + 1, y).getBlue();
            neigh[2] = cheetahNR.get(x, y).getBlue();
            neigh[3] = cheetahNR.get(x, y + 1).getBlue();
            int mode = detect(neigh, neigh[2], 2);
            cheetahED.set(x, y, new Color(mode, mode, mode));

            } else if (y == h - 1) {
            Integer[] neigh = new Integer[4];
            neigh[0] = cheetahNR.get(x - 1, y).getBlue();
            neigh[1] = cheetahNR.get(x + 1, y).getBlue();
            neigh[2] = cheetahNR.get(x, y).getBlue();
            neigh[3] = cheetahNR.get(x, y - 1).getBlue();
            int mode = detect(neigh, neigh[2], 2);
            cheetahED.set(x, y, new Color(mode, mode, mode));

            } else {
            Integer[] neigh = new Integer[5];
            neigh[0] = cheetahNR.get(x - 1, y).getBlue();
            neigh[1] = cheetahNR.get(x + 1, y).getBlue();
            neigh[2] = cheetahNR.get(x, y - 1).getBlue();
            neigh[3] = cheetahNR.get(x, y).getBlue();
            neigh[4] = cheetahNR.get(x, y + 1).getBlue();
            int mode = detect(neigh, neigh[3], 3);
            cheetahED.set(x, y, new Color(mode, mode, mode));

            }

             }
           }
        }

        return cheetahED;
    }

    /**
     * This method takes in the array of the Von Neumann neighbourhood of
     * a pixel, the greyscale value of the center pixel and the array
     * index of the center pixel to determine if any pixel in the
     * Von Neumann neighbourhood of that pixel have greyscale values with
     * a difference less than the epsilon value.
     * @param neigh This is the array of the greyscale values of the
     *                 pixels in the center pixel's Von Neumann
     *                 neighbourhood
     * @param center This is the greyscale value of the center pixel
     * @param cenIndex This is the array index of the center pixel
     * @return int This returns the greyscale value the center pixel has
     * to be set to.
     */
     public static int detect(Integer[] neigh, Integer center, int cenIndex) {
        int colAfter = 0;

        for (int i = 0; i < neigh.length; i++) {
           if (Math.abs(neigh[i] - center) >= epsilon
                              && i != cenIndex) {
                colAfter = 255;
           }
        }

        return colAfter;
    }

}
