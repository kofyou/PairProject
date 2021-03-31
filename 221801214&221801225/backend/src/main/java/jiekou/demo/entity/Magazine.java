package jiekou.demo.entity;

public class Magazine {
    private String magezine;
    private Integer number;
    private String publication_year;
    public Magazine(String magezine, Integer number,String publication_year) {
        this.magezine = magezine;
        this.number = number;
        this.publication_year=publication_year;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public String getPublication_year() {
        return publication_year;
    }

    public void setMagezine(String magezine) {
        this.magezine = magezine;
    }


    public String getMagezine() {
        return magezine;
    }

    public Integer getNumber() {
        return number;
    }
}
