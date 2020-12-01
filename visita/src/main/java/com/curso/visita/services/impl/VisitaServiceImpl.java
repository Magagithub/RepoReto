package com.curso.visita.services.impl;


import com.curso.dominiosMysql.Domain.Visita;
import com.curso.visita.repositories.VisitaRepository;
import com.curso.visita.services.VisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitaServiceImpl implements VisitaService {
@Autowired
private VisitaRepository visitaRepository;

    @Override
    public Visita findById(Integer id) {
        return visitaRepository.findById(id);
    }

    @Override
    public List<Visita> findAll() {
        return visitaRepository.findAll();
    }

    @Override
    public Visita save(Visita visita) {
        return visitaRepository.save(visita);
    }

    @Override
    public Visita updateCliente(Visita visita) {
        return visitaRepository.save(visita);
    }

    @Override
    public Void deleteCliente(Visita visita) {
        return visitaRepository.delete(visita);
    }

    @Override
    public List<Visita> findByIdcliente(Integer id) {
        return visitaRepository.findByIdcliente(id);
    }

    @Override
    public List<Visita> findByEstado(String estado) {
        return visitaRepository.findByEstado(estado);
    }
}
