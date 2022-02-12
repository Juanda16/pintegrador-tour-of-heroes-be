package co.udea.hero.api.controller;

import co.udea.hero.api.model.Hero;
import co.udea.hero.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Busca un hero por su id",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHero(@PathVariable Integer id){
        log.info("Rest request buscar heroe por id: "+ id);
        return ResponseEntity.ok(heroService.getHero(id));
    }// getHero(id) - @ GetMapping ()

    @GetMapping("")
    @ApiOperation(value = "Buscar todos los heroes",  response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes(){
        log.info("Rest request buscar heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    // addHero (hero) ) - @RequestMapping("crear")
    @PostMapping(
            value = "crear",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Crear un Hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public void createHero(@RequestBody Hero hero){

        log.info(hero.toString());
        log.info("Rest request crear hero");

        ResponseEntity.ok(heroService.createHero(hero));
    }

    //updateHero (hero) ) - @RequestMapping("actualizar")
    @PutMapping(
            value = "/actualizar",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Editar un Hero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero){

        log.info(hero.toString());
        log.info("Rest request crear hero");

        return ResponseEntity.ok(heroService.updateHero(hero));
    }

    // deleteHero (hero) - @RequestMapping("borrar")
    @DeleteMapping(value = "borrar",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void deleteHero (@RequestBody Hero hero){
        heroService.deleteHero(hero);
         ResponseEntity.ok();
    }

    //searchHeroes (term) ) - @RequestMapping("buscar")

    @RequestMapping(name = "buscar")
    public ResponseEntity<List<Hero>> searchHeroes(@RequestBody String term){
        return ResponseEntity.ok(heroService.searchHeroes(term));
    }

}


// getHeroNo404<Data>(id: number) ) - @RequestMapping("consultar404")
// searchHeroes (term) ) - @RequestMapping("buscar") .
//

