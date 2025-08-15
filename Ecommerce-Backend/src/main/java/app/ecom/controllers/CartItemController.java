package app.ecom.controllers;



import app.ecom.entities.CartItem;
import app.ecom.services.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    // Get all cart items
    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    // Get cart item by ID
    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Integer id) {
        return cartItemService.getCartItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new cart item
    @PostMapping
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.saveCartItem(cartItem);
    }

    // Update cart item
    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Integer id, @RequestBody CartItem updatedItem) {
        return cartItemService.getCartItemById(id)
                .map(existingItem -> {
                    existingItem.setCart(updatedItem.getCart());
                    existingItem.setProduct(updatedItem.getProduct());
                    existingItem.setQuantity(updatedItem.getQuantity());
                    CartItem saved = cartItemService.saveCartItem(existingItem);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete cart item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Integer id) {
        if (cartItemService.getCartItemById(id).isPresent()) {
            cartItemService.deleteCartItem(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

