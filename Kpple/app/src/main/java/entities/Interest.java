package entities;

import java.util.List;

public class Interest {

    private String name;
    private String url;
    private List<String> list_users;

    public Interest() {
    }

    public Interest(String name, String url, List<String> list_users) {
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

    public List<String> getList_users() {
        return list_users;
    }

    public void setList_users(List<String> list_users) {
        this.list_users = list_users;
    }
}
