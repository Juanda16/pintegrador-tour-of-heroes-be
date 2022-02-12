package co.udea.hero.api.service;

import co.udea.hero.api.model.Hero;
import co.udea.hero.api.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HeroService {
    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private HeroRepository heroRepository;
    //private Messages messages;


    public HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
        //this.messages = messages;
    }

    public List<Hero> getHeroes(){
        //TODO mejorar el control de excepciones
        List<Hero> heroesList = heroRepository.findAll();
        return heroesList;
    }

    public Hero getHero(Integer id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if(!optionalHero.isPresent()){
            log.info("No se encuentra un heroe con ID:"+id);
            //throw new BusinessException(messages.get("exception.data_not_found.hero"));
        }
        return optionalHero.get();
        //return new Hero(1,"juan");
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

    public void deleteHero (Hero hero)
    {

        boolean heroAlreadyExist = heroRepository.existsById(hero.getId());
        if (heroAlreadyExist)heroRepository.delete(hero);

    }

    public List<Hero>  searchHeroes (String term){
    return heroRepository.search(term);
    }


}





