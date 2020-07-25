package aPermRange;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range rightRange) {
        if (this.to > rightRange.from && this.to < rightRange.to) {
            return new Range(rightRange.from, this.to);
        }

        if (rightRange.from < this.to && rightRange.to < this.to) {
            return new Range(rightRange.from, rightRange.to);
        }

        return null;
    }

    public Range[] getUnionRange(Range rightRange) {
        if (this.to >= rightRange.from) {
            Range[] unionRange = new Range[1];
            unionRange[0] = new Range(this.from, rightRange.to);

            return unionRange;
        }
        Range[] unionRange = new Range[2];
        unionRange[0] = new Range(this.from, this.to);
        unionRange[1] = new Range(rightRange.from, rightRange.to);

        return unionRange;
    }

    public Range[] getComplementRange(Range rightRange) {
        if (this.to > rightRange.to) {
            Range[] complementRange = new Range[2];
            complementRange[0] = new Range(this.from, rightRange.from);
            complementRange[1] = new Range(rightRange.to, this.to);

            return complementRange;
        }

        if (rightRange.to > this.to && rightRange.from < this.to) {
            Range[] complementRange = new Range[1];
            complementRange[0] = new Range(this.from, rightRange.from);

            return complementRange;
        }

        return null;
    }

    @Override
    public String toString() {
        return from + " , " + to;
    }
}
