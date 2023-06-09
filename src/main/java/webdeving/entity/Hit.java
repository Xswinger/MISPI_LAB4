package webdeving.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Table(name = "hits")
@Entity
public class Hit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;
    @Column
    private double x;
    @Column
    private double y;
    @Column
    private String yLine;
    @Column
    private int r;
    @Column
    private long exTime;
    @Column
    private boolean success;
    @Column
    private String date;

    public String getSuccessLine() {
        return successLine;
    }

    @Column
    private String successLine;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (y == Double.MIN_VALUE) {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            Number number = null;
            try {
                number = format.parse(this.yLine);
                this.y = number.doubleValue();
            } catch (ParseException e) {
                this.y = Double.MIN_VALUE;
            }
        } else {
            this.y = y;
        }

    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public long getExTime() {
        return exTime;
    }

    public void setExTime(long exTime) {
        this.exTime = exTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        this.successLine = success ? "Gotcha" : "Miss";
    }

    public String getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = date.format(formatter);
    }
    public String getyLine() {
        return yLine;
    }

    public void setyLine(String yLine) {
        this.yLine = yLine;
        setY(Double.MIN_VALUE);
    }

}
