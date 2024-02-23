package isims.org.project.repository;

import isims.org.project.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository  extends JpaRepository<RendezVous,Long> {
}
