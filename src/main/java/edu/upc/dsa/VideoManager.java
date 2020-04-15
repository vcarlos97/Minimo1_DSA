package edu.upc.dsa;

import edu.upc.dsa.models.Video;
import java.util.List;

public interface VideoManager {

    //Funciones que hará la API

    public Video addVideo(String title, String duration);
    public Video addVideo(Video v);
    public Video getVideo(String id);
    public List<Video> findAll();
    public void deleteVideo(String id);
    public Video updateVideo(Video v);

    //Función que necesitamos para saber cuantos videos tiene la lista
    public int size();
}
