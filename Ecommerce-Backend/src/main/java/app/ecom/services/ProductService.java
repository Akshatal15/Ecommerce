package app.ecom.services;



import app.ecom.entities.Product;
import app.ecom.entities.Seller;
import app.ecom.entities.Categories;
import app.ecom.exceptions.ResourceNotFoundException;
import app.ecom.repositories.ProductRepository;
import app.ecom.repositories.SellerRepository;
import app.ecom.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    // Create Product
    public Product createProduct(Product product, int sellerId, int categoryId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found with id: " + sellerId));

        Categories category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        product.setSeller(seller);
        product.setCategory(category);

        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Product by ID
    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    // Update Product
    public Product updateProduct(int id, Product productDetails) {
        Product existingProduct = getProductById(id);

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setStock(productDetails.getStock());
        existingProduct.setImagePath(productDetails.getImagePath());

        return productRepository.save(existingProduct);
    }

    // Delete Product
    public void deleteProduct(int id) {
        Product existingProduct = getProductById(id);
        productRepository.delete(existingProduct);
    }
}

