import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;

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

        JScrollPane spContactos = new JScrollPane(tblContactos);

        getContentPane().add(tbContactos, BorderLayout.NORTH);
        getContentPane().add(spContactos, BorderLayout.CENTER);

        String nombreArchivo = System.getProperty("user.dir") + "/src/datos/Datos.txt";

        lContactos.desdeArchivo(nombreArchivo);
        lContactos.mostrar(tblContactos);

    }

}
