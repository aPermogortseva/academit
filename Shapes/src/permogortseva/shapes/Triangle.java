package permogortseva.shapes;

public class Triangle implements Shape {
    final private double x1;
    final private double x2;
    final private double x3;
    final private double y1;
    final private double y2;
    final private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    private static double max(double coordinate1, double coordinate2, double coordinate3) {
        if (coordinate2 > coordinate1) {
            return coordinate2;
        }

        return Math.max(coordinate3, coordinate1);
    }

    private static double min(double coordinate1, double coordinate2, double coordinate3) {
        if (coordinate2 < coordinate1) {
            return coordinate2;
        }

        return Math.min(coordinate3, coordinate1);
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public double getWidth() {
        return (max(x1, x2, x3) - min(x1, x2, x3));
    }

    @Override
    public double getHeight() {
        return (max(y1, y2, y3) - min(y1, y2, y3));
    }

    @Override
    public double getArea() {
        return 0.5 * Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1));
    }

    @Override
    public double getPerimeter() {
        return getSideLength(x1, y1, x2, y2) + getSideLength(x3, y3, x2, y2) + getSideLength(x3, y3, x1, y1);
    }

    @Override
    public String toString() {
        return "Треугольник. Координаты вершин: x1 = " + x1 + ", " + "y1 = " + y1 + ", " + "x2 = " + x2 + ", " + "y2 = " + y2 + ", " + "x3 = " + x3 + ", " + "y3 = " + y3;
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;
        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2 && x3 == triangle.x3 && y3 == triangle.y3;
    }
}

