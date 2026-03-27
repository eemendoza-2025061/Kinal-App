package com.eliasmendoza.Kinalapp.service;

import com.eliasmendoza.Kinalapp.entity.Venta;
import com.eliasmendoza.Kinalapp.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) { this.ventaRepository = ventaRepository; }

    @Override
    public List<Venta> listarTodos() { return ventaRepository.findAll(); }

    @Override
    public Venta guardar(Venta venta) { return ventaRepository.save(venta); }

    @Override
    public Optional<Venta> buscarPorCodigo(int codigoVenta) { return ventaRepository.findById(codigoVenta); }

    @Override
    public Venta actualizar(int codigoVenta, Venta venta) {
        if (ventaRepository.existsById(codigoVenta)) {
            venta.setCodigoVenta(codigoVenta);
            return ventaRepository.save(venta);
        }
        return null;
    }

    @Override
    public void eliminar(int codigoVenta) { ventaRepository.deleteById(codigoVenta); }

    @Override
    public boolean existePorCodigo(int codigoVenta) { return ventaRepository.existsById(codigoVenta); }
}