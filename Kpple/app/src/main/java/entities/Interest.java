package entities;

import java.util.List;

public class Interest {
    private String name;
    private String url_img;
    private List<User> list_users;

    public Interest() {
    }

    public Interest(String name, String url) {
        this.name = name;
        this.url_img = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public List<User> getList_users() {
        return list_users;
    }

    public void setList_users(List<User> list_users) {
        this.list_users = list_users;
    }
}
