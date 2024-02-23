package isims.org.project.service;

import isims.org.project.entity.Medecin;
import isims.org.project.repository.MedecinRepository;

import java.util.List;

public interface MedecinService  {
    List<Medecin> getAllMedecins();
    Medecin getMedecinById(Long id);
    Medecin saveMedecin(Medecin medecin);
    void deleteMedecin(Long id);



}
