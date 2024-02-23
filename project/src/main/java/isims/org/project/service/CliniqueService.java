package isims.org.project.service;

import isims.org.project.entity.Clinique;

import java.util.List;

public interface CliniqueService {
    List<Clinique> getAllCliniques();
    Clinique getCliniqueById(Long id);
    Clinique saveClinique(Clinique clinique);
    void deleteClinique(Long id);
    Clinique assignMedecinToClinique(Long cliniqueId, Long medecinId);
    Clinique removeMedecinFromClinique(Long cliniqueId, Long medecinId);



}
