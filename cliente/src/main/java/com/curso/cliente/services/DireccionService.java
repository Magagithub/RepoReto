package com.curso.cliente.services;



import com.curso.dominiosMysql.Domain.Cliente;
import com.curso.dominiosMysql.Domain.Direccion;

import java.util.List;

public interface DireccionService {


    void saveAll(List<Direccion> direcciones);

    List<Direccion> findByIdcliente(Integer id);

    List<Direccion> findByProvincia(String provincia);
}
