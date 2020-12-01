package com.curso.cliente.services.impl;

import com.curso.cliente.repositories.ClienteRepository;
import com.curso.cliente.repositories.DireccionRepository;
import com.curso.cliente.services.ClienteService;
import com.curso.dominiosMysql.Domain.Cliente;
import com.curso.dominiosMysql.Domain.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
@Autowired
private ClienteRepository clienteRepository;
@Autowired
private DireccionRepository direccionRepository;

    @Override
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> busquedaPorProvincia(String provincia) {
        List<Direccion> direcciones = direccionRepository.findByProvincia(provincia);
        List<Cliente> clientes = new ArrayList<>();
        //DE CADA DIRECCION BUSCAMOS SU CLIENTE Y A CLIENTE LE AÑADIMOS LA DIRECCION EN SU ARRAY
        //TIENE UN ARRAY PARA PODER GUARDAR VARIAS DIRECCIONES PERO NO ESTAN RELACIONADOS EN LA BBDD
        //AÑADIMOS EL CLIENTE CON SU DIRECCION EN EL ARRAY
        for (Direccion d:direcciones) {
            Cliente c=clienteRepository.findById(d.getIdcliente()).orElse(null);
            c.getDirecciones().add(d);
            clientes.add(c);
        }
        return clientes;
    }

    @Override
    public Cliente saveclientedir(Cliente cliente) {
        if (cliente.getDirecciones().size()!=0) {
            for (Direccion direccion : cliente.getDirecciones()) {
                direccionRepository.save(direccion);
            }
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente getIdCOnDirecciones(Integer id) {
        Cliente clienteDirecciones = clienteRepository.findById(id).orElse(null);
        List<Direccion> direcciones = direccionRepository.findByIdcliente(clienteDirecciones.getId());
        clienteDirecciones.setDirecciones(direcciones);
        return clienteDirecciones;
    }

    @Override
    public List<Cliente> busquedaPorNombre(String nombre) {
        List<Cliente> clienteDirecciones = clienteRepository.findByNombre(nombre);
        for (Cliente c: clienteDirecciones){
        List<Direccion> direcciones = direccionRepository.findByIdcliente(c.getId());
        c.setDirecciones(direcciones);
        }
        return clienteDirecciones;
    }

    @Override
    public List<Integer> findByEstado(String estado) {
        List<Integer> listaIds=new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findByEstado(estado);
        for (Cliente cliente: clientes){
            listaIds.add(cliente.getId());
        }

        return listaIds;
    }

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Cliente cliente) {

           clienteRepository.delete(cliente);

    }
}
