package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Pedido {
    public int id;
    public List<Product> productosPedidos = null;
    public Usuario usuario;

    public Pedido(){
    }

    public Pedido(int id, Usuario usuario) {
        this();
        this.id = id;
        this.usuario = usuario;
        this.productosPedidos = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProductosPedidos() {
        return productosPedidos;
    }

    public void setProductosPedidos(List<Product> productosPedidos) {
        this.productosPedidos = productosPedidos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int crearPedido(List<Product> productos, Usuario user){
        this.usuario.setId(user.getId());
        this.usuario.setName(user.getName());
        this.usuario.setProductosComprados(productos);
        return 0;
    }

    public void addProductoListaProductosPedido(String id, float precio, int cantidad){
        Product product = new Product(id,precio,cantidad);
        productosPedidos.add(product);
    }

    public void addPedidoListaUsuario(Pedido pedido){
        usuario.pedidos.add(pedido);
    }
    //USUARIO
        //LISTA PEDIDOS
            //LISTA PRODUCTOS
}
