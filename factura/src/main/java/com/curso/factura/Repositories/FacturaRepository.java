package com.curso.factura.Repositories;

import com.curso.dominiosMongo.Domain.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

public interface FacturaRepository extends ReactiveMongoRepository<Factura,Integer> {

    Flux<Factura> findByIdcliente(Integer id);

    Flux<Factura> findByEstado(String estado);

    Flux<Factura> findByImportetotal(Integer importe);
}
