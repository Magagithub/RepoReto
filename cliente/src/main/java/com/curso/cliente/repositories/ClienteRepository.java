
package com.curso.cliente.repositories;

import com.curso.dominiosMysql.Domain.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
public interface ClienteRepository extends Repository<Cliente, Integer> {




    Optional<Cliente> findById(Integer id);

    Object findAll();

    Cliente save(Cliente cliente);

    void delete(Cliente cliente);

    List<Cliente> findByNombre(String nombre);

    List<Cliente> findByEstado(String estado);
}
