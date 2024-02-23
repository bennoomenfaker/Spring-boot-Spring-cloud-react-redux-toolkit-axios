package isims.org.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/*
Un médecin peut avoir plusieurs rendez-vous,
*  tandis qu'un rendez-vous est lié à un seul médecin

Un rendez-vous est associé à un seul patient,
 tandis qu'un patient peut avoir plusieurs rendez-vous
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRDV;
    private Date dateRDV;
    private String remarque;

    @ManyToOne
    private Medecin medecin;

    @ManyToOne
    private Patient patient;
}
