package de.awa.training.webcrawler.repository;

import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PLZRepository extends JpaRepository<PostleitzahlenEntity,Integer> {
}
