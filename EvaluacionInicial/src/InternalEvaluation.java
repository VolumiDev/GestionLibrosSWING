import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.lang.classfile.instruction.DiscontinuedInstruction.JsrInstruction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

public class InternalEvaluation extends JInternalFrame{

	//TEXT FIELD
	private JTextField nombre;
	private JTextField fecha;
	
	//RadioButtons
	private JRadioButton rbHombre;
	private JRadioButton rbMujer;
	private JRadioButton[] rbPreg3;
	
	//BUTTON GROUP
	private ButtonGroup bgSexo;
	private ButtonGroup bgPreg3;
	
	//CHECK BOX
	private JCheckBox[] cbPreg1;
	
	//JTEXTAREA
	private JTextArea taPreg2;
	
	//JLIST
	private JList<String> listPreg4;
	
	//JBUTTONS
	private JButton calificar;
	private JButton buscar;
	
	public InternalEvaluation() {
		super();
		// TODO Auto-generated constructor stub
		
		this.setLayout(new BorderLayout());
		//north
		JLabel titulo = new JLabel("EVALUACION INICIAL", SwingConstants.CENTER);
		titulo.setFont(new Font("arial", Font.BOLD, 20));
		titulo.setForeground(Color.RED);
		this.add(titulo, BorderLayout.NORTH);
		
		//south
		calificar = new JButton("Calificar");
		calificar.setForeground(Color.RED);
		calificar.addActionListener(new Escucha(this));
		this.add(calificar, BorderLayout.SOUTH);
		
		//center
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//		centerPanel.setPreferredSize(new Dimension(600,300));
		
		
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Datos Personales:");
		titleBorder.setTitleColor(Color.RED);
		
		JPanel datosPersonalesPanel = new JPanel();
		datosPersonalesPanel.setBorder(titleBorder);
		datosPersonalesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		datosPersonalesPanel.setPreferredSize(new Dimension(500,120));
		
		JLabel lblNomYApp = new JLabel("Nombre y Apellido:", SwingConstants.LEFT);
		lblNomYApp.setPreferredSize(new Dimension(200,20));
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento:", SwingConstants.LEFT);
		lblFecha.setPreferredSize(new Dimension(200,20));
		
		JLabel lblSexo = new JLabel("Sexo:", SwingConstants.LEFT);
		lblSexo.setPreferredSize(new Dimension(165,20));
		
		rbHombre = new JRadioButton("Hombre");
		rbMujer = new JRadioButton("Mujer");
		bgSexo = new ButtonGroup();
		bgSexo.add(rbHombre);
		bgSexo.add(rbMujer);
		
		nombre = new JTextField(15);
		fecha = new JTextField(15);
		
		buscar = new JButton("Buscar");
		buscar.addActionListener(new Escucha(this));
		
		datosPersonalesPanel.add(lblNomYApp);
		datosPersonalesPanel.add(nombre);
		datosPersonalesPanel.add(lblFecha);
		datosPersonalesPanel.add(fecha);
		datosPersonalesPanel.add(lblSexo);
		datosPersonalesPanel.add(rbHombre);
		datosPersonalesPanel.add(rbMujer);
		datosPersonalesPanel.add(buscar);
		
		centerPanel.add(datosPersonalesPanel);
		
		
		//PREGUNTAS PANEL
		JPanel preguntasPanel = new JPanel();
		preguntasPanel.setLayout(new GridLayout(2,2));
//		preguntasPanel.setPreferredSize(new Dimension(600,400));
		
		//pregunta 1
		JPanel preg1Panel = new JPanel();
		preg1Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		preg1Panel.setPreferredSize(new Dimension(300,100));
		
		JLabel lblPreg1 = new JLabel("1. Selecciona los elementos que sean SoftWare:");
		lblPreg1.setForeground(Color.RED);
		preg1Panel.add(lblPreg1);
		
		String[] titPregunta1 = {"Raton", "Sistema Operativo", "Juego de Ordenador", "Disquetera"};
		cbPreg1 = new JCheckBox[4];
		for (int i = 0; i < titPregunta1.length; i++) {
			cbPreg1[i] = new JCheckBox(titPregunta1[i]);
			cbPreg1[i].setPreferredSize(new Dimension(300,13));
			preg1Panel.add(cbPreg1[i]);
		}
		
		preguntasPanel.add(preg1Panel);
		
		//pregunta 3
		JPanel preg3Panel = new JPanel();
		preg3Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		preg3Panel.setPreferredSize(new Dimension(300,100));

		
		JLabel lblPreg3 = new JLabel("3. ¿Cual es la definicion de clase en POO?");
		lblPreg3.setForeground(Color.RED);
		preg3Panel.add(lblPreg3);
		
		String[] titPreg3 = {"Es un concepto similar a un 'Array'", 
				"Es un tipo particular de variable",
				"Es un modelo a partir del cual creamos objetos", 
				"Es una categoria de datos ordenada secuencialmente"};
		rbPreg3 = new JRadioButton[4];
		bgPreg3 = new ButtonGroup();
		for (int i = 0; i < titPreg3.length; i++) {
			rbPreg3[i] = new JRadioButton(titPreg3[i]);
			rbPreg3[i].setPreferredSize(new Dimension(250,13));
			bgPreg3.add(rbPreg3[i]);
			preg3Panel.add(rbPreg3[i]);
		}
		preguntasPanel.add(preg3Panel);
		
		//pregunta 2
		
		JPanel preg2Panel = new JPanel();
		preg2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		preg2Panel.setPreferredSize(new Dimension(300,100));

		
		JLabel lblPreg2 = new JLabel("2. ¿Que es un programa?");
		lblPreg2.setForeground(Color.RED);
		lblPreg2.setPreferredSize(new Dimension(250,13));
		preg2Panel.add(lblPreg2, BorderLayout.NORTH);
		
		taPreg2 = new JTextArea(5, 5);
		JScrollPane scroll = new JScrollPane(taPreg2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(250,75));
		preg2Panel.add(scroll);
		preguntasPanel.add(preg2Panel);
		
		
		
		//Pregunta4
		JPanel preg4Panel = new JPanel();
		preg4Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		preg4Panel.setPreferredSize(new Dimension(300,100));

		
		JLabel lblPreg4 = new JLabel("4. ¿Que es eclipse?");
		lblPreg4.setForeground(Color.RED);
		preg4Panel.add(lblPreg4, BorderLayout.NORTH);
		
		JPanel listPanel= new JPanel();
		listPanel.setPreferredSize(new Dimension(300, 200));

		DefaultListModel<String> preg4Moldel = new DefaultListModel<String>();
		preg4Moldel.addElement("Una libreria de Java");
		preg4Moldel.addElement("Una version de Java especial para servidores");
		preg4Moldel.addElement("Un IDE para desarrollar aplicaciones");
		preg4Moldel.addElement("Ninguna de las anteriores");
		listPreg4 = new JList<String>(preg4Moldel);
		listPreg4.setBackground(Color.PINK);
		listPanel.add(listPreg4);
		
		preg4Panel.add(listPanel);
		
		preguntasPanel.add(preg4Panel);

 		
		
		centerPanel.add(preguntasPanel);
		
		this.add(centerPanel, BorderLayout.CENTER);
	}

	
	public boolean estanVaciosDatos() {
		if(nombre.getText().isBlank() || fecha.getText().isBlank()) {
			return true;
		}
		return false;
	}
	
