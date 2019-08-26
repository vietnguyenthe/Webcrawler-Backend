package de.awa.training.webcrawler.repository;

import de.awa.training.webcrawler.entity.KontaktanfrageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KontaktanfrageRepository extends JpaRepository<KontaktanfrageEntity, Integer> {
}
