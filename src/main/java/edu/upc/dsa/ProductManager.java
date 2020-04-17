package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface ProductManager {

    //Lista productos ordenado x precio ascendente
    //Lista productos ordenado descendente x nº de ventas
    //GET ListaProductos

    public List<Product> findAll();

    //Realizar pedido (formado x diferentes productos y cantidades) por un usuario
    //POST(add) pedido
    public void addPedido(Pedido p);

    //POST PRODUCT
    public Product addProduct(String id, float precio, int cantidad);

    //Servir un pedido x orden de llegada
    //DELETE pedido
    public void deletePedido(int id);

    //Lista pedidos que ha realizado un usuario
    //GET ListaProductosComprados
    public List<Product> findProdUser(Usuario user);

    public List<Pedido> findPedidos(Usuario user);
    
    public List<Product> sortProductsPrecio();

    public List<Product> sortProductsVentas();
    
    public Product getProduct(String id);

    public int size();

    public Pedido getPedido(int id);
}
