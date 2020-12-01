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
package com.curso.visita.controllers;

import com.curso.dominiosMysql.Domain.Visita;
import com.curso.visita.services.VisitaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class VisitaController {


    @Autowired
    private VisitaService visitaService;


    @GetMapping(value ="/visita/{id}" )
    public Visita getId(@PathVariable("id") Integer id){ return visitaService.findById(id); }

    @PostMapping(value = "/visita/add")
    public Visita add(@RequestBody Visita visita){
        return visitaService.save(visita);
    }

    @GetMapping(value ="/visita/list" )
    public List<Visita> get(){
        return visitaService.findAll();
    }

    @PutMapping(value = "/visita/update")
    public Visita update(@RequestBody Visita visita){ return visitaService.updateCliente(visita);
    }
    @DeleteMapping("/visita/delete")
    public Void delete(@RequestBody Visita visita){
        return visitaService.deleteCliente(visita);
    }

    //-----------------reto-----------------------

    @GetMapping(value ="/visita/filtroporcliente/{id}" )
    public List<Visita> getIdCliente(@PathVariable("id") Integer id){ return visitaService.findByIdcliente(id); }

    @GetMapping(value ="/visita/filtroporestado/{estado}" )
    public List<Visita> getIEstado(@PathVariable("estado") String estado){ return visitaService.findByEstado(estado); }

    //---------------COMPROBACION DE QUE FUNCIONA---------------------
    @GetMapping(value = "/estadomicroservicio")
    public String getEstado(){
        return "OK";
    }

}
