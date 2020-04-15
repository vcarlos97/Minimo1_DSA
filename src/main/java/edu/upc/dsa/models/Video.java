package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Video {
    public String id;
    public String title;
    public String duration;

    public Video(){
        this.id = RandomUtils.getId();
    }

    public Video(String title, String duration){
        this();
        this.title=title;
        this.duration=duration;
    }

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

    @Override
    public String toString() {
        return "Video [id="+id+", title=" + title + ", duration=" + duration +"]";
    }
}
