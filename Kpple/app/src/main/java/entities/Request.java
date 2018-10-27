package entities;

public class Request {
    private String id;
    private String flag;
    private String typeReq;

    public Request() {
    }

    public Request(String id, String flag, String typeReq) {
        this.id = id;
        this.flag = flag;
        this.typeReq = typeReq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTypeReq() {
        return typeReq;
    }

    public void setTypeReq(String typeReq) {
        this.typeReq = typeReq;
    }
}
