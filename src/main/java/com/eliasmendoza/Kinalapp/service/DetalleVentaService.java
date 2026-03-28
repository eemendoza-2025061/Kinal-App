package com.eliasmendoza.Kinalapp.service;

import com.eliasmendoza.Kinalapp.entity.DetalleVenta;
import com.eliasmendoza.Kinalapp.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> listarTodos() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public Optional<DetalleVenta> buscarPorCodigo(int codigoDetalleVenta) {
        return detalleVentaRepository.findById(codigoDetalleVenta);
    }

    @Override
    public DetalleVenta actualizar(int codigoDetalleVenta, DetalleVenta detalleVenta) {
        if (detalleVentaRepository.existsById(codigoDetalleVenta)) {
            detalleVenta.setCodigoDetalleVenta(codigoDetalleVenta);
            return detalleVentaRepository.save(detalleVenta);
        }
        return null;
    }

    @Override
    public void eliminar(int codigoDetalleVenta) {
        detalleVentaRepository.deleteById(codigoDetalleVenta);
    }

    @Override
    public boolean existePorCodigo(int codigoDetalleVenta) {
        return detalleVentaRepository.existsById(codigoDetalleVenta);
    }
}