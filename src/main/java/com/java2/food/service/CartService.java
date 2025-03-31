package com.java2.food.service;



import com.java2.food.dto.request.CartCreationRequest;
import com.java2.food.dto.response.CartReponse;
import com.java2.food.entity.Cart;
import com.java2.food.entity.Foods;
import com.java2.food.entity.Users;
import com.java2.food.exception.AppException;
import com.java2.food.exception.ErrorCode;
import com.java2.food.mapper.CartMapper;
import com.java2.food.repository.CartRepository;


import com.java2.food.repository.FoodRepository;
import com.java2.food.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {
    CartRepository cartRepository;
    CartMapper cartMapper;
    UserRepository userRepository;
    FoodRepository foodRepository;
    public List<CartReponse> getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .stream()
                .map(cartMapper::toCartResponse)
                .collect(Collectors.toList());
    }

    public CartReponse addToCart(CartCreationRequest request){
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Foods food = foodRepository.findById(request.getFoodId())
                .orElseThrow(() -> new AppException(ErrorCode.FOOD_NOT_EXISTED));

        Cart cart = cartMapper.toCart(request,user,food);

        try{
            cart =cartRepository.save(cart);
        }catch (DataIntegrityViolationException exception){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return cartMapper.toCartResponse(cart);
    }

    public void removeCartItem(Long cartId){
        try{
            if (!cartRepository.existsById(cartId)){
                throw new AppException(ErrorCode.FOOD_NOT_EXISTED);
            }
            cartRepository.deleteById(cartId);
        }catch (DataAccessException exception){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }
    }


    public void removeAllCartItem(Long userId){
        try{
            if (!cartRepository.existsById(userId)){
                throw new AppException(ErrorCode.USER_NOT_EXISTED);
            }
            cartRepository.deleteAllByUserId(userId);
        }catch (DataAccessException exception){
            throw new AppException(ErrorCode.DATABASE_ERROR);
        }
    }

    public int totalItem(Long userId){
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty for user: " + userId);
        }
        return cartItems.stream().mapToInt(Cart::getQuantity).sum();
    }
}
