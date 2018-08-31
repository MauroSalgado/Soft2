package entities;

public class Post {
    private String idPost;
    private String idUser;
    private String datePost;
    private String urlImage;
    private String urlVideo;
    private String textPost;

    public Post() {
    }

    public Post(String idPost, String idUser, String datePost, String urlImage, String urlVideo, String textPost) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.datePost = datePost;
        this.urlImage = urlImage;
        this.urlVideo = urlVideo;
        this.textPost = textPost;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }
}
