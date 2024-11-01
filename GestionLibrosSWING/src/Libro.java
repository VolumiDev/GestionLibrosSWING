import java.awt.JobAttributes;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JOptionPane;

public class Libro {

	private String isbn;
	private String titulo;
	private String autor;
	private String categoria;
	
	
	public Libro(String isbn, String titulo, String autor, String categoria) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
	}
	
	public Libro() {
		// TODO Auto-generated constructor stub
	}

	//INSERTAR LIBRO EN LA BD
	public void insertLibro(VentanaGestion vg) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		if(vg.estanVacios() == false) {
			this.titulo = vg.getTitulo().getText();
			this.autor = vg.getAutor().getText();
			this.isbn = vg.getIsbn().getText();
			
			
			for (int i = 0; i < vg.getCategorias().length && flag == false; i++) {
				if(vg.getCategorias()[i].isSelected()) {
					this.categoria = vg.getCategorias()[i].getText();
					flag = true;
				}
			}
			Conexion con = new Conexion();
			String sql = "insert into libros values('"+isbn+"','"+titulo+"','"+autor+"','"+categoria+"')";
			if(con.actualizar(sql) == 1) {
				JOptionPane.showMessageDialog(vg, "Libro insertado correctamente");
			};
			con.cerrar();
		}else {
			JOptionPane.showMessageDialog(vg, "No puede dejar campos vacios");
		}
	}
	
	
	//ELINAR LIBRO DE LA BD
	public void eliminarLibro(VentanaGestion vg, String tit) throws ClassNotFoundException, SQLException {
		String sql = "delete from libros where titulo like '"+tit+"'";
		Conexion con = new Conexion();
		if(con.actualizar(sql) == 1) {
			JOptionPane.showMessageDialog(vg, "El libro se elimino de la base de datos");
		}
		con.cerrar();
	}
	
	//GET LIBRO DE LA BD
	public void getLibro(VentanaGestion vg,String tit) throws ClassNotFoundException, SQLException {
		String sql = "select * from libros where titulo like '"+tit+"'";
		Conexion con = new Conexion();
		ResultSet rs = con.consulta(sql);
		if(rs.isBeforeFirst()) {
			while(rs.next()) {
				this.isbn = rs.getString(1);
				this.titulo = rs.getString(2);
				this.autor = rs.getString(3);
				this.categoria = rs.getString(4);
			}
		}else {
			JOptionPane.showMessageDialog(vg, "El libro no esta en la BD");
		}
		con.cerrar();
	}
	
	
	//EDITAR LIBRO DE LA BD 
	public void editLibro(VentanaGestion vg) throws ClassNotFoundException, SQLException {
		boolean flag = false;
		if(vg.estanVacios() == false) {
			this.titulo = vg.getTitulo().getText();
			this.autor = vg.getAutor().getText();
			this.isbn = vg.getIsbn().getText();
			
			
			for (int i = 0; i < vg.getCategorias().length && flag == false; i++) {
				if(vg.getCategorias()[i].isSelected() == false) {
					this.categoria = vg.getCategorias()[i].getText();
					flag = true;
				}
			}
			Conexion con = new Conexion();
			String sql = "update libros set titulo = '"+titulo+"', autor = '"+autor+"', categoria = '"+categoria+"' where isbn = '"+isbn+"'";
			if(con.actualizar(sql) == 1) {
				JOptionPane.showMessageDialog(vg, "Libro editado correctamente");
			};
			con.cerrar();
		}else {
			JOptionPane.showMessageDialog(vg, "No puede dejar campos vacios");
		}
	}
	
	//MOSTRAR INFO EN INPUTS
	public void setInputs(VentanaGestion vg) {
		boolean flag = false;
		vg.getTitulo().setText(this.titulo);
		vg.getAutor().setText(this.autor);
		vg.getIsbn().setText(this.isbn);
		
		for (int i = 0; i < vg.getCategorias().length && flag == false; i++) {
			if(vg.getCategorias()[i].getText().equalsIgnoreCase(this.categoria)) {
				vg.getCategorias()[i].setSelected(true);
				flag = true;
			}
		}
	}
		
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
