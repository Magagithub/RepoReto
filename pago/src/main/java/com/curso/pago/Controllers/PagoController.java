package com.curso.pago.Controllers;

import com.curso.dominiosMongo.Domain.Pago;
import com.curso.pago.Services.PagoService;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class PagoController{
    @Autowired
    PagoService pagoService;

    @GetMapping(value ="/pago/{id}" )
    public Mono<Pago> getPagoId(@PathVariable("id") Integer id){
        return pagoService.findById(id);
    }
    @PostMapping(value = "/pago/add")
    public Mono<Pago> addPago(@RequestBody Pago pago){
    return pagoService.save(pago);
    }

    @GetMapping(value ="/pago/list" )
    public Flux<Pago> getPago(){
        return pagoService.findAll();
    }

    @DeleteMapping(value ="/pago/delete/{id}" )
    public Mono<Pago> deletePagoId(@PathVariable("id")Integer id){
        return pagoService.delete(id);
    }

    @PutMapping(value = "/pago/update")
    public Mono<Pago> updatePago(@RequestBody Pago pago){
            return pagoService.updateFactura(pago);

    }
    @DeleteMapping("/pago/delete")
    public Mono<Void> deletePago(@RequestBody Pago pago){
        return pagoService.deleteFactura(pago);
    }

    //-------------------reto------------

    @GetMapping(value ="/pago/filtroidfactura/{id}" )
    public Flux<Pago> getPagoIdFactura(@PathVariable("id") Integer id){
        return pagoService.findByIdfactura(id);
    }

    @GetMapping(value ="/pago/filtroestado/{estado}" )
    public Flux<Pago> getPagoEstado(@PathVariable("estado") String estado){
        return pagoService.findByEstado(estado);
    }


    //estos necesitan de otros microservicios
    @GetMapping(value ="/pago/filtroidcliente/{id}" )
    public List<Pago> getPagoIdCliente(@PathVariable("id") Integer id){

        return pagoService.findByIdcliente(id);
    }

    @GetMapping(value ="/pago/filtroestadocliente/{estado}" )
    public List<Pago> getPagoEstadoCliente(@PathVariable("estado") String estado){ return pagoService.findByEstadoCliente(estado);}

    @GetMapping(value ="/pago/filtroestadofactura/{estado}" )
    public List<Pago> getPagoEstadoFactura(@PathVariable("estado")String estado){ return pagoService.findByEstadoFactura(estado);}

    //---------------COMPROBACION DE QUE FUNCIONA---------------------
    @GetMapping(value = "/estadomicroservicio")
    public String getEstado(){
        return "OK";
    }

}
