package com.ecommerce.bex.model;

import com.ecommerce.bex.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Setter
    private ProductStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime soldAt;
    private LocalDateTime sentAt;
    private LocalDateTime receivedAt;

    @ManyToOne
    @JoinColumn(name="owner_id")
    public User owner;

    public Product(String username){
        this.username = username;
        this.createdAt = LocalDateTime.now();
        this.status = ProductStatus.CREATED;
    }

    public void nextStatus(){
        this.status.nextStatus(this);
    }

    public void setSoldDate(){
        this.soldAt = LocalDateTime.now();
    }

    public void setSentDate(){
        this.sentAt = LocalDateTime.now();
    }

    public void setReceivedDate(){
        this.receivedAt = LocalDateTime.now();
    }
}
