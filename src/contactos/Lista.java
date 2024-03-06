package contactos;

import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Lista {

    private Nodo cabeza;

    public Lista() {
        cabeza = null;
    }

    //Agrega un nodo al final de la lista
    public void agregar(Nodo n) {
        if (n != null) {
            if (cabeza == null) {
                cabeza = n;
            } else {
                //recorrer la lista hasta el último nodo
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
        //recorrer la lista completa
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            apuntador = apuntador.siguiente;
            totalNodos++;
        }
        return totalNodos;
    }

    //Devuelve el nodo ubicado en una posicion
    public Nodo getNodo(int posicion) {
        int p = 0;
        Nodo apuntador = cabeza;
        while (apuntador != null && p != posicion) {
            apuntador = apuntador.siguiente;
            p++;
        }
        if (apuntador != null && p == posicion) {
            return apuntador;
        } else {
            return null;
        }
    }

    //cambia los valores de un nodo dada la posición
    public void actualizar(int posicion,
            String nombre, String telefono, String celular, String direccion, String correo) {
        Nodo n = getNodo(posicion);
        if (n != null) {
            n.actualizar(nombre, telefono, celular, direccion, correo);
        }
    }

    //Muestra la lista en una tabla
    public void mostrar(JTable tbl) {
        int filas = getLongitud();

        String[][] datos = new String[filas][encabezados.length];
        //recorrer la lista completa
        int fila = 0;
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            //asignar los datos a la matriz fuente de datos de la tabla
            datos[fila][0] = apuntador.nombre;
            datos[fila][1] = apuntador.telefono;
            datos[fila][2] = apuntador.celular;
            datos[fila][3] = apuntador.direccion;
            datos[fila][4] = apuntador.correo;
            fila++;
            apuntador = apuntador.siguiente;
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, Lista.encabezados);
        dtm.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int fila = e.getFirstRow();
                DefaultTableModel dtm = (DefaultTableModel) e.getSource();
                actualizar(fila, (String) dtm.getValueAt(fila, 0),
                        (String) dtm.getValueAt(fila, 1),
                        (String) dtm.getValueAt(fila, 2),
                        (String) dtm.getValueAt(fila, 3),
                        (String) dtm.getValueAt(fila, 4));
            }
        });

        tbl.setModel(dtm);
    }

    private String noVacio(String texto) {
        return texto.length() == 0 ? " " : texto;
    }

    public boolean guardar(String nombreArchivo) {
        int totalFilas = getLongitud();
        if (totalFilas > 0) {
            String[] lineas = new String[totalFilas];

            Nodo n = cabeza;
            int fila = 0;
            while (n != null) {
                lineas[fila] = noVacio(n.nombre) + "\t" + noVacio(n.telefono) + "\t" + noVacio(n.celular) + "\t" + noVacio(n.direccion) + "\t" + noVacio(n.correo);
                fila++;
                n = n.siguiente;
            }
            return Archivo.guardarArchivo(nombreArchivo, lineas);
        } else {
            return false;
        }
    }

    //Elimina un nodo de la lista
    public void eliminar(Nodo n) {
        if (n != null && cabeza != null) {
            //Buscar el nodo
            boolean encontrado = false;
            Nodo apuntador = cabeza;
            Nodo anterior = null;
            while (apuntador != null && !encontrado) {
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

    //Llena la lista desde un archivo plano
    public void desdeArchivo(String nombreArchivo) {
        cabeza = null;
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        try {
            String linea = br.readLine();
            while (linea != null) {
                String[] textos = linea.split("\t");
                if (textos.length >= 5) {
                    Nodo n = new Nodo(textos[0],
                            textos[1],
                            textos[2],
                            textos[3],
                            textos[4]);
                    agregar(n);
                }
                linea = br.readLine();
            }
        } catch (IOException ex) {
        }
    }

    //metodo para intercambio de nodos
    public void intercambiar(Nodo n1, Nodo n2, Nodo a1, Nodo a2) {
        if (cabeza != null && n1 != n2 && n1 != null && n2 != null && a2 != null) {
            if (a1 != null) {
                a1.siguiente = n2;
            } else {
                cabeza = n2;
            }
            //Se guarda temporalmente el apuntador siguiente del segundo nodo
            Nodo t = n2.siguiente;
            if (n1 != a2) {
                n2.siguiente = n1.siguiente;
                a2.siguiente = n1;
            } else {
                n2.siguiente = n1;
            }
            n1.siguiente = t;
        }
    }

    public void ordenar() {
        Nodo ni = cabeza;
        Nodo ai = null;
        while (ni.siguiente != null) {

            Nodo nj = ni.siguiente;
            Nodo aj = ni;
            while (nj != null) {
                if (ni.nombre.compareTo(nj.nombre) > 0) {
                    intercambiar(ni, nj, ai, aj);
                    Nodo t = ni;
                    ni = nj;
                    nj = t;
                }

                aj = nj;
                nj = nj.siguiente;
            }

            ai = ni;
            ni = ni.siguiente;
        }
    }

//    public void mostrarConsola(){
//        Nodo apuntador = cabeza;
//        while (apuntador != null) {
//            System.out.println(apuntador.nombre);
//            apuntador = apuntador.siguiente;
//        }
//    }
    // ******* Variables y métodos estaticos *********
    public static String[] encabezados = new String[]{"Nombre", "Telefono", "Celular", "Direccion", "Correo"};

}
