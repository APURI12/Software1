import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This project takes five numbers and uses four of them to try to estimate the
 * last one with the closest combination of indexes.
 *
 *
 * @author Ariv Puri
 */
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        // Declared my variables
        double bNum = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        final double[] powers = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4, 0,
                1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };
        int i = 0;
        double bestEstimate = 1;
        out.println(bNum);
        while (i < powers.length) {
            int j = 0;
            while (j < powers.length) {
                int k = 0;
                while (k < powers.length) {
                    int l = 0;
                    while (l < powers.length) {
                        //checks every index for the closest possible estimate to
                        //the number the user entered.
                        if (Math.abs(bestEstimate - bNum) > Math
                                .abs((Math.pow(w, i) * Math.pow(x, j)
                                        * Math.pow(y, k) * Math.pow(z, l))
                                        - bNum)) {
                            bestEstimate = Math.pow(w, i) * Math.pow(x, j)
                                    * Math.pow(y, k) * Math.pow(z, l);
                            a = i;
                            b = j;
                            c = k;
                            d = l;
                        }
                        l++;
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        double error = (bestEstimate - bNum) / bNum;
        out.println("The closest estimate with your numbers is " + bestEstimate
                + " which can be found by multiplying " + w + "^" + a + ", " + x
                + "^" + b + ", " + y + "^" + c + ", and " + z + "^" + d
                + ". The difference between your "
                + "estimate and your number is " + error + "%.");
        out.close();
        in.close();
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        int i = 0;
        String input;
        double d = 0;
        while (i == 0) {
            out.println("Enter a positive real number:");
            input = in.nextLine();
            if (FormatChecker.canParseDouble(input)) {
                d = Double.parseDouble(input);
                return d;
            }
        }
        return d;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        int i = 0;
        String input;
        double d = 0;
        while (i == 0) {
            out.println("Enter a positive real number:");
            input = in.nextLine();
            if (FormatChecker.canParseDouble(input)) {
                d = Double.parseDouble(input);
                if (d != 1.0) {
                    return d;
                }
            }
        }
        return d;
    }

}
