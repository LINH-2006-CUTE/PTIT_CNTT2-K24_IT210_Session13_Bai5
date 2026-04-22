package spring_boot.session13_it210_bai5.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prescriptions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_name")
    private String patientName; // Thêm mã/tên bệnh nhân theo yêu cầu bài 5

    @Column(name = "doctor_name")
    private String doctorName;

    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PrescriptionDetail> details = new ArrayList<>();
    public void addDetail(PrescriptionDetail detail) {
        details.add(detail);
        detail.setPrescription(this);
    }
}