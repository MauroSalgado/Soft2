package entities;


import java.util.List;

public class Hobbie {

    private String name;
    private String url;
    private List<User> list_users;

    public Hobbie() {
    }

    public Hobbie(String name, String url, List<User> list_users) {
        this.name = name;
        this.url = url;
        this.list_users = list_users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<User> getList_users() {
        return list_users;
    }

    public void setList_users(List<User> list_users) {
        this.list_users = list_users;
    }
}
