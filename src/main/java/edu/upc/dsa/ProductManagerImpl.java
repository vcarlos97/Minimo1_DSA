package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.Video;
import edu.upc.dsa.util.RandomUtils;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.*;

public class ProductManagerImpl implements ProductManager{

    /*Receta Singleton y declaración de atributos*/

    //Atributo estatico privado para Singleton
    private static ProductManager instance;
    //Lista videos
    protected List<Product> products;
    protected List<Pedido> pedidos;
    //Para mostrar resultados en consola
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    //Constructor privado singleton para que nadie pueda crear instancias
    private ProductManagerImpl() {
        this.products = new LinkedList<>();
        this.pedidos = new LinkedList<>();
    }

    //Metodo publico estatico que proporciona la instancia
    public static ProductManager getInstance() {
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }

    /*Y ahora funciones que no son estaticas, las demas*/

    //Retorna tamaño lista (NECESARIO!!)
    public int size() {
        int ret = this.products.size();
        logger.info("size " + ret);

        return ret;
    }

    //Mostrar en consola
    public Product addProduct(Product p) {
        logger.info("new Product " + p);

        this.products.add (p);
        logger.info("new Product added");
        return p;
    }


    //Crea video con los parametros introducidos
    public Product addProduct(String id, float precio, int cantidad) {
        return this.addProduct(new Product(id,precio,cantidad));
    }

    //Devuelve video que coincide con el id
    public Product getProduct(String id) {
        logger.info("getProduct("+id+")");

        for (Product p: this.products) {
            if (p.getId().equals(id)) {
                logger.info("getProduct("+id+"): "+p);

                return p;
            }
        }

        logger.warn("not found " + id);
        return null;
    }

    //Devuelve todos los productos de la lista
    public List<Product> findAll() { return this.products;}

    //ORDENAR PRODUCTOS POR PRECIO
    public List<Product> sortProductsPrecio() {
        
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                //ToIgnoreCase: To not distinguish between Capital and LowerCase
                return p1.comparePrecio(p2);
            }
        });
        logger.info("List Ordered Cheaper: " + products.toString());
        return products; //200 OK PETITION
    }

    //ORDENAR PRODUCTOS POR VENTAS
    public List<Product> sortProductsVentas(){
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                //ToIgnoreCase: To not distinguish between Capital and LowerCase
                return p1.compareVentas(p2);
            }
        });
        logger.info("List Products ordenados por ventas: " + products.toString());
        return products; //200 OK PETITION
    }

    //CREAR NUEVO PEDIDO
    @Override
    public void addPedido(Pedido p) {
        p.crearPedido(p.productosPedidos,p.usuario);
    }

    public Pedido getPedido(int id) {
        logger.info("getPedido("+id+")");

        for (Pedido p: this.pedidos) {
            if (p.getId()==id) {
                logger.info("getPedido("+id+"): "+p);
                return p;
            }
        }

        logger.warn("not found " + id);
        return null;
    }

    //BORRAR/SERVIR PEDIDO
    @Override
    public void deletePedido(int id) {
        Pedido p = this.getPedido(id);
        if (p.id == id) {
            logger.info(p + " deleted ");

        } else logger.warn("not found " + p);

        pedidos.remove(p);
    }



    @Override
    public List<Product> findProdUser(Usuario user) {
        return user.getListaProductosUser();
    }

    @Override
    public List<Pedido> findPedidos(Usuario user) {
        return user.getPedidos();
    }



    /*
    //Actualiza datos de un video
    @Override
    public Video updateVideo(Video vid) {
        Video v = this.getVideo(vid.getId());

        if (v!=null) {
            logger.info(vid+" rebut!!!! ");

            v.setDuration(vid.getDuration());
            v.setTitle(vid.getTitle());

            logger.info(v+" updated ");
        }
        else {
            logger.warn("not found "+vid);
        }

        return v;
    }*/

}