	public boolean estanVaciosSexo() {
		if(bgSexo.getSelection() == null)
			return true;
		else
			return false;
	}
	
	public boolean estanVaciosPreg1() {
		boolean disp = true;
		for (JCheckBox i : cbPreg1) {
			if(i.isSelected()) {
				return false;
			}
		}
		return disp;
	}
	
	public boolean estanVaciosPreg2() {
		if(taPreg2.getText().isBlank()) {
			return true;
			
		}else { 
			return false;
		}
	}
	public boolean estanVaciosPreg3() {
		if(bgPreg3.getSelection() != null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean estanVaciosPreg4() {
		if(listPreg4.getSelectedIndex() == -1) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean estanVaciosTodosCampos() {
		if(estanVaciosDatos() || estanVaciosSexo() || estanVaciosPreg1() || estanVaciosPreg2() ||
				estanVaciosPreg3() || estanVaciosPreg4()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean fechaCorrecta() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			LocalDate hoy = LocalDate.now();
			LocalDate f = LocalDate.parse(fecha.getText(), format);
			if(f.isBefore(hoy)) {
				return true;
			}else {
				JOptionPane.showMessageDialog(this, "Introduce una fecha anterior al dia de hoy");
				return false;
			}
		}catch(DateTimeParseException e){
			JOptionPane.showMessageDialog(this, "Introduce una fecha con formato yyyy/MM/dd");
			return false;
		}
	}
	
	
	public double respuesta1() {
		
		if(cbPreg1[0].isSelected()) {
			cbPreg1[0].setForeground(Color.RED);
		}
		if(cbPreg1[1].isSelected()) {
			cbPreg1[1].setForeground(Color.GREEN);
		}
		if(cbPreg1[2].isSelected()) {
			cbPreg1[2].setForeground(Color.GREEN);
		}
		if(cbPreg1[3].isSelected()) {
			cbPreg1[3].setForeground(Color.RED);
		}
		
		if(!cbPreg1[0].isSelected() && cbPreg1[1].isSelected() && cbPreg1[2].isSelected() && !cbPreg1[3].isSelected()) {
			return 1;
		}else {
			return -0.5;
		}
	}
	
	public double respuesta2() {
		
		if(taPreg2.getText().equalsIgnoreCase("conjunto de instrucciones que permiten realizar una tarea")) {
			taPreg2.setForeground(Color.GREEN);
			return 1;
		}else
			taPreg2.setForeground(Color.RED);
			return -0.5;
	}
	
	public double respuesta3() {
		if(rbPreg3[0].isSelected()) {
			rbPreg3[0].setForeground(Color.RED);
		}
		if(rbPreg3[1].isSelected()) {
			rbPreg3[1].setForeground(Color.RED);
		}
		if(rbPreg3[3].isSelected()) {
			rbPreg3[3].setForeground(Color.RED);
		}
		if(rbPreg3[2].isSelected()) {
			rbPreg3[2].setForeground(Color.GREEN);
			return 1;
		}else {
			return -0.5;
		}
		
	}
	
	public double respuesta4() {
		if(listPreg4.getSelectedIndex() == 2) {
			listPreg4.setSelectionBackground(Color.GREEN);
			return 1;
		}else {
			listPreg4.setSelectionBackground(Color.RED);
			return -0.5;
		}
	}
	
	public void mostrarPuntuacion() {
		double puntuacion = respuesta1() + respuesta2() + respuesta3() + respuesta4();
		//JOptionPane.showMessageDialog(this, "La puntuacion de " +nombre.getText()+ " es de "+ puntuacion + " puntos");
		
	}
	
	public boolean existNombre() throws ClassNotFoundException, SQLException {
		String sql = "Select * from resultados where nombre like '"+nombre.getText()+"'";
		Conexion con = new Conexion();
		ResultSet rs = con.consulta(sql);
		if(rs.isBeforeFirst()) {
			con.cerrar();
			return true;
		}else {
			con.cerrar();
			return false;
		}
	}
	
	public boolean tieneIntentos() throws ClassNotFoundException, SQLException {
		boolean flag = false;
		String sql = "select intentos from resultados where nombre like '"+nombre.getText()+"'";
		Conexion con = new Conexion();
		ResultSet rs = con.consulta(sql);
		while(rs.next()) {
			if(rs.getInt(1) < 3 ) {
				flag = true;
			}else {
				flag = false;
			}
		}
		con.cerrar();
		return flag;
	}
	
	public void buscarNombre() throws ClassNotFoundException, SQLException {
		String nom = JOptionPane.showInputDialog(this, "Introduce tu nombre completo");
		String sql = "select * from resultados where nombre like '"+nom+"'";
		Conexion con = new Conexion();
		ResultSet rs = con.consulta(sql);
		if(rs.isBeforeFirst()) {
			while(rs.next()) {
				nombre.setText(rs.getString(1));
				fecha.setText(rs.getString(2));
				if(rs.getString(3).equalsIgnoreCase("Hombre")) {
					rbHombre.setSelected(true);
				}else {
					rbMujer.setSelected(true);
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "No registrado, debe introducir sus datos");
		}
		con.cerrar();
	}
	
	public void insertDatos() throws ClassNotFoundException, SQLException {
		String sexo = "";
		if(rbHombre.isSelected()) {
			sexo = "Hombre";
		}else {
			sexo = "Mujer";
		}
		String sql = "insert into resultados values ('"+nombre.getText()+"', '"+fecha.getText()+"',"
				+ " '"+sexo+"', "+respuesta1()+", "+respuesta2()+", "+respuesta3()+", "+respuesta4()+
				", "+(respuesta1()+respuesta2()+respuesta3()+ respuesta4())+", 1)";
		Conexion con = new Conexion();
		con.actualizar(sql);
		JOptionPane.showMessageDialog(this, "Nuevo usuario registrado");
		con.cerrar();
	}
	
	public void actualizarDatos() throws ClassNotFoundException, SQLException {
		int intent=0;
		
		String sql = "select intentos from resultados where nombre like '"+nombre.getText()+"'";
		Conexion con = new Conexion();
		ResultSet rs = con.consulta(sql);
		while(rs.next()) {
			intent = rs.getInt(1);
		}
		
		sql = "Update  resultados set Pregunta1 = "+respuesta1()+", Pregunta2 = "+respuesta2()+", Pregunta3 = "+respuesta3()+", Pregunta4 = "+respuesta4()+","
				+ "Puntos = "+(respuesta1()+respuesta2()+respuesta3()+respuesta4())+", Intentos = "+(intent+1)+" where Nombre like '"+nombre.getText()+"'";
		con.actualizar(sql);
		con.cerrar();
	}
	
	public void resetearPreguntas() {
		for (JCheckBox i : cbPreg1) {
			i.setSelected(false);
			i.setForeground(Color.BLACK);
		}
		
		taPreg2.setText("");
		taPreg2.setForeground(Color.BLACK);
		
		bgPreg3.clearSelection();
		for (JRadioButton i : rbPreg3) {
			i.setForeground(Color.BLACK);
		}

		listPreg4.setSelectionBackground(Color.PINK);
		listPreg4.setSelectedIndex(-1);
	}
	
}
