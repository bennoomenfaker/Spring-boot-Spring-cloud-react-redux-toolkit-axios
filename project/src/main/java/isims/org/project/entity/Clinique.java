package isims.org.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 le clinique  contient plusieurs medicins
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Clinique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClinique;
    private String nomClinique;
    private String adresse;
    private int telephone;
    @JsonIgnoreProperties("cliniques")
    @ManyToMany
    private List<Medecin> medecins;
}
