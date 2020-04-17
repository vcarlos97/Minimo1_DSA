package edu.upc.dsa.models;

//EN ESTA CLASE SOLO HE DEFINIDO LAS VARIABLES DE PRODUCTO NECESARIAS
//Y HE INSERTADO 2 CONSTRUCTORES Y LOS GETTERS Y SETTERS

public class Product {
    public String id;
    public float precio;
    public int cantidad;

    public Product(){
    }

    public Product(String id, float precio, int cantidad) {
        this.id = id;
        this.precio = precio;
        this.cantidad=cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad(){ return cantidad; }

    public void setCantidad(int cantidad){ this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "Producto [id="+id+", precio=" + precio +"]";
    }

    public int comparePrecio(Product p) {
        if (precio < p.precio) {
            return -1;
        }
        if (precio > p.precio) {
            return 1;
        }
        return 0;
    }

    public int compareVentas(Product p) {
        if (cantidad > p.cantidad) {
            return -1;
        }
        if (cantidad < p.cantidad) {
            return 1;
        }
        return 0;
    }
}

