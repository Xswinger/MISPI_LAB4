package webdeving.verify;

import java.util.ArrayList;
import java.util.List;

public class Verifier {
    private final int[] R_MIN_MAX = new int[]{2,  5};

    public boolean verifyHit(double x, double y, int r) {
        return verifyFirstQuater(x, y, r) || verifySecondQuater(x, y, r) || verifyThirdQuater(x, y, r) || verifyFourthQuater(x, y, r);
    }

    private boolean verifyFirstQuater(final double x, final double y, final int r) {
        return (x >= 0 && y >= 0) && (y <= (-x/2 + r/2d));
    }

    private boolean verifySecondQuater(final double x, final double y, final int r) {
        return false;
    }

    private boolean verifyThirdQuater(final double x, final double y, final int r) {
        return (x <= 0 && y <= 0) && (y >= -r/2d && x >= -r);
    }

    private boolean verifyFourthQuater(final double x, final double y, final int r) {
        return (x >= 0 && y <= 0) && (x*x + y*y <= (r*r/4d));
    }

    public boolean verifyR(int r) {
        return r >= R_MIN_MAX[0] && r <= R_MIN_MAX[1];
    }

}
