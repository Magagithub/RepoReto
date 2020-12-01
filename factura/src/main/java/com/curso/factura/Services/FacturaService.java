package com.curso.factura.Services;


import com.curso.dominiosMongo.Domain.Factura;
import com.curso.factura.Repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FacturaService {
    @Autowired
    FacturaRepository facturaRepository;

    public Mono<Factura> findById(Integer id) {
        return facturaRepository.findById(id);
    }
    @Transactional
    public Mono<Factura> save(Factura factura) {
        factura.sumarImportetotal();
        return facturaRepository.save(factura);
    }

    public Flux<Factura> findAll() {
        return facturaRepository.findAll();
    }


    public Mono<Factura> updateFactura(Factura factura) {
        return facturaRepository.save(factura);
    }


    public Mono<Void> deleteFactura(Factura factura) {
        return facturaRepository.delete(factura);
    }

    public Flux<Factura> findByIdcliente(Integer id) {
        return facturaRepository.findByIdcliente(id);
    }

    public Flux<Factura> findByEstado(String estado) {
        return facturaRepository.findByEstado(estado);
    }

    public Flux<Factura> findByImporte(Integer importe) {
        return facturaRepository.findByImportetotal(importe);
    }
}
