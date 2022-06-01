package it.univr.webapp.repositories;

import it.univr.webapp.models.AgentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentsRepository extends JpaRepository<AgentsEntity, String>{
}
