package com.curso.pago.Services;


import com.curso.dominiosMongo.Domain.Factura;
import com.curso.dominiosMongo.Domain.Pago;
import com.curso.pago.Repositories.PagoRepository;
import com.curso.pago.Repositories.PagoRepository2;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
@Component
@Service
public class PagoService {
    @Autowired
    PagoRepository pagoRepository;
    @Autowired
    PagoRepository2 pagoRepository2;

  /*  @Autowired
    RestTemplate restTemplate;*/

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;


    public Mono<Pago> findById(Integer id) {
        return pagoRepository.findById(id); }

    public Mono<Pago> save(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Flux<Pago> findAll() {
        return pagoRepository.findAll();
    }

    public Mono<Pago> updateFactura(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Mono<Pago> delete(Integer id) {
        pagoRepository.deleteById(id);
        return null; }

    public Mono<Void> deleteFactura(Pago pago) {
        return pagoRepository.delete(pago);
    }

    public Flux<Pago> findByIdfactura(Integer id) {
        return  pagoRepository.findByIdfactura(id); }

    public Flux<Pago> findByEstado(String estado) {
        return pagoRepository.findByEstado(estado); }

    public List<Pago> defaultfindByEstadoCliente(String estado) {
        throw new RuntimeException("Fallo al conectar con otro microservicio");
    }
    @HystrixCommand(fallbackMethod="defaultfindByEstadoCliente")
    public List<Pago> findByEstadoCliente(String estado)  {
        //clientes filtrados por un estado
        Application application=eurekaClient.getApplication("Cliente-Sincrono-Mysql");
        InstanceInfo instanceInfo=application.getInstances().get(0);
        String fooResourceUrl = instanceInfo.getHomePageUrl();
        ResponseEntity<Integer[]> responseEntity;
        RestTemplate restTemplate = new RestTemplate();
        responseEntity  = restTemplate.getForEntity(fooResourceUrl+"cliente/estado/"+estado, Integer[].class);
        Integer[] idsClientes=responseEntity.getBody();

        //facturas filtradas por los clientes
        application=eurekaClient.getApplication("Factura-Asincrono-Mongo");
        instanceInfo=application.getInstances().get(0);
        fooResourceUrl = instanceInfo.getHomePageUrl();
        ResponseEntity<Factura[]> responseEntity2;
        RestTemplate restTemplate2 = new RestTemplate();
        List<Integer> idsFacturas = new ArrayList<>();
        for (Integer id: idsClientes){
            responseEntity2  = restTemplate2.getForEntity(fooResourceUrl+"factura/filtroporcliente/"+id, Factura[].class);
            Factura[] facturas=responseEntity2.getBody();
            for(int x=0; x<facturas.length; x++){
                idsFacturas.add(facturas[x].getId());
            }
        }

        //pagos filtrados por facturas
        List<Pago> pagos = new ArrayList<>();
        for (Integer idFac:idsFacturas) {
            pagos.addAll(pagoRepository2.findByIdfactura(idFac));
        }

        return pagos; }

    public List<Pago> findByEstadoFactura(String estado) {
        //facturas filtradas por los estados
        Application application=eurekaClient.getApplication("Factura-Asincrono-Mongo");
        InstanceInfo instanceInfo=application.getInstances().get(0);
        String fooResourceUrl = instanceInfo.getHomePageUrl();
        ResponseEntity<Factura[]> responseEntity2;
        RestTemplate restTemplate2 = new RestTemplate();
        List<Integer> idsFacturas = new ArrayList<>();
            responseEntity2  = restTemplate2.getForEntity(fooResourceUrl+"factura/filtroporestado/"+estado, Factura[].class);
            Factura[] facturas=responseEntity2.getBody();
            for(int x=0; x<facturas.length; x++){
                idsFacturas.add(facturas[x].getId());
            }


        //pagos filtrados por facturas
        List<Pago> pagos = new ArrayList<>();
        for (Integer idFac:idsFacturas) {
            pagos.addAll(pagoRepository2.findByIdfactura(idFac));
        }
        return pagos; }

    public List<Pago> findByIdcliente(Integer id) {
        Application application=eurekaClient.getApplication("Factura-Asincrono-Mongo");
        InstanceInfo instanceInfo=application.getInstances().get(0);
        String fooResourceUrl
                = instanceInfo.getHomePageUrl();
        ResponseEntity<Factura[]> responseEntity;
        RestTemplate restTemplate = new RestTemplate();
              responseEntity  = restTemplate.getForEntity(fooResourceUrl+"factura/filtroporcliente/"+id, Factura[].class);
        Factura[] facturas=responseEntity.getBody();
        List<Pago> pagos = new ArrayList<>();
        for (Factura f:facturas) {
            pagos.addAll(pagoRepository2.findByIdfactura(f.getId()));
        }
        return pagos;}


}
