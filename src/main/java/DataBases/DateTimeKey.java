package DataBases;
import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeKey implements Comparable<DateTimeKey> {
    private LocalDate date;
    private LocalTime time;

    public DateTimeKey(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public int compareTo(DateTimeKey other) {
        int dateCompare = this.date.compareTo(other.date);
        if (dateCompare != 0) {
            return dateCompare;
        }
        return this.time.compareTo(other.time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTimeKey that = (DateTimeKey) o;
        return date.equals(that.date) && time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return date.hashCode() * 31 + time.hashCode();
    }
}
