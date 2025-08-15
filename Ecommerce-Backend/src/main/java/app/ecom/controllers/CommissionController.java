package app.ecom.controllers;

import app.ecom.entities.Commission;
import app.ecom.services.CommissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commissions")
@RequiredArgsConstructor
public class CommissionController {

    private final CommissionService commissionService;

    // Get all commissions
    @GetMapping
    public List<Commission> getAllCommissions() {
        return commissionService.getAllCommissions();
    }

    // Get commission by ID
    @GetMapping("/{id}")
    public ResponseEntity<Commission> getCommissionById(@PathVariable Integer id) {
        return commissionService.getCommissionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get commission by order item ID
    @GetMapping("/order-item/{orderItemId}")
    public ResponseEntity<Commission> getCommissionByOrderItem(@PathVariable Integer orderItemId) {
        Commission commission = commissionService.getCommissionByOrderItemId(orderItemId);
        if (commission != null) return ResponseEntity.ok(commission);
        return ResponseEntity.notFound().build();
    }

    // Create a new commission
    @PostMapping
    public Commission createCommission(@RequestBody Commission commission) {
        return commissionService.saveCommission(commission);
    }

    // Update commission
    @PutMapping("/{id}")
    public ResponseEntity<Commission> updateCommission(@PathVariable Integer id, @RequestBody Commission updatedCommission) {
        return commissionService.getCommissionById(id)
                .map(existing -> {
                    existing.setOrderItem(updatedCommission.getOrderItem());
                    existing.setPlatformFee(updatedCommission.getPlatformFee());
                    existing.setCommissionPercentage(updatedCommission.getCommissionPercentage());
                    existing.setCommissionAmount(updatedCommission.getCommissionAmount());
                    Commission saved = commissionService.saveCommission(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete commission
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommission(@PathVariable Integer id) {
        if (commissionService.getCommissionById(id).isPresent()) {
            commissionService.deleteCommission(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

