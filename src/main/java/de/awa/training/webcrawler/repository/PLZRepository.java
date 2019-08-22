package de.awa.training.webcrawler.repository;

import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PLZRepository extends JpaRepository<PostleitzahlenEntity,Integer> {

    @Query("SELECT id from postleitzahlen WHERE plz=?1")
    Integer findAllByPlz(String PLZ);
}
