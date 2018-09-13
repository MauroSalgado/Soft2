package entities;

public class Post {
    private String idPost;
    private String idUser;
    private String datePost;
    private String urlImage;
    private String urlVideo;
    private String txtPost;
    private int likes;
    private int dislike;

    public Post() {
    }

    public Post(String idPost, String idUser, String datePost, String urlImage, String urlVideo,
                String txtPost, int likes, int dislike) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.datePost = datePost;
        this.urlImage = urlImage;
        this.urlVideo = urlVideo;
        this.txtPost = txtPost;
        this.likes = likes;
        this.dislike = dislike;
    }

    public String getIdPost() {
        return idPost;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
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

    public String getTxtPost() {
        return txtPost;
    }

    public void setTxtPost(String txtPost) {
        this.txtPost = txtPost;
    }
}
