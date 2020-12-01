package com.curso.factura.Controllers;

import com.curso.dominiosMongo.Domain.Factura;
import com.curso.factura.Services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @GetMapping(value ="/factura/{id}" )
    public Mono<Factura> getFacturaId(@PathVariable("id") Integer id){
        return facturaService.findById(id);
    }

    @PostMapping(value = "/factura/add")
    public Mono<Factura> addFactura(@RequestBody Factura factura){
    return facturaService.save(factura);}

    @GetMapping(value ="/factura/list" )
    public Flux<Factura> getFacturas(){
        return facturaService.findAll();
    }

    @PutMapping(value = "/factura/update")
    public Mono<Factura> updateFactura(@RequestBody Factura factura){ return facturaService.updateFactura(factura);
    }
    @DeleteMapping("/factura/delete")
    public Mono<Void> deleteFactura(@RequestBody Factura factura){
        return facturaService.deleteFactura(factura);
    }


    //------------------reto----------------------
    @GetMapping(value ="/factura/filtroporcliente/{id}" )
    public Flux<Factura> getFacturaIdCliente(@PathVariable("id") Integer id){
        return facturaService.findByIdcliente(id);
    }
    @GetMapping(value ="/factura/filtroporestado/{estado}" )
    public Flux<Factura> getFacturaEstado(@PathVariable("estado") String estado){
        return facturaService.findByEstado(estado);
    }
    @GetMapping(value ="/factura/filtroporimporte/{importe}" )
    public Flux<Factura> getFacturaImporte(@PathVariable("importe") Integer importe){
        return facturaService.findByImporte(importe);
    }

    //---------------COMPROBACION DE QUE FUNCIONA---------------------
    @GetMapping(value = "/estadomicroservicio")
    public String getEstado(){
        return "OK";
    }
}
