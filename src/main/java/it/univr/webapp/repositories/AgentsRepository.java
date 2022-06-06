package it.univr.webapp.repositories;

import it.univr.webapp.models.AgentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentsRepository extends JpaRepository<AgentsEntity,String> {
}
