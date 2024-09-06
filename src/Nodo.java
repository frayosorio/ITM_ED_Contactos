public class Nodo {

    private final String SEPARADOR = "\t";

    String nombre;
    String telefono;
    String celular;
    String direccion;
    String correo;

    Nodo siguiente;

    public Nodo() {
        siguiente = null;
    }

    private String noVacio(String texto) {
        return texto == null || texto.length() == 0 ? " " : texto;
    }

    @Override
    public String toString() {
        return noVacio(nombre) + SEPARADOR +
                noVacio(telefono) + SEPARADOR +
                noVacio(celular) + SEPARADOR +
                noVacio(direccion) + SEPARADOR +
                noVacio(correo);
    }

    public Nodo(String nombre, String telefono, String celular, String direccion, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.correo = correo;
        siguiente = null;
    }

    public void actualizar(String nombre,
            String telefono,
            String celular,
            String direccion,
            String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.correo = correo;
    }

}
