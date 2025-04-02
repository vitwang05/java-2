package com.java2.food.repository;

import com.java2.food.entity.Foods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Foods, Long> {
    @Query("SELECT f FROM Foods f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(f.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Foods> searchByKeyword(@Param("keyword") String keyword);
}
