package entities;

public class User {

    private String idUser;
    private String city;
    private String country;
    private String sex;
    private String birthday;
    private String nickname;
    private String name;
    private String language;
    private String code;
    private Boolean active;
    private String urlImgProfile;

    public User() {
    }

    public User(String idUser, String city, String country, String sex, String birthday,
                String nickname, String name, String language, String code, Boolean active, String urlImgProfile) {
        this.idUser = idUser;
        this.city = city;
        this.country = country;
        this.sex = sex;
        this.birthday = birthday;
        this.nickname = nickname;
        this.name = name;
        this.language = language;
        this.code = code;
        this.active = active;
        this.urlImgProfile = urlImgProfile;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUrlImgProfile() {
        return urlImgProfile;
    }

    public void setUrlImgProfile(String urlImgProfile) {
        this.urlImgProfile = urlImgProfile;
    }
}
