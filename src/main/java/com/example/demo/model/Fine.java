package com.example.demo.model;

import java.math.BigDecimal;

public class Fine {
    private Long id;
    private Long readerId;   // просто ID, без связи
    private Long bookId;     // просто ID, без связи
    private BigDecimal amount;
    private boolean paid;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getReaderId() { return readerId; }
    public void setReaderId(Long readerId) { this.readerId = readerId; }
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
}