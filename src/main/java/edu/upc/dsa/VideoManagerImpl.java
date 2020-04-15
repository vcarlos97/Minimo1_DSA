package edu.upc.dsa;

import edu.upc.dsa.models.Video;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class VideoManagerImpl implements VideoManager {

    /*Receta Singleton y declaración de atributos*/

    //Atributo estatico privado para Singleton
    private static VideoManager instance;
    //Lista videos
    protected List<Video> videos;
    //Para mostrar resultados en consola
    final static Logger logger = Logger.getLogger(VideoManagerImpl.class);

    //Constructor privado singleton para que nadie pueda crear instancias
    private VideoManagerImpl() {
        this.videos = new LinkedList<>();
    }

    //Metodo publico estatico que proporciona la instancia
    public static VideoManager getInstance() {
        if (instance==null) instance = new VideoManagerImpl();
        return instance;
    }

    /*Y ahora funciones que no son estaticas, las demas*/

    //Retorna tamaño lista (NECESARIO!!)
    public int size() {
        int ret = this.videos.size();
        logger.info("size " + ret);

        return ret;
    }

    //Mostrar en consola
    public Video addVideo(Video v) {
        logger.info("new Video " + v);

        this.videos.add (v);
        logger.info("new Video added");
        return v;
    }


    //Crea video con los parametros introducidos
    public Video addVideo(String title, String duration) {

        return this.addVideo(new Video(title, duration));
    }

    //Devuelve video que coincide con el id
    public Video getVideo(String id) {
        logger.info("getVideo("+id+")");

        for (Video v: this.videos) {
            if (v.getId().equals(id)) {
                logger.info("getVideo("+id+"): "+v);

                return v;
            }
        }

        logger.warn("not found " + id);
        return null;
    }

    //Devuelve todos los videos de la lista
    public List<Video> findAll() {
        return this.videos;
    }

    //Elimina video de la lista
    @Override
    public void deleteVideo(String id) {

        Video v = this.getVideo(id);
        if (v==null) {
            logger.warn("not found " + v);
        }
        else logger.info(v + " deleted ");

        this.videos.remove(v);

    }

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
    }
}