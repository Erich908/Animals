
import java.awt.Color;
import java.io.File;

/**
 * The Animal program implements an application that takes an image of a
 * cheetah as input and applies Cellular Automata to detect the amount
 * of spots present on the cheetah.
 *
 * @author GF Engelbrecht
 * @version 1.0
 * @since 10-05-2021
 */
public class Animal extends SpotDetection {
    ///////////////////////////// Change guiMode value to true to print image
    static boolean gui = false;

    /**
     * This method returns if the given string is an int
     *
     * @param mode String
     * @return boolean This method returns a boolean staing if the given string
     * is an int
     */
    public static boolean validModEp(String mode) {
        try {
            Integer.parseInt(mode);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * This is the main method which uses validModEp method.
     *
     * @param args Terminal arguments which determines which mode is
     *             run(args[0]) ,which image is chosen as input(args[1]) and
     *             which epsilon value is used when mode 2 is
     *             selected(args[2]).
     */
    public static void main(String[] args) {
        //////////////////////////////////////////////Error handling
        if (Integer.parseInt(args[0]) == 3) {
            if (args.length != 5) {
                System.err.println("ERROR: invalid number of arguments");
                System.exit(0);
            }
        } else if (Integer.parseInt(args[0]) == 2) {
            if (args.length != 3) {
                System.err.println("ERROR: invalid number of arguments");
                System.exit(0);
            }
        } else if (Integer.parseInt(args[0]) == 1
                || Integer.parseInt(args[0]) == 0) {
            if (args.length != 2) {
                System.err.println("ERROR: invalid number of arguments");
                System.exit(0);
            }
        }
        if (!validModEp(args[0])) {
            System.err.println("ERROR: invalid argument type");
            System.exit(0);
        }
        if (Integer.parseInt(args[0]) >= 2) {
            if (!validModEp(args[2])) {
                System.err.println("ERROR: invalid argument type");
                System.exit(0);
            }
        }
        if (Integer.parseInt(args[0]) > 3 || Integer.parseInt(args[0]) < 0) {
            System.err.println("ERROR: invalid mode");
            System.exit(0);
        }
        if (Integer.parseInt(args[0]) >= 2) {
            if (Integer.parseInt(args[2]) > 255
                    || Integer.parseInt(args[2]) < 0) {
                System.err.println("ERROR: invalid epsilon");
                System.exit(0);
            }
        }
        if (!new File(args[1]).isFile()) {
            System.err.println("ERROR: invalid or missing file");
            System.exit(0);
        }
        /////////////////////////////////////////////// GreyScale
        File photo = new File(args[1]);
        Picture cheetah = new Picture(photo);

        if (Integer.parseInt(args[0]) >= 0) {
            for (int x = 0; x < cheetah.width(); x++) {
                for (int y = 0; y < cheetah.height(); y++) {
                    Color currentColour = cheetah.get(x, y);
                    int r = currentColour.getRed();
                    int g = currentColour.getGreen();
                    int b = currentColour.getBlue();
                    cheetah.set(x, y, Greyscale.getGreyScale(r, g, b));
                }
            }
        }
        Picture cheetahGS = cheetah;
        String rename = photo.getName().substring(0,
                photo.getName().indexOf("."));
        String savePath = "../out/" + rename + "_GS.png";
        if (Integer.parseInt(args[0]) == 0) {
            cheetahGS.save(savePath);
        }
        if (gui && Integer.parseInt(args[0]) == 0) {
            cheetahGS.show();
        }
        //////////////////////////////////////////// Noise reduction
        Picture cheetahNR = null;
        if (Integer.parseInt(args[0]) >= 1) {
            cheetahNR = NoiseReduction.reduceNoise(cheetahGS);

            if (Integer.parseInt(args[0]) == 1) {
                cheetahNR.save("../out/" + rename + "_NR.png");
            }
        }
        if (gui && Integer.parseInt(args[0]) == 1) {
            cheetahNR.show();
        }
        ///////////////////////////////////////////// Edge Detection
        Picture cheetahED = null;
        if (Integer.parseInt(args[0]) >= 2) {
            cheetahED = EdgeDetection.detectBorder(cheetahNR,
                    Integer.parseInt(args[2]));
            if (Integer.parseInt(args[0]) == 2) {
                cheetahED.save("../out/" + rename + "_ED.png");
            }
        }
        if (gui && Integer.parseInt(args[0]) == 2) {
            cheetahED.show();
        }
        //////////////////////////////////////////// Spot Detection
        Picture cheetahSD;
        if (Integer.parseInt(args[0]) == 3) {
            int[][] config = new int[8][4];
            config[0][0] = 4;
            config[0][1] = 6;
            config[0][2] = 0;
            config[0][3] = 4800;
            config[1][0] = 5;
            config[1][1] = 9;
            config[1][2] = 1;
            config[1][3] = 6625;
            config[2][0] = 6;
            config[2][1] = 12;
            config[2][2] = 1;
            config[2][3] = 11000;
            config[3][0] = 7;
            config[3][1] = 15;
            config[3][2] = 1;
            config[3][3] = 15000;
            config[4][0] = 8;
            config[4][1] = 18;
            config[4][2] = 1;
            config[4][3] = 19000;
            config[5][0] = 9;
            config[5][1] = 21;
            config[5][2] = 1;
            config[5][3] = 23000;
            config[6][0] = 10;
            config[6][1] = 24;
            config[6][2] = 2;
            config[6][3] = 28000;
            config[7][0] = 11;
            config[7][1] = 27;
            config[7][2] = 2;
            config[7][3] = 35000;

            setSpots(cheetahED);
            int lB = Integer.parseInt(args[3]) - 4;
            int uB = Integer.parseInt(args[4]) - 4;
            for (int i = lB; i <= uB; i++) {
                findSpots(cheetahED, mask(config[i][0], config[i][1],
                        config[i][2]), config[i][3]);
            }
            cheetahSD = getSpots();
            cheetahSD.save("../out/" + rename + "_SD.png");
            if (gui && Integer.parseInt(args[0]) == 3) {
                cheetahSD.show();
            }
            System.out.println(getSpotsCounted());
        }
    }
}
