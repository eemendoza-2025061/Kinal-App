package com.eliasmendoza.Kinalapp.service;

import com.eliasmendoza.Kinalapp.entity.Producto;
import com.eliasmendoza.Kinalapp.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) { this.productoRepository = productoRepository; }

    @Override
    public List<Producto> listarTodos() { return productoRepository.findAll(); }

    @Override
    public Producto guardar(Producto producto) { return productoRepository.save(producto); }

    @Override
    public Optional<Producto> buscarPorCodigo(int codigo) { return productoRepository.findById(codigo); }

    @Override
    public Producto actualizar(int codigo, Producto producto) {
        if (productoRepository.existsById(codigo)) {
            producto.setCodigoProducto(codigo);
            return productoRepository.save(producto);
        }
        return null;
    }

    @Override
    public void eliminar(int codigo) { productoRepository.deleteById(codigo); }

    @Override
    public boolean existePorCodigo(int codigo) { return productoRepository.existsById(codigo); }
}
