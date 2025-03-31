package com.java2.food.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
     Order order;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
     Foods food;

    @Column(nullable = false)
     int quantity;

    @Column(nullable = false)
     Double price;


}
