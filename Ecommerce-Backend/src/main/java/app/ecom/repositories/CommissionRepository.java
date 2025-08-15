package app.ecom.repositories;

import app.ecom.entities.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {

    // Find commission by order item ID
    @Query("SELECT c FROM Commission c WHERE c.orderItem.id = :orderItemId")
    Commission findByOrderItemId(@Param("orderItemId") Integer orderItemId);
}

