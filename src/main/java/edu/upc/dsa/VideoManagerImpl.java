package edu.upc.dsa;

import edu.upc.dsa.models.Video;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class VideoManagerImpl implements VideoManager {
    private static VideoManager instance;
    protected List<Video> videos;
    final static Logger logger = Logger.getLogger(VideoManagerImpl.class);

    private VideoManagerImpl() {
        this.videos = new LinkedList<>();
    }

    public static VideoManager getInstance() {
        if (instance==null) instance = new VideoManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.videos.size();
        logger.info("size " + ret);

        return ret;
    }

    public Video addVideo(Video v) {
        logger.info("new Video " + v);

        this.videos.add (v);
        logger.info("new Video added");
        return v;
    }

    public Video addVideo(String title, String duration) {

        return this.addVideo(new Video(title, duration));
    }

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

    public List<Video> findAll() {
        return this.videos;
    }

    @Override
    public void deleteVideo(String id) {

        Video v = this.getVideo(id);
        if (v==null) {
            logger.warn("not found " + v);
        }
        else logger.info(v + " deleted ");

        this.videos.remove(v);

    }

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