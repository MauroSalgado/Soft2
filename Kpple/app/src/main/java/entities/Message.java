package entities;

public class Message {
    private String message;
    private String urlPhoto;
    private String name;
    private String profilePhoto;
    private String type_message;

    public Message() {
    }

    public Message(String message, String urlPhoto, String name, String profilePhoto, String type_message) {
        this.message = message;
        this.urlPhoto = urlPhoto;
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.type_message = type_message;
    }

    public Message(String message, String name, String profilePhoto, String type_message) {
        this.message = message;
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.type_message = type_message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getType_message() {
        return type_message;
    }

    public void setType_message(String type_message) {
        this.type_message = type_message;
    }
}
