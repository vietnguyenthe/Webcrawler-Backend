package de.awa.training.webcrawler.repository;

import de.awa.training.webcrawler.entity.MeinfluessiggasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeinFluessiggasRepository extends JpaRepository<MeinfluessiggasEntity,Integer> {
}