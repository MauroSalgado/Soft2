package entities;

import java.util.ArrayList;
import java.util.List;

public class Community {

    private String idComunity;
    private String name;
    private String descripcion;
    private List<String> topics;
    private String urlImage;
    private int calification;
    private List<String> followers;

    public Community() {
    }

    public Community(String name, String descripcion, String urlImage) {
        this.calification = 0;
        this.name = name;
        this.descripcion = descripcion;
        this.urlImage = urlImage;
        this.topics = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public String getIdComunity() {
        return idComunity;
    }

    public void setIdComunity(String idComunity) {
        this.idComunity = idComunity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        descripcion = descripcion;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }
}
