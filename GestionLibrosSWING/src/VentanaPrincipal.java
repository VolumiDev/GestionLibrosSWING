import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaPrincipal extends JFrame {

	private JMenuBar barraMenu;

	private JMenu menuArchivo;
	private JMenu menuAyuda;

	private JMenuItem exportarFichero;
	private JMenuItem gestionLibros;
	private JMenuItem salir;

	public VentanaPrincipal() {
		// TODO Auto-generated constructor stub
		super();
		// INICIALIZAMOS COMPONENTES
		barraMenu = new JMenuBar();

		menuArchivo = new JMenu("Archivo");
		menuAyuda = new JMenu("Ayuda");

		exportarFichero = new JMenuItem("Exportar Fichero de texto");
		gestionLibros = new JMenuItem("Gestion Libros");
		salir = new JMenuItem("Salir");

		// AÑADIMOS LOS ITEMS A MENU
		menuArchivo.add(exportarFichero);
		menuArchivo.add(gestionLibros);
		menuArchivo.add(salir);

		// AÑADIMOS LOS MENUS A LA BARRA
		barraMenu.add(menuArchivo);
		barraMenu.add(menuAyuda);

		// AÑADIMOS LA BARRA DE MENU A LA VENTANA
		this.setJMenuBar(barraMenu);

		// OPERATIVA
		salir.addActionListener(e -> {
			System.exit(0);
		});

		gestionLibros.addActionListener(e -> {
			VentanaGestion vg = new VentanaGestion(this);
			this.getContentPane().removeAll();
			this.getContentPane().add(vg);
		});

		exportarFichero.addActionListener(e -> {
			try {
				escribirFichero();
			} catch (ClassNotFoundException | IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	// ESCRIBIMOS EN FICHERO TXT
	public void escribirFichero() throws IOException, ClassNotFoundException, SQLException {
		
		File txt = new File("Libros.txt");
		Conexion con = new Conexion();
		String sql = "select * from libros";
		FileWriter fw  = new FileWriter(txt);
		PrintWriter pw = new PrintWriter(fw);
		
		ResultSet rs = con.consulta(sql);
		if(rs.isBeforeFirst()) {
			while(rs.next()) {
				pw.println("ISBN: " + rs.getString(1));
				pw.println("Titulo: " + rs.getString(2));
				pw.println("Autor: " + rs.getString(3));
				pw.println("Categoria : " + rs.getString(4));
				pw.println("---------------------------------");
			}
		}
		con.cerrar();
		pw.close();
		fw.close();
	}

}
