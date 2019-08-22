package de.awa.training.webcrawler.repository;

import de.awa.training.webcrawler.entity.PfiffiggasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PfiffiggasRepository extends JpaRepository<PfiffiggasEntity, Integer> {
}
