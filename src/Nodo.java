public class Nodo {

    String nombre;
    String telefono;
    String celular;
    String direccion;
    String correo;

    Nodo siguiente;

    public Nodo() {
        siguiente = null;
    }

    public Nodo(String nombre, String telefono, String celular, String direccion, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.correo = correo;
        siguiente = null;
    }

}
