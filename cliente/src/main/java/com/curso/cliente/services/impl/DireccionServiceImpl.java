package com.curso.cliente.services.impl;

import com.curso.cliente.repositories.ClienteRepository;
import com.curso.cliente.repositories.DireccionRepository;
import com.curso.cliente.services.ClienteService;
import com.curso.cliente.services.DireccionService;
import com.curso.dominiosMysql.Domain.Cliente;
import com.curso.dominiosMysql.Domain.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionServiceImpl implements DireccionService {
@Autowired
private DireccionRepository direccionRepository;


    @Override
    public void saveAll(List<Direccion> direcciones) {


    }

    @Override
    public List<Direccion> findByIdcliente(Integer id) {
        return direccionRepository.findByIdcliente(id);
    }

    @Override
    public List<Direccion> findByProvincia(String provincia) {
        return direccionRepository.findByProvincia(provincia);
    }
}
