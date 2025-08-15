
package app.ecom.repositories;

import app.ecom.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    // Optional: find by category name
    Categories findByCategoryName(Categories.CategoryName categoryName);
}
