/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.curso.cliente.controllers;

import com.curso.cliente.services.ClienteService;
import com.curso.cliente.services.DireccionService;
import com.curso.dominiosMysql.Domain.Cliente;
import com.curso.dominiosMysql.Domain.Direccion;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
class ClienteController {


    @Autowired
    private ClienteService clienteService;
   /* @Autowired
    private DireccionService direccionService;
*/
    @GetMapping(value ="/cliente/{id}" )
    public Cliente getId(@PathVariable("id") Integer id){
        return clienteService.findById(id);
    }
    //si le pasas direcciones en el array las guarda en direcciones con el id delcliente
    @PostMapping(value = "/cliente/add")
    public Cliente add(@RequestBody Cliente cliente){
        return clienteService.saveclientedir(cliente);
    }

    @GetMapping(value ="/cliente/list" )
    public List<Cliente> get(){
        return clienteService.findAll();
    }

    @PutMapping(value = "/cliente/update")
    public Cliente update(@RequestBody Cliente cliente){ return clienteService.updateCliente(cliente);
    }
    @DeleteMapping("/cliente/delete")
    public void delete(@RequestBody Cliente cliente){
        clienteService.deleteCliente(cliente);
    }


    //DEVUELVE CLIENTE CON SUS DIRECCIONES RELACIONADAS POR EL IDCLIENTE
    //EL CLIENTE NO ESTA RELACIONADO CON LAS DIRECCIONES EN LA BBDD
    @GetMapping(value ="/cliente/clientedirecciones/{id}" )
    public Cliente getIdconDirrecciones(@PathVariable("id") Integer id){

        return clienteService.getIdCOnDirecciones(id);
    }


    //--------------reto----------------------/

    @GetMapping(value ="/cliente/busquedaporprovincia/{provincia}" )
    public List<Cliente>  busquedaPorProvincia(@PathVariable("provincia") String provincia){
        return clienteService.busquedaPorProvincia(provincia);
    }

    @GetMapping(value ="/cliente/busquedapornombre/{nombre}" )
    public List<Cliente>  busquedaPorNombre(@PathVariable("nombre") String nombre){
        return clienteService.busquedaPorNombre(nombre);
    }

    @GetMapping(value ="/cliente/estado/{estado}" )
    public List<Integer> getId(@PathVariable("estado") String estado){
        return clienteService.findByEstado(estado);
    }

    //---------------COMPROBACION DE QUE FUNCIONA---------------------
    @GetMapping(value = "/estadomicroservicio")
    public String getEstado(){
        return "OK";
    }
}
