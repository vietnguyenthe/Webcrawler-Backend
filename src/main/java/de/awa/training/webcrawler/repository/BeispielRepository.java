package de.awa.training.webcrawler.repository;

import de.awa.training.webcrawler.entity.BeispielEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeispielRepository extends JpaRepository<BeispielEntity, Integer> {
}
