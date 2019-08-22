package de.awa.training.webcrawler.repository;

import de.awa.training.webcrawler.entity.UnternehmenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnternehemensRepository extends JpaRepository<UnternehmenEntity, Integer> {
}
