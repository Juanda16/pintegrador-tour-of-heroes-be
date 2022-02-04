package co.udea.hero.api.service;

import co.udea.hero.api.model.Hero;
import co.udea.hero.api.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {
HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }
    public List<Hero> getHeroes ()
    {
          return heroRepository.findAll();

    }

    public Hero getHero (Integer id)
    {
        //Optional<Hero> optionalHero = heroRepository.findById(id);
        //return optionalHero.get();
        return new Hero(1,"Juan David");
    }

    public Hero updateHero (Hero hero)
    {

        boolean heroAlreadyExist = heroRepository.existsById(hero.getId());

        if (heroAlreadyExist) return null;
        return heroRepository.save(hero);

    }
    public Hero createHero (Hero hero)
    {
        boolean heroAlreadyExist = heroRepository.existsById(hero.getId());
        if (heroAlreadyExist) return null;
        return heroRepository.save(hero);
    }

    public void deleteHero (Integer id)
    {
        boolean heroAlreadyExist = heroRepository.existsById(id);
        if (heroAlreadyExist) heroRepository.deleteById(id);

    }


}