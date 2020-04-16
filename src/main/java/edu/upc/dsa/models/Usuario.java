package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;
import java.util.List;

public class Usuario{
    public String id;
    public String name;
    public List<Pedido> pedidos = null;
    private List<Product> productosComprados = null;

    //CONSTRUCTORES***********
    public Usuario(){
        this.id= RandomUtils.getId();
    }
    public Usuario(String name){
        this();
        this.name=name;
        this.productosComprados=new LinkedList<>();
        this.pedidos=new LinkedList<>();
    }
    //*****************

    //GETTERS Y SETTERS***********
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(List<Product> productosComprados) {
        this.productosComprados = productosComprados;
    }

    public int getSizeListaProd(){
        return this.productosComprados.size();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //********************

    //FUNCIONES
    //Obtener producto de la lista de productos comprados
    public Product getProduct(int id){
        try{
            return this.productosComprados.get(id);
        } catch (IndexOutOfBoundsException | ExceptionInInitializerError e){
            return null;
        }
    }

    //Añadir producto a la lista de productos comprados
    public int addProductoLista(Product product){
        try{
            this.productosComprados.add(product);
        } catch (ExceptionInInitializerError e){
            return 400; //Bad request
        } catch (IndexOutOfBoundsException e){
            return 507; //No storage
        }
        return 201; //Created
    }

    //Devuelve la lista de productos comprados del Usuario
    public List<Product> getListaProductosUser(){
        return this.productosComprados;
    }

    //Devuelve la cantidad de productos comprados
    public int getCantidadProductosComprados(String id) {
        int cantidad = 0;
        for (Product p : this.productosComprados) {
            if (id.equals(p.getId())) {
                cantidad=cantidad+p.getCantidad();
            }
        }
        return cantidad;
    }

    /*//Obtener el precio de un producto de la lista
    public float getPrecioProductoLista(List<Product> list, String id){
        int index = Integer.parseInt(id);
        return list.get(index).getPrecio();
    }*/

    @Override
    public String toString() {
        return "Usuario [id="+id+", name=" + name +"]";
    }
}
