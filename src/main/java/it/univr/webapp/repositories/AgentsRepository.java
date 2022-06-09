package it.univr.webapp.repositories;

import it.univr.webapp.models.AgentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentsRepository extends JpaRepository<AgentsEntity,String> {
    String deleteByAgentCode(String agentCode);
}
