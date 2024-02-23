package isims.org.project.service;

import isims.org.project.entity.Clinique;
import isims.org.project.entity.Medecin;
import isims.org.project.repository.CliniqueRepository;
import isims.org.project.repository.MedecinRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CliniqueServiceImpl implements CliniqueService {

     CliniqueRepository cliniqueRepository;
     MedecinRepository medecinRepository;



    @Override
    public List<Clinique> getAllCliniques() {
        return cliniqueRepository.findAll();
    }

    @Override
    public Clinique getCliniqueById(Long id) {
        return cliniqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinique not found with id: " + id));
    }

    @Override
    public Clinique saveClinique(Clinique clinique) {
        return cliniqueRepository.save(clinique);
    }

    @Override
    public void deleteClinique(Long id) {
        cliniqueRepository.deleteById(id);
    }

    @Override
    public Clinique assignMedecinToClinique(Long cliniqueId, Long medecinId) {
        // Récupérer la clinique et le médecin depuis leurs identifiants
        Clinique clinique = cliniqueRepository.findById(cliniqueId)
                .orElseThrow(() -> new EntityNotFoundException("Clinique not found with id: " + cliniqueId));

        Medecin medecin = medecinRepository.findById(medecinId)
                .orElseThrow(() -> new EntityNotFoundException("Medecin not found with id: " + medecinId));

        // Vérifier si le médecin est déjà associé à la clinique
        if (clinique.getMedecins().contains(medecin)) {
            throw new IllegalStateException("Medecin is already assigned to Clinique");
        }

        // Associer le médecin à la clinique
        clinique.getMedecins().add(medecin);
        cliniqueRepository.save(clinique);

        return clinique;
    }



    @Override
    public Clinique removeMedecinFromClinique(Long cliniqueId, Long medecinId) {
        // Récupérer la clinique depuis son identifiant
        Clinique clinique = cliniqueRepository.findById(cliniqueId)
                .orElseThrow(() -> new EntityNotFoundException("Clinique not found with id: " + cliniqueId));

        // Récupérer le médecin depuis son identifiant
        Medecin medecin = medecinRepository.findById(medecinId)
                .orElseThrow(() -> new EntityNotFoundException("Medecin not found with id: " + medecinId));

        // Vérifier si le médecin est associé à la clinique
        if (!clinique.getMedecins().contains(medecin)) {
            throw new IllegalStateException("Medecin is not assigned to Clinique");
        }

        // Retirer le médecin de la clinique
        clinique.getMedecins().remove(medecin);
        cliniqueRepository.save(clinique);

        return clinique;
    }




}
