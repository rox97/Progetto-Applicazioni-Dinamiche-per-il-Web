package it.univr.webapp.repositories.orders;

import it.univr.webapp.models.orders.AgentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentsRepository extends JpaRepository<AgentsEntity,String> {
    String deleteByAgentCode(String agentCode);
}
