package permogortseva.range;

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

    public Range getIntersection(Range range) {
        if (range.to <= from || to <= range.from) {
            return null;
        }

        return new Range(Math.max(range.from, from), Math.min(range.to, to));
    }

    public Range[] getUnion(Range range) {
        if (range.to < from || range.from > to) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(range.from, from), Math.max(to, range.to))};
    }

    public Range[] getComplement(Range range) {
        if (to > range.to && from < range.from) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (from < range.from && range.to > to && range.from < to) {
            return new Range[]{new Range(from, range.from)};
        }

        if (to > range.to && from > range.from && range.to > from) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[0];
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ")";
    }
}
