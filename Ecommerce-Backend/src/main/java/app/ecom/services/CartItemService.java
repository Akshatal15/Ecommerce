package app.ecom.services;



import app.ecom.entities.CartItem;
import app.ecom.repositories.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    // Get all cart items
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    // Get cart item by ID
    public Optional<CartItem> getCartItemById(Integer id) {
        return cartItemRepository.findById(id);
    }

    // Save or update cart item
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    // Delete cart item by ID
    public void deleteCartItem(Integer id) {
        cartItemRepository.deleteById(id);
    }

    // Optional: get items by cart ID
    public List<CartItem> getItemsByCartId(Integer cartId) {
        return cartItemRepository.findByCartCartId(cartId);
    }
}

