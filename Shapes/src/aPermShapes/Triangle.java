package aPermShapes;

public class Triangle implements Shape {
    private int x1;
    private int x2;
    private int x3;
    private int y1;
    private int y2;
    private int y3;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return (MaxAndMin.max(x1, x2, x3) - MaxAndMin.min(x1, x2, x3));
    }

    @Override
    public double getHeight() {
        return (MaxAndMin.max(y1, y2, y3) - MaxAndMin.min(y1, y2, y3));
    }

    @Override
    public double getArea() {
        return 0.5 * Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1));
    }

    @Override
    public double getPerimeter() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) + Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2)) +
                Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
    }

    @Override
    public String toString() {
        return "x1 = " + x1 + ", " + "y1 = " + y1 + ", " + "x2 = " + x2 + ", " + "y2 = " + y2 + ", " + "x3 = " + x3 + ", " + "y3 = " + y3;
    }

    @Override
    public int hashCode() {
        final int prime = 33;
        int hash = 1;

        hash = prime * hash + x1;
        hash = prime * hash + x2;
        hash = prime * hash + x3;
        hash = prime * hash + y1;
        hash = prime * hash + y2;
        hash = prime * hash + y3;

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;
        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2 && x3 == triangle.x3 && y3 == triangle.y3;
    }
}

