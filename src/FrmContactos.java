import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmContactos extends JFrame {

    private JToolBar tbContactos;
    private JTable tblContactos;

    Lista lContactos = new Lista();

    public FrmContactos() {
        setSize(600, 300);
        setTitle("Mis contactos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tbContactos = new JToolBar();
        tblContactos = new JTable();

        JButton btnAgregar = new JButton();
        JButton btnEliminar = new JButton();
        JButton btnGuardar = new JButton();
        JButton btnOrdenar = new JButton();

        btnAgregar.setIcon(new ImageIcon(getClass().getResource("/imagenes/Agregar.png")));
        btnAgregar.setToolTipText("Agregar");
        btnEliminar.setIcon(new ImageIcon(getClass().getResource("/imagenes/Eliminar.png")));
        btnEliminar.setToolTipText("Eliminar");
        btnGuardar.setIcon(new ImageIcon(getClass().getResource("/imagenes/Guardar.png")));
        btnGuardar.setToolTipText("Guardar");
        btnOrdenar.setIcon(new ImageIcon(getClass().getResource("/imagenes/Ordenar.png")));
        btnOrdenar.setToolTipText("Ordenar");

        tbContactos.add(btnAgregar);
        tbContactos.add(btnEliminar);
        tbContactos.add(btnGuardar);
        tbContactos.add(btnOrdenar);

        JScrollPane spContactos = new JScrollPane(tblContactos);

        getContentPane().add(tbContactos, BorderLayout.NORTH);
        getContentPane().add(spContactos, BorderLayout.CENTER);

        String nombreArchivo = System.getProperty("user.dir") + "/src/datos/Datos.txt";

        lContactos.desdeArchivo(nombreArchivo);
        lContactos.mostrar(tblContactos);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAgregar_Click(e);
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnEliminar_Click(e);
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardar_Click(e);
            }
        });

        btnOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOrdenar_Click(e);
            }
        });

    }

    private void btnAgregar_Click(ActionEvent e) {
        lContactos.agregar(new Nodo());
        lContactos.mostrar(tblContactos);
    }

    private void btnEliminar_Click(ActionEvent e) {
        if (tblContactos.getSelectedRow() >= 0) {
            lContactos.eliminar(lContactos.obtener(tblContactos.getSelectedRow()));
            lContactos.mostrar(tblContactos);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la lista");
        }
    }

    private void btnGuardar_Click(ActionEvent e) {

    }

    private void btnOrdenar_Click(ActionEvent e) {

    }

}
