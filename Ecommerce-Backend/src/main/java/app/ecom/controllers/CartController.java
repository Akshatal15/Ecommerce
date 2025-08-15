package app.ecom.controllers;

import app.ecom.entities.Cart;
import app.ecom.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    // Create cart for a user
    @PostMapping("/user/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable int userId) {
        Cart createdCart = cartService.createCartForUser(userId);
        return ResponseEntity.ok(createdCart);
    }

    // Get cart by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    // Delete cart by cart ID
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable int cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok("Cart deleted successfully");
    }
}
