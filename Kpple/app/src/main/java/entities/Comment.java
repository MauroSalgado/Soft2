package entities;

public class Comment {
    private String comment;
    private String nombre;
    private String urlFoto;
    private String fotoPerfil;
    private String type_message;


    public Comment(String comment, String nombre, String fotoPerfil, String type_message) {
        this.comment = comment;
        this.nombre = nombre;
        this.fotoPerfil = fotoPerfil;
        this.type_message = type_message;
    }

    public Comment(String comment, String nombre, String urlFoto, String fotoPerfil, String type_message) {
        this.comment = comment;
        this.nombre = nombre;
        this.urlFoto = urlFoto;
        this.fotoPerfil = fotoPerfil;
        this.type_message = type_message;
    }

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getType_message() {
        return type_message;
    }

    public void setType_message(String type_message) {
        this.type_message = type_message;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
