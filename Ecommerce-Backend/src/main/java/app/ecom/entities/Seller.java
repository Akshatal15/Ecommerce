package app.ecom.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sellers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonIgnoreProperties({
            "hibernateLazyInitializer",
            "handler",
            "role" // prevents lazy Role serialization chain
    })
    private User user;

    @Column(length = 50, nullable = false)
    private String storeName;

    @Column(length = 50, nullable = false, unique = true)
    private String gstNumber;

    @Lob
    private byte[] panCard;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    public enum ApprovalStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
