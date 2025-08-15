package app.ecom.services;

import app.ecom.entities.Cart;
import app.ecom.entities.User;
import app.ecom.repositories.CartRepository;
import app.ecom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a cart for a user
    public Cart createCartForUser(int userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User with ID " + userId + " not found");
        }

        // Prevent duplicate cart for same user
        if (cartRepository.findByUser_Id(userId).isPresent()) {
            throw new RuntimeException("Cart already exists for this user");
        }

        Cart cart = new Cart();
        cart.setUser(userOpt.get());
        return cartRepository.save(cart);
    }

    // Get cart by user ID
    public Cart getCartByUserId(int userId) {
        return cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID " + userId));
    }

    // Delete cart by cart ID
    public void deleteCart(int cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new RuntimeException("Cart with ID " + cartId + " not found");
        }
        cartRepository.deleteById(cartId);
    }
}
