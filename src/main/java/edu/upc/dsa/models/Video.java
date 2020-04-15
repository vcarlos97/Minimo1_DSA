package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Video {
    public String id;
    public String title;
    public String duration;

    //Siempre necesitamos un constructor vacio para que Jersey funcione
    public Video(){
        this.id = RandomUtils.getId();
    }

    public Video(String title, String duration){
        this(); //Referencia al anterior constructor
        this.title=title;
        this.duration=duration;
    }


    //Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    //Método toString para obtener el JSON que enviaremos en el Request
    @Override
    public String toString() {
        return "Video [id="+id+", title=" + title + ", duration=" + duration +"]";
    }
}
