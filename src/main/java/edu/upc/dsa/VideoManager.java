package edu.upc.dsa;

import edu.upc.dsa.models.Video;
import java.util.List;

public interface VideoManager {
    public Video addVideo(String title, String duration);
    public Video addVideo(Video v);
    public Video getVideo(String id);
    public List<Video> findAll();
    public void deleteVideo(String id);
    public Video updateVideo(Video v);

    public int size();
}
