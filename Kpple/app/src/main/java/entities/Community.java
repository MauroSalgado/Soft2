package entities;

import com.firebase.ui.auth.data.model.User;

import java.util.List;

public class Community {

    private String idComunity;
    private String name;
    private String Descripcion;
    private List<String> topics;
    private String urlImage;
    private List<String> followers;

    public Community() {
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
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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
