package com.pairproject.papercv.model;

import java.util.List;

/**
 * 热词类
 *
 * @date 2021/03/25
 */
public class Word {
    private String value;
    private List<String> years;
    private List<Integer> counts;

    public Word(String value, List<String> years, List<Integer> counts) {
        this.value = value;
        this.years = years;
        this.counts = counts;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public void setCounts(List<Integer> counts) {
        this.counts = counts;
    }
}
