package isims.org.project.controller;

import isims.org.project.entity.Clinique;
import isims.org.project.entity.Medecin;
import isims.org.project.service.MedecinService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medecins")
@AllArgsConstructor
public class MedecinController {

   MedecinService medecinService;



    @GetMapping
    public ResponseEntity<List<Medecin>> getAllMedecins() {
        List<Medecin> medecins = medecinService.getAllMedecins();
        return ResponseEntity.ok(medecins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id) {
        Medecin medecin = medecinService.getMedecinById(id);
        return ResponseEntity.ok(medecin);
    }

    @PostMapping
    public ResponseEntity<Medecin> createMedecin(@RequestBody Medecin medecin) {
        Medecin createdMedecin = medecinService.saveMedecin(medecin);
        return new ResponseEntity<>(createdMedecin, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClinique(@PathVariable Long id, @RequestBody Medecin updatedMedecin) {
        try {
            Medecin existingMedecin = medecinService.getMedecinById(id);
            existingMedecin.setNomMedicin(updatedMedecin.getNomMedicin());
            existingMedecin.setSpecialite(updatedMedecin.getSpecialite());
            existingMedecin.setTelephone(updatedMedecin.getTelephone());
            existingMedecin.setPrixConsultation((updatedMedecin.getPrixConsultation()));

            Medecin savedMedecin = medecinService.saveMedecin(existingMedecin);
            return ResponseEntity.ok(savedMedecin);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "medecin not found"));
        }
    }
}
