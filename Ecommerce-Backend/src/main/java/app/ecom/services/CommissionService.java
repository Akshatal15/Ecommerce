package app.ecom.services;

import app.ecom.entities.Commission;
import app.ecom.repositories.CommissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommissionService {

    private final CommissionRepository commissionRepository;

    // Get all commissions
    public List<Commission> getAllCommissions() {
        return commissionRepository.findAll();
    }

    // Get commission by ID
    public Optional<Commission> getCommissionById(Integer id) {
        return commissionRepository.findById(id);
    }

    // Get commission by order item ID
    public Commission getCommissionByOrderItemId(Integer orderItemId) {
        return commissionRepository.findByOrderItemId(orderItemId);
    }

    // Create or update commission
    public Commission saveCommission(Commission commission) {
        return commissionRepository.save(commission);
    }

    // Delete commission by ID
    public void deleteCommission(Integer id) {
        commissionRepository.deleteById(id);
    }
}
