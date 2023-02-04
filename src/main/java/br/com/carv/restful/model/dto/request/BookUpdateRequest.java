package br.com.carv.restful.model.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookUpdateRequest {

    private Long id;
    private String author;
    private BigDecimal price;
    private String title;

    public BookUpdateRequest() {
    }

    public BookUpdateRequest(Long id, String author, BigDecimal price, String title) {
        this.id = id;
        this.author = author;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
