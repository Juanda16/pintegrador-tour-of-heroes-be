package co.udea.hero.api.repository;

import co.udea.hero.api.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  HeroRepository extends JpaRepository<Hero,Integer> {
    @Query("SELECT h FROM Hero h WHERE h.name LIKE %?1%"

    )public List<Hero> search(String keyword);
}
