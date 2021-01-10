package permogortseva.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Неверный размер = " + size + ". Размер не может быть меньше 1");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Неверный размер массива = " + array.length + ". Размер не может быть = 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Неверный размер = " + size + ". Размер не может быть меньше 1");
        }

        components = new double[size];

        System.arraycopy(array, 0, components, 0, Math.min(size, array.length));
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiply(-1);
    }

    public double getElement(int index) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс не должен быть больше размерности вектора. " +
                    "Размерность вектора = " + components.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть > 0");
        }

        return components[index];
    }

    public void setElement(int index, double value) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс не должен быть больше размерности вектора. " +
                    "Размерность вектора = " + components.length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс = " + index + ". Индекс должен быть > 0");
        }

        components[index] = value;
    }

    public double getLength() {
        double componentsSquaresSum = 0;

        for (double e : components) {
            componentsSquaresSum += e * e;
        }

        return Math.sqrt(componentsSquaresSum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');

        for (double e : components) {
            sb.append(e);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return sb.append('}').toString();
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int hash = 1;

        hash = prime * hash + components.length;
        hash = prime * hash + Arrays.hashCode(components);

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

        Vector vector = (Vector) o;

        return Arrays.equals(components, vector.components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(vector1);

        newVector.add(vector2);

        return newVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector newVector = new Vector(vector1);

        newVector.subtract(vector2);

        return newVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;
        int minVectorSize = Math.min(vector2.components.length, vector1.components.length);

        for (int i = 0; i < minVectorSize; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}