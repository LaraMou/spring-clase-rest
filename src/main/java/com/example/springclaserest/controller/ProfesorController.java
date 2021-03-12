package com.example.springclaserest.controller;

import com.example.springclaserest.model.Profesor;
import com.example.springclaserest.repository.ProfesorRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@ApiOperation(value = "Find all profesor")
public class ProfesorController {
    private final Logger log = LoggerFactory.getLogger(ProfesorController.class);
    private final ProfesorRepository repository;

    public ProfesorController(ProfesorRepository repository) {
        this.repository = repository;
    }
    //CRUD:

    /**
     * Devuelve profe
     * @return
     */
    @GetMapping("/profesor")

    public List<Profesor>  findProfesors() {
        log.debug("REST request to find all Profesores");
        return repository.findAll();
    }
    @GetMapping("/profesor/{id}")
    @ApiOperation("Find Profesor by id")
    public ResponseEntity<Profesor> findOne(@ApiParam("Clave primaria") @PathVariable Long id) {
        log.debug("REST request to finde one profe by id:{}", id);
        Optional<Profesor> profeOpt = repository.findById(id);
//        if (profeOpt.isPresent())
//           return ResponseEntity.ok().body(profeOpt.get());
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return profeOpt.map(profesor -> ResponseEntity.ok().body(profesor)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    //create new

    /**
     * Se envia información para crear un nuevo empleado
     * Si lo que llega es un id nulo , se crea uno nuevo
     * si no se dirá que es bad_request
     * Al crear
     * @param prof
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/profesor")
    public ResponseEntity<Profesor> createProfe(@RequestBody Profesor prof) throws URISyntaxException {
        log.debug("MLO REST request to save a profesor{}",prof);
        if(prof.getId()!=null) //Error, aqui solo para crear
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Profesor createdProfes =repository.save(prof);

        return ResponseEntity
                .created(new URI("/api/profesor/"+createdProfes.getId()))
                .body(createdProfes);

    }
    //UPDATE ONE
    /**
     * Actualizar
     */
    @PutMapping("/profesor")
    public ResponseEntity<Profesor> updateEmployee(@RequestBody Profesor profesor){
        log.debug("MLO REST request to update Profe:{}",profesor);
        if(profesor.getId()==null){
            log.warn("Updating profesor without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Profesor profeDB = repository.save(profesor);
        return ResponseEntity.ok().body(profeDB);

    }
    //RETRIEVE BY PROPERTY
    //NOTA si en la url viene un valor
    //"/profesor/mail/{mailraro
    // en el PathVariable("mailraro") tiene que existir
    // la variable que apunta al
    //todo doesn't work
    @GetMapping("/profesor/mail/{mail}")
    public ResponseEntity<Profesor> findByMail(@PathVariable String mail){
        log.info("REST request to find one profesor by email:{}",mail);

        Optional<Profesor> profeopt = repository.findByMail(mail);
        return profeopt.map(profesor -> ResponseEntity.ok().body(profesor)).orElseGet(
                () -> ResponseEntity.notFound().build());
//         Optional<Profesor> profeopt = repository.findByMail(mail);
//        if(profeopt.isPresent())
//            return ResponseEntity.ok().body(profeopt.get());
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Filter by propierty multiple results
     */
    @GetMapping("/profesor/suplente/{suplente}")
    public ResponseEntity<List<Profesor>> filterBySuplente(@PathVariable Boolean suplente){
        log.debug("REST filter by suplente:{}",suplente);
        List<Profesor> profesors = repository.findBySuplente(suplente);

        if(profesors.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(profesors);
    }
    //TODO CALCULAR SALARIO
    /*
    1- Recibir id
    2- Calculo salario en base a rendimiento
    if yearsInCompany > 10
    setSalary
    3- persistir empleado con el nuevo salario en base de datos
    4-Devolver profesor con el salario
     */
    @GetMapping("/profesor/calculate-salary/{id}")
    public ResponseEntity<Profesor> calculateSalary(@PathVariable Long id){
        log.debug("REST request to calculate id:{}",id);
         Optional<Profesor>  profopt= repository.findById(id);
         if(!profopt.isPresent())
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         Profesor profe = profopt.get();

         //tratar los nulos
        if(profe.getYearsInCompany()==null)
            return ResponseEntity.ok().body(profe);
         if(profe.getYearsInCompany()< 5){
             profe.setSalario(1000D);

         }else if(profe.getYearsInCompany()> 10 ){
             profe.setSalario(6000D);
        }else {
             profe.setSalario(8000D);
         }
         repository.save(profe);

         return ResponseEntity.ok().body(repository.save(profe));

    }

    /**
     * Borrado profesor
     * @param id
     * @return
     */
    //delete one
    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<Void> deleteProfe(@PathVariable Long id) {
        log.debug("REST request to delete profe by id{}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
    //DELETE ALL
    @DeleteMapping("/profesor")
    public ResponseEntity<Void> deleteAll() {
        log.debug("REST request for deleting ");
        repository.deleteAll();
        return ResponseEntity.noContent().build();

    }

    //FILTRAR POR AGE



    @GetMapping("/profesor/age-greater/{edad}")
    public ResponseEntity<List<Profesor>> filerByAgeGreater(@PathVariable Integer edad) {
        log.debug("REST Requtest to filter employees");
        List<Profesor> profesors = repository.findAllByEdadAfter(edad);
        if (profesors.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(profesors);
    }


}
