import java.io.BufferedReader;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class Lista {

    private Nodo cabeza;

    public Lista() {
        cabeza = null;
    }

    public void agregar(Nodo n) {
        if (n != null) {
            if (cabeza == null) {
                cabeza = n;
            } else {
                Nodo apuntador = cabeza;
                while (apuntador.siguiente != null) {
                    apuntador = apuntador.siguiente;
                }
                apuntador.siguiente = n;
            }
            n.siguiente = null;
        }
    }

    public int getLongitud() {
        int totalNodos = 0;

        Nodo apuntador = cabeza;
        while (apuntador != null) {
            totalNodos++;
            apuntador = apuntador.siguiente;
        }
        return totalNodos;
    }

    public Nodo obtener(int posicion) {
        int p = 0;
        Nodo apuntador = cabeza;
        while (apuntador != null && p < posicion) {
            apuntador = apuntador.siguiente;
            p++;
        }
        return p == posicion && apuntador != null ? apuntador : null;
    }

    public void eliminar(Nodo n) {
        if (n != null && cabeza != null) {
            boolean encontrado = false;
            Nodo apuntador = cabeza;
            Nodo anterior = null;
            while (!encontrado && apuntador != null) {
                if (apuntador == n) {
                    encontrado = true;
                } else {
                    anterior = apuntador;
                    apuntador = apuntador.siguiente;
                }
            }
            if (encontrado) {
                if (anterior == null) {
                    cabeza = apuntador.siguiente;
                } else {
                    anterior.siguiente = apuntador.siguiente;
                }
            }
        }
    }

    public void desdeArchivo(String nombreArchivo) {
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        try {
            String linea = br.readLine();
            while (linea != null) {
                String[] datos = linea.split("\t");
                if (datos.length >= 5) {
                    Nodo n = new Nodo(datos[0], datos[1], datos[2], datos[3], datos[4]);
                    agregar(n);
                }
                linea = br.readLine();
            }

        } catch (Exception ex) {

        }
    }

    public boolean haciaArchivo(String nombreArchivo) {
        String[] lineas = new String[getLongitud()];
        int fila = 0;
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            lineas[fila++] = apuntador.toString();
            apuntador = apuntador.siguiente;
        }

        return Archivo.guardarArchivo(nombreArchivo, lineas);
    }

    public void actualizar(int posicion,
            String nombre,
            String telefono,
            String celular,
            String direccion,
            String correo) {
        Nodo n = obtener(posicion);
        if (n != null) {
            n.actualizar(nombre, telefono, celular, direccion, correo);
        }
    }

    public void mostrar(JTable tbl) {
        String[] encabezados = new String[] { "Nombre", "Telefono", "Celular", "Direccion", "Correo" };
        String[][] datos = new String[getLongitud()][5];

        int fila = 0;
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            datos[fila][0] = apuntador.nombre;
            datos[fila][1] = apuntador.telefono;
            datos[fila][2] = apuntador.celular;
            datos[fila][3] = apuntador.direccion;
            datos[fila][4] = apuntador.correo;

            fila++;
            apuntador = apuntador.siguiente;
        }

        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        dtm.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int fila = e.getFirstRow();
                DefaultTableModel dtm = (DefaultTableModel) e.getSource();
                actualizar(fila,
                        (String) dtm.getValueAt(fila, 0),
                        (String) dtm.getValueAt(fila, 1),
                        (String) dtm.getValueAt(fila, 2),
                        (String) dtm.getValueAt(fila, 3),
                        (String) dtm.getValueAt(fila, 4));
            }

        });

        tbl.setModel(dtm);
    }

}
