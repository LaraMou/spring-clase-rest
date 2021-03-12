package com.example.springclaserest.repository;

import com.example.springclaserest.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor,Long> {
  //  Profesor findByMail(String mail);

  Optional<Profesor> findByMail(String mail);

   List<Profesor> findBySuplente(Boolean suplente);

   List<Profesor> findAllByEdadAfter(Integer edad);
}
