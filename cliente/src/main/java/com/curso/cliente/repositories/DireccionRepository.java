
package com.curso.cliente.repositories;

import com.curso.dominiosMysql.Domain.Cliente;
import com.curso.dominiosMysql.Domain.Direccion;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface DireccionRepository extends Repository<Direccion, Integer> {




    Optional<Direccion> findById(Integer id);

    Object findAll();

    Direccion save(Direccion d);

    void delete(Direccion d);


    List<Direccion> findByIdcliente(Integer id);

    List<Direccion> findByProvincia(String provincia);
}
