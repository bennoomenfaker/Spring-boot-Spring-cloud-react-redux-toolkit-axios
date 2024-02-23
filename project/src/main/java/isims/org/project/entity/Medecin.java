package isims.org.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 le medicin  peut travailler dans plusieurs cliniques


 Un médecin peut avoir plusieurs rendez-vous,
 *  tandis qu'un rendez-vous est lié à un seul médecin
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedecin;
    private String nomMedicin;
    private Specialite specialite;
    private int telephone;
    private int prixConsultation;

    @JsonIgnoreProperties("medecins")
    @ManyToMany(mappedBy = "medecins")
    private List<Clinique>  cliniques;

    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVousList;

}
