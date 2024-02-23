package isims.org.project.controller;


import isims.org.project.entity.Clinique;
import isims.org.project.entity.Medecin;
import isims.org.project.service.CliniqueService;
import isims.org.project.service.MedecinService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliniques")
@AllArgsConstructor
public class CliniqueController {

    CliniqueService cliniqueService;
    MedecinService medecinService;


    @GetMapping
    public ResponseEntity<List<Clinique>> getAllClinique() {
        List<Clinique> cliniques = cliniqueService.getAllCliniques();
        return ResponseEntity.ok(cliniques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinique> getCliniqueById(@PathVariable Long id) {
        Clinique clinique = cliniqueService.getCliniqueById(id);
        return ResponseEntity.ok(clinique);
    }

    @PostMapping
    public ResponseEntity<Clinique> createClinique(@RequestBody Clinique clinique) {
        Clinique createdClinique = cliniqueService.saveClinique(clinique);
        return new ResponseEntity<>(createdClinique, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClinique(@PathVariable Long id, @RequestBody Clinique updatedClinique) {
        try {
            Clinique existingClinique = cliniqueService.getCliniqueById(id);
            existingClinique.setNomClinique(updatedClinique.getNomClinique());
            existingClinique.setAdresse(updatedClinique.getAdresse());
            existingClinique.setTelephone(updatedClinique.getTelephone());

            Clinique savedClinique = cliniqueService.saveClinique(existingClinique);
            return ResponseEntity.ok(savedClinique);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Clinique not found"));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinique(@PathVariable Long id) {
        cliniqueService.deleteClinique(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cliniqueId}/assignMedecin/{medecinId}")
    public ResponseEntity<?> assignMedecinToClinique(@PathVariable Long cliniqueId, @PathVariable Long medecinId) {
        try {
            Clinique updatedClinique = cliniqueService.assignMedecinToClinique(cliniqueId, medecinId);
            return ResponseEntity.ok(updatedClinique);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }


    @DeleteMapping("/{cliniqueId}/removeMedecin/{medecinId}")
    public ResponseEntity<?> removeMedecinFromClinique(@PathVariable Long cliniqueId, @PathVariable Long medecinId) {
        try {
            Clinique updatedClinique = cliniqueService.removeMedecinFromClinique(cliniqueId, medecinId);
            return ResponseEntity.ok(updatedClinique);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }







}
