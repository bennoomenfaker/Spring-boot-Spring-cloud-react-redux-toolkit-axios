package isims.org.project.service;

import isims.org.project.entity.Medecin;
import isims.org.project.repository.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class MedecinServiceImpl implements MedecinService {

    MedecinRepository medecinRepository;



    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }


    @Override
    public Medecin getMedecinById(Long id) {
        return medecinRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medecin not found with id: " + id));
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }


}

