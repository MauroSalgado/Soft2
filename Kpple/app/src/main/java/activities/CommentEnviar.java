package activities;

import java.util.Map;

import entities.Comment;

public class CommentEnviar extends Comment {
    private Map hora;

    public CommentEnviar(Map hora) {
        this.hora = hora;
    }

    public CommentEnviar() {
    }

    public CommentEnviar(String comment, String nombre, String urlFoto, String fotoPerfil, String type_message, Map hora) {
        super(comment, nombre, urlFoto, fotoPerfil, type_message);
        this.hora = hora;
    }

    public CommentEnviar(String comment, String nombre, String fotoPerfil, String type_message, Map hora) {
        super(comment, nombre, fotoPerfil, type_message);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
