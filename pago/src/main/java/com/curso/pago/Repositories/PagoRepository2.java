package com.curso.pago.Repositories;

import com.curso.dominiosMongo.Domain.Pago;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface PagoRepository2 extends MongoRepository<Pago,Integer> {


    List<Pago> findByIdfactura(Integer id);
}
