package com.curso.visita.services;


import com.curso.dominiosMysql.Domain.Visita;

import java.util.List;

public interface VisitaService {


    Visita findById(Integer id);


    List<Visita> findAll();

    Visita save(Visita visita);

    Visita updateCliente(Visita visita);

    Void deleteCliente(Visita visita);

    List<Visita> findByIdcliente(Integer id);

    List<Visita> findByEstado(String estado);
}
