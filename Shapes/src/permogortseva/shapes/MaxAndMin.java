package permogortseva.shapes;

public class MaxAndMin {
    public static int max(int x1, int x2, int x3) {
        if (x2 > x1) {
            return x2;
        }

        if (x3 > x1) {
            return x3;
        }

        return x1;
    }

    public static int min(int x1, int x2, int x3) {
        if (x2 < x1) {
            return x2;
        }

        if (x3 < x1) {
            return x3;
        }

        return x1;
    }
}
