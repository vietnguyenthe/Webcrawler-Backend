package de.awa.training.webcrawler.repository;

import de.awa.training.webcrawler.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRespository extends JpaRepository<TestEntity,Integer> {
}
