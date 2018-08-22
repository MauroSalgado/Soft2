package entities;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class User {

    private String id_user;
    private String ciudad;
    private String pais;
    private String sexo;
    private String fecha_de_nacimiento;
    private String idioma;
    private String nombre;
    private String nickname;

    public User() {
    }

    public User(String ciudad, String pais, String sexo, String fecha_de_nacimiento, String idioma, String nombre, String nickname) {
        this.ciudad = ciudad;
        this.pais = pais;
        this.sexo = sexo;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.idioma = idioma;
        this.nombre = nombre;
        this.nickname = nickname;
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

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
