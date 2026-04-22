package spring_boot.session13_it210_bai5.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prescription_details")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PrescriptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @Column(name = "medicine_name")
    private String medicineName;

    private Integer quantity;
}