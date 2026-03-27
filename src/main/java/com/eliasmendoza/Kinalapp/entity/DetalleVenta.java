package com.eliasmendoza.Kinalapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVenta {

    @Id
    @Column(name = "codigo_detalle_venta")
    private int codigoDetalleVenta;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private double precioUnitario;

    @Column(name = "subtotal")
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "Productos_codigo_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "Ventas_codigo_venta")
    private Venta venta;

    public DetalleVenta() {
    }

    public DetalleVenta(int codigoDetalleVenta, int cantidad, double precioUnitario, double subtotal, Producto producto, Venta venta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.producto = producto;
        this.venta = venta;
    }

    // Getters y Setters
    public int getCodigoDetalleVenta() { return codigoDetalleVenta; }

    public void setCodigoDetalleVenta(int codigoDetalleVenta) { this.codigoDetalleVenta = codigoDetalleVenta; }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }

    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getSubtotal() { return subtotal; }

    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public Producto getProducto() { return producto; }

    public void setProducto(Producto producto) { this.producto = producto; }

    public Venta getVenta() { return venta; }

    public void setVenta(Venta venta) { this.venta = venta; }
}