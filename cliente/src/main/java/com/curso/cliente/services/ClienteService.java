package com.curso.cliente.services;



import com.curso.dominiosMysql.Domain.Cliente;

import java.util.List;

public interface ClienteService {


    Cliente save(Cliente cliente);

    List<Cliente> findAll();

    Cliente updateCliente(Cliente cliente);

    void deleteCliente(Cliente cliente);

    Cliente findById(Integer id);

    List<Cliente> busquedaPorProvincia(String provincia);

    Cliente saveclientedir(Cliente cliente);

    Cliente getIdCOnDirecciones(Integer id);

    List<Cliente> busquedaPorNombre(String nombre);

    List<Integer> findByEstado(String estado);
}
