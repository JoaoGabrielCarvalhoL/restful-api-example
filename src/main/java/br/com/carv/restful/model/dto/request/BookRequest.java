package br.com.carv.restful.model.dto.request;

import java.math.BigDecimal;

public class BookRequest {

    private String author;
    private BigDecimal price;
    private String title;

    public BookRequest() {
    }

    public BookRequest(String author, BigDecimal price, String title) {
        this.author = author;
        this.price = price;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
