package entities;

import java.util.List;

public class Pictures {

    private String id_user;
    private List<String>photos_profile;
    private List<String> photos_post;

    public Pictures() {
    }

    public Pictures(String id_user, List<String> photos_profile, List<String> photos_post) {
        this.id_user = id_user;
        this.photos_profile = photos_profile;
        this.photos_post = photos_post;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public List<String> getPhotos_profile() {
        return photos_profile;
    }

    public void setPhotos_profile(List<String> photos_profile) {
        this.photos_profile = photos_profile;
    }

    public List<String> getPhotos_post() {
        return photos_post;
    }

    public void setPhotos_post(List<String> photos_post) {
        this.photos_post = photos_post;
    }
}
