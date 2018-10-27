package activities;

import entities.Comment;

public class CommentRecibir extends Comment {
    private Long hora;

    public CommentRecibir() {
    }

    public CommentRecibir(Long hora) {
        this.hora = hora;
    }

    public CommentRecibir(String comment, String nombre, String urlFoto, String fotoPerfil, String type_message, Long hora) {
        super(comment, nombre, urlFoto, fotoPerfil, type_message);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
