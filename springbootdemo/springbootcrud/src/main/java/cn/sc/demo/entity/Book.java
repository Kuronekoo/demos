package cn.sc.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "db_book")
public class Book {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String bookName;
    @Column
    private Double price;
    @Column
    private Date publishDate;


    public Book(String bookName, Double price, Date publishDate) {
        this.bookName = bookName;
        this.price = price;
        this.publishDate = publishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
