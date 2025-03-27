package com.java2.food.entity;

import java.math.BigDecimal;


import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;

import jakarta.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Foods")
public class Foods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name",nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String name;

    @Column(name = "price", unique = true,nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    BigDecimal price;

    @Column(name = "image", columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String image;

    @Column(name = "category",nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String category;
}
