package pojo;

import java.util.ArrayList;

public class YearFrequency {

    private int year;//年份
    private ArrayList<Integer> frequency;//十大关键词在该年的频率

    public int getYear() {
        return year;
    }

    public ArrayList<Integer> getFrequency() {
        return frequency;
    }

    public void setFrequency(ArrayList<Integer> frequency) {
        this.frequency = frequency;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
