package com.java2.food.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    Users user;

    @Column(nullable = true)
    Double amount;

    @Column(nullable = false)
    String address;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Food Processing'")
    String status = "Food Processing";

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date date = new Date();

    @Column(nullable = false)
    Boolean payment ;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    Set<OrderItems> orderItems = new HashSet<>();

    @PrePersist
    public void setDefaultValues() {
        if (payment == null) {
            payment = false;
        }
        if (status == null) {
            status = "Food Processing";
        }
        if (date == null) {
            date = new Date();
        }
    }


}
