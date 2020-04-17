package edu.upc.dsa.services;


import edu.upc.dsa.VideoManager;
import edu.upc.dsa.VideoManagerImpl;
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
@Api(value = "/videos", description = "Endpoint to Video Service")
@Path("/videos")
public class VideoService {

    //Implementacion del Singleton
    private VideoManager vm;

    //Inicializa Singleton y lista de videos
    public VideoService() {
        this.vm = VideoManagerImpl.getInstance();
        if (vm.size()==0) {
            this.vm.addVideo("Astronomia", "1h");
            this.vm.addVideo("Lobo Wall Street", "3meses");
            this.vm.addVideo("BigMommy", "70");
        }
    }

    /*Las respuestas que se obtienen aqui es como si fuese un if/elseif con los codigos de respuesta posibles
    Es decir, si ha habido exito o no en la peticion*/

    //GET /videos
    @GET
    @ApiOperation(value = "get all Videos", notes = "asdasd")
    @ApiResponses(value = {
            //Codigos de respuesta
            @ApiResponse(code = 201, message = "Successful", response = Video.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON) //Tipo de fichero devuelto
    //Respuesta
    public Response getVideos() {

        List<Video> videos = this.vm.findAll();

        //Crea la response y la envía como JSON
        GenericEntity<List<Video>> entity = new GenericEntity<List<Video>>(videos) {};
        return Response.status(201).entity(entity).build()  ;

    }

    //GET /videos/{id}
    @GET
    @ApiOperation(value = "get a Video", notes = "asdasd")
    @ApiResponses(value = {
            //Codigos
            @ApiResponse(code = 201, message = "Successful", response = Video.class),
            @ApiResponse(code = 404, message = "Video not found")
    })
    @Path("/{id}") //campos de la petición
    @Produces(MediaType.APPLICATION_JSON) //Tipo de archivo
    //Respuesta
    public Response getVideos(@PathParam("id") String id) {
        Video v = this.vm.getVideo(id);
        if (v == null) return Response.status(404).build();
        else  return Response.status(201).entity(v).build();
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
    public Response deleteVideo(@PathParam("id") String id) {
        Video v = this.vm.getVideo(id);
        if (v == null) return Response.status(404).build();
        else this.vm.deleteVideo(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Video", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Video not found")
    })
    @Path("/")
    public Response updateVideo(Video video) {

        Video v = this.vm.updateVideo(video);

        if (v == null) return Response.status(404).build();

        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Video", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Video.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newVideo(Video video) {

        if (video.getDuration()==null || video.getTitle()==null)  return Response.status(500).entity(video).build();
        this.vm.addVideo(video);
        return Response.status(201).entity(video).build();
    }

}