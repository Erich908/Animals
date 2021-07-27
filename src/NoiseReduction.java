import java.awt.Color;

/**
 * The NoiseReduction class is used by the Animal program to take an image as
 * input and return a noise reduced version of the image. This class includes
 * the reduceNoise and mode methods.
 *
 * @author GF Engelbrecht
 * @version 1.0
 * @since 10-05-2021
 */
public class NoiseReduction {
    static int cool = 0;

    /**
     * This method makes use of the mode method to determine the greyscale
     * value most commonly present in every pixel's Von Neumann
     * neighbourhood and then set the pixel to that greyscale value.
     *
     * @param cheetah This is the Picture parameter used to create a noise
     *                reduced version of the given Picture object
     * @return Picture This method returns a noise reduced Picture object
     * of the given Picture.
     */
    public static Picture reduceNoise(Picture cheetah) {
        int h = cheetah.height();
        int w = cheetah.width();
        Picture cheetahNR = new Picture(w, h);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if (x == 0) {

                    if (y == 0) {
                        Integer[] neigh = new Integer[3];
                        neigh[0] = cheetah.get(0, 0).getBlue();
                        neigh[1] = cheetah.get(0, 1).getBlue();
                        neigh[2] = cheetah.get(1, 0).getBlue();
                        int mode = mode(neigh, neigh[0]);
                        cheetahNR.set(x, y, new Color(mode, mode, mode));

                    } else if (y == h - 1) {
                        Integer[] neigh = new Integer[3];
                        neigh[0] = cheetah.get(0, h - 2).getBlue();
                        neigh[1] = cheetah.get(0, h - 1).getBlue();
                        neigh[2] = cheetah.get(1, h - 1).getBlue();
                        int mode = mode(neigh, neigh[1]);
                        cheetahNR.set(x, y, new Color(mode, mode, mode));

                    } else {
                        Integer[] neigh = new Integer[4];
                        neigh[0] = cheetah.get(x + 1, y).getBlue();
                        neigh[1] = cheetah.get(x, y - 1).getBlue();
                        neigh[2] = cheetah.get(x, y).getBlue();
                        neigh[3] = cheetah.get(x, y + 1).getBlue();
                        int mode = mode(neigh, neigh[2]);
                        cheetahNR.set(x, y, new Color(mode, mode, mode));

                    }

                } else if (x == w - 1) {

                    if (y == 0) {
                        Integer[] neigh = new Integer[3];
                        neigh[0] = cheetah.get(w - 2, 0).getBlue();
                        neigh[1] = cheetah.get(w - 1, 0).getBlue();
                        neigh[2] = cheetah.get(w - 1, 1).getBlue();
                        int mode = mode(neigh, neigh[1]);
                        cheetahNR.set(x, y, new Color(mode, mode, mode));

                    } else if (y == h - 1) {
                        Integer[] neigh = new Integer[3];
                        neigh[0] = cheetah.get(w - 2, h - 1).getBlue();
                        neigh[1] = cheetah.get(w - 1, h - 2).getBlue();
                        neigh[2] = cheetah.get(w - 1, h - 1).getBlue();
                        int mode = mode(neigh, neigh[2]);
                        cheetahNR.set(x, y, new Color(mode, mode, mode));

                    } else {
                        Integer[] neigh = new Integer[4];
                        neigh[0] = cheetah.get(x - 1, y).getBlue();
                        neigh[1] = cheetah.get(x, y - 1).getBlue();
                        neigh[2] = cheetah.get(x, y).getBlue();
                        neigh[3] = cheetah.get(x, y + 1).getBlue();
                        int mode = mode(neigh, neigh[2]);
                        cheetahNR.set(x, y, new Color(mode, mode, mode));

                    }

                } else {

                    if (y == 0) {
                        Integer[] neigh = new Integer[4];
                        neigh[0] = cheetah.get(x - 1, y).getBlue();
                        neigh[1] = cheetah.get(x + 1, y).getBlue();
                        neigh[2] = cheetah.get(x, y).getBlue();
                        neigh[3] = cheetah.get(x, y + 1).getBlue();
                        int mode = mode(neigh, neigh[2]);
                        cheetahNR.set(x, y, new Color(mode, mode, mode));

                    } else if (y == h - 1) {
                        Integer[] neigh = new Integer[4];
                        neigh[0] = cheetah.get(x - 1, y).getBlue();
                        neigh[1] = cheetah.get(x + 1, y).getBlue();
                        neigh[2] = cheetah.get(x, y).getBlue();
                        neigh[3] = cheetah.get(x, y - 1).getBlue();
                        int mode = mode(neigh, neigh[2]);
                        cheetahNR.set(x, y, new Color(mode, mode, mode));

                    } else {
                        Integer[] neigh = new Integer[5];
                        neigh[0] = cheetah.get(x - 1, y).getBlue();
                        neigh[1] = cheetah.get(x + 1, y).getBlue();
                        neigh[2] = cheetah.get(x, y - 1).getBlue();
                        neigh[3] = cheetah.get(x, y).getBlue();
                        neigh[4] = cheetah.get(x, y + 1).getBlue();
                        int mode = mode(neigh, neigh[3]);
                        cheetahNR.set(x, y, new Color(mode, mode, mode));

                    }

                }
            }
        }
        return cheetahNR;
    }

    /**
     * This method takes in the array of a pixel's Von Neumann
     * neighbourhood greyscale values and the center pixel's greyscale
     * value and returns the value most commonly present in the array
     * given.
     *
     * @param a      This is the greyscale value array of the Von Neumann
     *               neighbourhood
     * @param center This is the greyscale value of the center pixel
     * @return int This method returns the greyscale value the center
     * pixel must be set to.
     */
    public static int mode(Integer[] a, int center) {
        int currentMode = 0;
        int modeOccur = 0;
        int currentOccur = 1;

        Insertion.sort(a);

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].equals(a[i + 1]) && i + 1 < a.length - 1) {
                currentOccur++;
            } else if (a[i].equals(a[i + 1]) && i + 1 == a.length - 1
                    && currentOccur + 1 > modeOccur) {
                currentOccur++;

                modeOccur = currentOccur;
                currentMode = a[i];
                currentOccur = 1;
            } else if (a[i].equals(a[i + 1]) && i + 1 == a.length - 1
                    && currentOccur + 1 == modeOccur
                    && (currentMode == center || a[i].equals(center))) {
                currentOccur++;

                modeOccur = currentOccur;
                currentMode = center;
                currentOccur = 1;
            } else if (!a[i].equals(a[i + 1])
                    && currentOccur > modeOccur) {
                modeOccur = currentOccur;
                currentMode = a[i];
                currentOccur = 1;
            } else if (!a[i].equals(a[i + 1]) && currentOccur == modeOccur
                    && (currentMode == center || a[i].equals(center)
                                              || a[i + 1].equals(center))) {
                modeOccur = currentOccur;
                currentMode = center;
                currentOccur = 1;
            } else if (!a[i].equals(a[i + 1])) {
                currentOccur = 1;
            }
        }
        return currentMode;
    }

}
