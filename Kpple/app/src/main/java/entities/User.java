package entities;

public class User {
    private String nombre;
    private int cedula;

    public User() {
    }

    public User(String name, int cedula) {
        this.nombre = name;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
}
