package com.java2.food.repository;

import com.java2.food.entity.Cart;

import com.java2.food.entity.Foods;
import com.java2.food.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);
    @Transactional
    void deleteAllByUserId(Long userId);

}
