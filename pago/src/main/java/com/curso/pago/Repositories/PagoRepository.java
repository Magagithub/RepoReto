package com.curso.pago.Repositories;

import com.curso.dominiosMongo.Domain.Pago;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface PagoRepository extends ReactiveMongoRepository<Pago,Integer> {

    Flux<Pago> findByIdfactura(Integer id);

    Flux<Pago> findByEstado(String estado);
}
