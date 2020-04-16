package edu.upc.dsa.models;

//EN ESTA CLASE SOLO HE DEFINIDO LAS VARIABLES DE PRODUCTO NECESARIAS
//Y HE INSERTADO 2 CONSTRUCTORES Y LOS GETTERS Y SETTERS

public class Product {
    public String id;
    public float precio;

    public Product(){
    }

    public Product(String id, float precio) {
        this.id = id;
        this.precio = precio;
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

    @Override
    public String toString() {
        return "Producto [id="+id+", precio=" + precio +"]";
    }
}

