package it.univr.webapp.repositories.orders;

import it.univr.webapp.models.orders.AgentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentsRepository extends JpaRepository<AgentsEntity,String> {
    Integer deleteByAgentCode(String agentCode);
    List<AgentsEntity> findByOrderByAgentCodeAsc();

}
