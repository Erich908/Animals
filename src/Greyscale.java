
import java.awt.Color;

/**
 * The Greyscale class is used by the Animal program to take a pixel's RGB
 * values as input and return a greyscale colour of that pixel. This class
 * includes the getGreyScale method.
 *
 * @author GF Engelbrecht
 * @version 1.0
 * @since 10-05-2021
 */
public class Greyscale {
    /**
     * This method takes in the RGB values of a pixel and return the
     * greyscale colour of that pixel.
     * @param r This is the Red value of a pixel
     * @param g This is the Green value of a pixel
     * @param b This is the Blue value of a pixel
     * @return Color This returns the greyscale colour object if a given
     * pixel.
     */
    public static Color getGreyScale(int r, int g, int b) {
        int grey = (int) (r * 0.299 + g * 0.587 + b * 0.114);
        Color greyNew = new Color(grey, grey, grey);
        return greyNew;
    }

}
