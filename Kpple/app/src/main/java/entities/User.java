package entities;

public class User {

    private String id_user;
    private String ciudad;
    private String pais;
    private String sexo;
    private String fecha_nacimiento;
    private String nickname;
    private String nombre;
    private String idioma;

    public User() {
    }

    public User(String id_user, String ciudad, String pais, String sexo, String fecha_nacimiento, String nickname, String nombre, String idioma) {
        this.id_user = id_user;
        this.ciudad = ciudad;
        this.pais = pais;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nickname = nickname;
        this.nombre = nombre;
        this.idioma = idioma;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
