package edu.upc.dsa.services;


import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Video;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Ruta donde se encuentra el servicio
@Api(value = "/products", description = "Endpoint to Product Service")
@Path("/products")
public class ProductService {

    //Implementacion del Singleton
    private ProductManager pm;

    //Inicializa Singleton y lista de videos
    public ProductService() {
        this.pm = ProductManagerImpl.getInstance();
        if (pm.size()==0) {
            this.pm.addProduct("patatas", (float) 1.45, 3);
            this.pm.addProduct("cocacola", (float)1.69, 6);
        }
    }

    /*Las respuestas que se obtienen aqui es como si fuese un if/elseif con los codigos de respuesta posibles
    Es decir, si ha habido exito o no en la peticion*/

    //GET /videos
    @GET
    @ApiOperation(value = "get all Videos", notes = "asdasd")
    @ApiResponses(value = {
            //Codigos de respuesta
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON) //Tipo de fichero devuelto
    //Respuesta
    public Response getProductsPrice() {

        List<Product> products = this.pm.sortProductsPrecio();

        //Crea la response y la envía como JSON
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build()  ;

    }

    //GET /videos/{id}
    @GET
    @ApiOperation(value = "get a Video", notes = "asdasd")
    @ApiResponses(value = {
            //Codigos
            @ApiResponse(code = 201, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Video not found")
    })
    @Path("/{id}") //campos de la petición
    @Produces(MediaType.APPLICATION_JSON) //Tipo de archivo
    //Respuesta
    public Response getProducts(@PathParam("id") String id) {
        Product p = this.pm.getProduct(id);
        if (p == null) return Response.status(404).build();
        else  return Response.status(201).entity(p).build();
    }

    //ASI IGUAL TOL RATO PARA LOS DIFERENTES TIPOS
    //DE PETICIONES QUE QUERAMOS REALIZAR
    //PROGRAMANDO LOS POSIBLES CODIGOS DE RESPUESTA

    @DELETE
    @ApiOperation(value = "delete a Video", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Video not found")
    })
    @Path("/{id}")
    public Response deletePedido(@PathParam("id") int id) {
        Pedido p = this.pm.getPedido(id);
        if (p == null) return Response.status(404).build();
        else this.pm.deletePedido(id);
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "create a new Pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Pedido.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPedido(Pedido p) {

        if (p.getUsuario()==null || p.getProductosPedidos()==null)  return Response.status(500).entity(p).build();
        this.pm.addPedido(p);
        return Response.status(201).entity(p).build();
    }

}