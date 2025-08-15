package app.ecom.repositories;

import app.ecom.entities.Categories;
import app.ecom.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
}
