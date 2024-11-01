import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VentanaGestion extends JInternalFrame{

	private JList<String> listaLibros;
	private DefaultListModel<String> listModel; 
	private JPanel listPanel;
	
	private JButton[] botonesAccion;
	
	private JPanel detallesLibro;
	
	private JTextField titulo;
	private JTextField autor;
	private JTextField isbn;
	
	private JRadioButton[] categorias;
	private ButtonGroup radiosCat;
	
	private JButton[] botonesLibro;
	
	public VentanaGestion(VentanaPrincipal vp) {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		//ELEMENTO LISTA
		listModel = new DefaultListModel<String>();
		listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(200,200));
		listPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		listaLibros = new JList<String>();
		listaLibros.setPreferredSize(new Dimension(180,220));
		listaLibros.addListSelectionListener(e -> {
			botonesAccion[1].setEnabled(true);
			botonesAccion[2].setEnabled(true);
		});		
		try {
			this.getTituloLibros(listModel);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listPanel.add(listaLibros);
		this.add(listPanel);
		
		
		//ELEMENTOS BOTONES
		botonesAccion = new JButton[3];
		String[] titulosBotonesAccion = {"Agregar Libro", "Editar libro", "Eliminar libro"};
		JPanel panelBotonesAccion = new JPanel();
		panelBotonesAccion.setPreferredSize(new Dimension(300,200));
		panelBotonesAccion.setLayout(new FlowLayout(FlowLayout.CENTER));
		for (int i = 0; i < botonesAccion.length; i++) {
			botonesAccion[i] = new JButton(titulosBotonesAccion[i]);
			botonesAccion[i].addActionListener(new ListenerGestion(this, vp));
			botonesAccion[i].setPreferredSize(new Dimension(200,40));
			panelBotonesAccion.add(botonesAccion[i]);
		}
		botonesAccion[1].setEnabled(false);
		botonesAccion[2].setEnabled(false);
		this.add(panelBotonesAccion);
		
		
		//PANEL OCULTO CON LOS DATOS DEL LIBRO
		detallesLibro = new JPanel();
		detallesLibro.setLayout(new FlowLayout(FlowLayout.CENTER));
		detallesLibro.setPreferredSize(new Dimension(500,300));
		
			//titulo
		JLabel lblDetallesLibros = new JLabel("Detalles libro");
		lblDetallesLibros.setPreferredSize(new Dimension(490,20));
		detallesLibro.add(lblDetallesLibros);
		
		
			//inputs
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		inputsPanel.setPreferredSize(new Dimension(200,150));
		JPanel tituloPanel= lblYTF("Titulo:");
		JPanel autorPanel= lblYTF("Autor:");
		JPanel isbnPanel= lblYTF("ISBN:");
		inputsPanel.add(tituloPanel);
		inputsPanel.add(autorPanel);
		inputsPanel.add(isbnPanel);
		
		
			//botontes
		botonesLibro = new JButton[2];
		String[] titulosBotonesLibros = {"Guardar", "Cancelar"};
		for (int i = 0; i < botonesLibro.length; i++) {
			botonesLibro[i] = new JButton(titulosBotonesLibros[i]);
			botonesLibro[i].addActionListener(new ListenerGestion(this, vp));
			botonesLibro[i].setPreferredSize(new Dimension(90,40));
		}
		
		
			//radios
		JPanel categoriasPanel = new JPanel();
		categoriasPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		categoriasPanel.setPreferredSize(new Dimension(200, 150));
		radiosCat = new ButtonGroup();
		
		JLabel lblCat = new JLabel("Categorias");
		lblCat.setPreferredSize(new Dimension(190,20));
		categoriasPanel.add(lblCat);
		
		String[] titRadios = {"Ficcion", "No ficcion", "Educativo", "Otra" };
		categorias = new JRadioButton[4];
		for (int i = 0; i < categorias.length; i++) {
			categorias[i] = new JRadioButton(titRadios[i]);
			categorias[i].setPreferredSize(new Dimension(90,20));
			radiosCat.add(categorias[i]);
			categoriasPanel.add(categorias[i]);
		}
		
			//añadimos paneles
		JPanel radiosYBotonesPanel = new JPanel();
		radiosYBotonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		radiosYBotonesPanel.setPreferredSize(new Dimension(200,200));
		
		radiosYBotonesPanel.add(categoriasPanel);
		radiosYBotonesPanel.add(botonesLibro[0]);
		radiosYBotonesPanel.add(botonesLibro[1]);
		
		detallesLibro.add(inputsPanel);
		detallesLibro.add(radiosYBotonesPanel);
		
		
		
		
		
		
			
		
		
		
		
		this.setVisible(true);
	}
	
	//HACE UNA CONSULTA A BASE DE DATOS Y MUESTRA EL TITULO DE TODOS LO LIBROS DE LA BIBLIOTECA
	public void getTituloLibros(DefaultListModel listModel) throws ClassNotFoundException, SQLException {
		listModel.removeAllElements();
		Conexion con = new Conexion();
		String sql = "select titulo from libros";
		ResultSet rs = con.consulta(sql);
		if(rs.isBeforeFirst()) {
			while(rs.next()) {
				listModel.addElement(rs.getString(1));
			}
		}else {
			listModel.addElement("No hay libros en BD");
		}
		con.cerrar();
		listaLibros.setModel(listModel);
	}
	
	//METODO QUE FORMA PANEL DE LBL Y TF
	private JPanel lblYTF(String titulo) {
		JPanel inputPanel = new JPanel();
		JLabel lbl;
		inputPanel.setPreferredSize(new Dimension(200,30));
		inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		switch (titulo) {
		case "Titulo:": 
			this.titulo = new JTextField(10);
			lbl = new JLabel(titulo);
			inputPanel.add(lbl);
			inputPanel.add(this.titulo);
			break;
		case "Autor:":
			this.autor = new JTextField(10);
			lbl = new JLabel(titulo);
			inputPanel.add(lbl);
			inputPanel.add(this.autor);
			break;
		case "ISBN:":
			this.isbn = new JTextField(10);
			lbl = new JLabel(titulo);
			inputPanel.add(lbl);
			inputPanel.add(this.isbn);
			break;
		}
		return inputPanel;
	}

	
	
	
	//VACIAMOS LOS DETALLES LIBRO
	public void vaciarDetalles() {
		titulo.setText("");
		autor.setText("");
		isbn.setText("");
		radiosCat.clearSelection();
	}
	
	//VALIDAMOS CAMPOS VACIOS
	public boolean estanVacios() {
		boolean flag = false;
		boolean flagradios = false;
		if(titulo.getText().isBlank() || autor.getText().isBlank() || isbn.getText().isBlank()) {
			flag = true;
		}
		for (int i = 0; i < categorias.length && flag == false; i++) {
			if(categorias[i].isSelected()) {
				flagradios = true;
			}
		}
		if(flag == false && flagradios == true) {
			return false;
		}else {
			return true;
		}
	}
	
	
	
	
	
	
	
	
	
	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}

	public JPanel getListPanel() {
		return listPanel;
	}

	public void setListPanel(JPanel listPanel) {
		this.listPanel = listPanel;
	}

	public JList<String> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(JList<String> listaLibros) {
		this.listaLibros = listaLibros;
	}

	public JButton[] getBotonesAccion() {
		return botonesAccion;
	}

	public void setBotonesAccion(JButton[] botonesAccion) {
		this.botonesAccion = botonesAccion;
	}

	public JPanel getDetallesLibro() {
		return detallesLibro;
	}

	public void setDetallesLibro(JPanel detallesLibro) {
		this.detallesLibro = detallesLibro;
	}

	public JTextField getTitulo() {
		return titulo;
	}

	public void setTitulo(JTextField titulo) {
		this.titulo = titulo;
	}

	public JTextField getAutor() {
		return autor;
	}

	public void setAutor(JTextField autor) {
		this.autor = autor;
	}

	public JTextField getIsbn() {
		return isbn;
	}

	public void setIsbn(JTextField isbn) {
		this.isbn = isbn;
	}

	public JRadioButton[] getCategorias() {
		return categorias;
	}

	public void setCategorias(JRadioButton[] categorias) {
		this.categorias = categorias;
	}

	public ButtonGroup getRadiosCat() {
		return radiosCat;
	}

	public void setRadiosCat(ButtonGroup radiosCat) {
		this.radiosCat = radiosCat;
	}

	public JButton[] getBotonesLibro() {
		return botonesLibro;
	}

	public void setBotonesLibro(JButton[] botonesLibro) {
		this.botonesLibro = botonesLibro;
	}
	
	
}
