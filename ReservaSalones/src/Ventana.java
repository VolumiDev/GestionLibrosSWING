import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.io.DataOutputStream;
import java.lang.classfile.instruction.ConstantInstruction.ArgumentConstantInstruction;
import java.nio.channels.IllegalBlockingModeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.management.remote.rmi.RMIConnectionImpl;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

public class Ventana extends JFrame{
	
	//labels
	private JLabel lblNom;
	private JLabel lblTel;
	private JLabel lblTipoEvento;
	private JLabel lblFecha;
	private JLabel lblCocina;
	private JLabel lblnumPersonas;
	private JLabel lblAux1;
	private JLabel lblAux2;
	
	//tField
	private JTextField nombre;
	private JTextField telefono;
	private JTextField fecha;
	private JTextField numPersonas;
	private JTextField inputAux;

	
	//radioButtons
	private JRadioButton rbBanquete;
	private JRadioButton rbCongreso;
	private JRadioButton rbAux1;
	private JRadioButton rbAux2;

	
	//button group
	private ButtonGroup radios;
	private ButtonGroup auxRadios;
	
	//ComboBox;
	private JComboBox<String> cocina;
	
	//Paneles
	private JPanel auxiliar;
	private JPanel botones;
	
	//Buttons
	private JButton reservar;
	private JButton limpiar;
	private JButton cerrar;

	
	public Ventana() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		//panel datos personales.
		JPanel datosPersonales = new JPanel();
		datosPersonales.setLayout(new FlowLayout());
		datosPersonales.setPreferredSize(new Dimension(350,80));
		
		
		lblNom = new JLabel("Nombre: ");
		lblNom.setPreferredSize(new Dimension(100,20));
		lblTel = new JLabel("Telefono: ");
		lblTel.setPreferredSize(new Dimension(100,20));
		nombre = new JTextField(13);
		telefono = new JTextField(13);
		
		datosPersonales.add(lblNom);
		datosPersonales.add(nombre);
		datosPersonales.add(lblTel);
		datosPersonales.add(telefono);
		
		datosPersonales.setBorder(new TitledBorder("Datos Personales"));
		
		this.getContentPane().add(datosPersonales);
		
		//datos reserva
		
		JPanel datosReserva = new JPanel();
		datosReserva.setLayout(new FlowLayout());
		datosReserva.setPreferredSize(new Dimension(350,110));
		
		lblTipoEvento = new JLabel("Tipo evento:");
		lblTipoEvento.setPreferredSize(new Dimension(120,20));

		rbBanquete = new JRadioButton("Banquete");
		rbBanquete.addActionListener(e-> {
			this.getContentPane().remove(auxiliar);
			this.getContentPane().remove(botones);
			if(rbBanquete.isSelected()) {
				cambiaAuxiliar("nº Comensales:", "Tipo de Mesa:", "Cuadrada", "Redonda");
				this.getContentPane().add(auxiliar);
				this.getContentPane().add(botones);
			}
			this.repaint();
			this.revalidate();
		});
		rbCongreso = new JRadioButton("Congreso");
		rbCongreso.addActionListener(e->{
			this.getContentPane().remove(auxiliar);
			this.getContentPane().remove(botones);
			if(rbCongreso.isSelected()) {
				cambiaAuxiliar("nº Jornadas:", "Requiere Hab.", "SI", "NO");
				this.getContentPane().add(auxiliar);
				this.getContentPane().add(botones);
			}
			this.repaint();
			this.revalidate();
		});
		radios = new ButtonGroup();
		radios.add(rbBanquete);
		radios.add(rbCongreso);
		
		datosReserva.add(lblTipoEvento);
		datosReserva.add(rbBanquete);
		datosReserva.add(rbCongreso);
		
		lblFecha = new JLabel("Fecha Evento: ");
		lblFecha.setPreferredSize(new Dimension(120,20));
		fecha = new JTextField(15);
		datosReserva.add(lblFecha);
		datosReserva.add(fecha);
		
		lblCocina = new JLabel("Tipo cocina");
		cocina = new JComboBox<String>();
		cocina.setPreferredSize(new Dimension(100,20));
		cocina.addItem("Buffet");
		cocina.addItem("Catering");
		cocina.addItem("Carta");
		
		datosReserva.add(lblCocina);
		datosReserva.add(cocina);
		
		lblnumPersonas =new  JLabel("Num. Personas");
		numPersonas = new JTextField(3);
		datosReserva.add(lblnumPersonas);
		datosReserva.add(numPersonas);
		
		datosReserva.setBorder(new TitledBorder("Datos reserva"));
		
		this.getContentPane().add(datosReserva);
		
		
		//panel auxiliar
		auxiliar = new JPanel();
		auxiliar.setLayout(new FlowLayout(FlowLayout.LEFT));
		auxiliar.setPreferredSize(new Dimension(350,80));
		auxiliar.setBorder(new TitledBorder(""));
		
		lblAux1 = new JLabel();
		lblAux1.setPreferredSize(new Dimension(120,20));
		auxiliar.add(lblAux1);
		
		inputAux = new JTextField(10);
		auxiliar.add(inputAux);

		lblAux2 = new JLabel();
		lblAux2.setPreferredSize(new Dimension(120,20));
		auxiliar.add(lblAux2);
		
		rbAux1 = new JRadioButton();
		rbAux2 = new JRadioButton();
		auxRadios = new ButtonGroup();
		auxRadios.add(rbAux1);
		auxRadios.add(rbAux2);
		
		auxiliar.add(rbAux1);
		auxiliar.add(rbAux2);
		
		
		//this.add(auxiliar);
		
		
		//panel botones
		botones = new JPanel();
		botones.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		reservar = new JButton("Reservar");
		reservar.setPreferredSize(new Dimension(100, 30));
		limpiar = new JButton("Limpiar");
		limpiar.setPreferredSize(new Dimension(100, 30));
		cerrar = new JButton("Cerrar");
		cerrar.setPreferredSize(new Dimension(100, 30));
		reservar.addActionListener(new Escucha(this));
		limpiar.addActionListener(new Escucha(this));
		cerrar.addActionListener(new Escucha(this));
		
		botones.add(reservar);
		botones.add(limpiar);
		botones.add(cerrar);


		
	}
	
	private void cambiaAuxiliar(String lblOp1, String lblOp2, String rbOp1, String rbOp2) {
		lblAux1.setText(lblOp1);
		lblAux2.setText(lblOp2);
		rbAux1.setText(rbOp1);
		rbAux2.setText(rbOp2);
		this.setSize(400,380);
	}
	
	public boolean validaVacios() {
		if(nombre.getText().isBlank() || telefono.getText().isBlank() || radios.getSelection() == null || fecha.getText().isBlank() || cocina.getSelectedItem() == null 
				|| numPersonas.getText().isBlank() || inputAux.getText().isEmpty() || auxRadios.getSelection() == null) {
			JOptionPane.showMessageDialog(this, "No puede dejar campos vacios!");
			return false;
		}else
			return true;
	}

	public boolean validaFecha() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			LocalDate hoy = LocalDate.now();
			LocalDate dateFormateada = LocalDate.parse(fecha.getText(), format); 
			if(dateFormateada.isBefore(hoy)) {
				JOptionPane.showMessageDialog(this, "Introduce una fecha que sea posterior al dia de hoy!!");

				return false;
			}
		}catch (DateTimeParseException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Introduce una fecha con formato correcto dd/mm/aaaa!");
			return false;
		}
		return true;
	}
	
	
	
	public JLabel getLblNom() {
		return lblNom;
	}

	public void setLblNom(JLabel lblNom) {
		this.lblNom = lblNom;
	}

	public JLabel getLblTel() {
		return lblTel;
	}

	public void setLblTel(JLabel lblTel) {
		this.lblTel = lblTel;
	}

	public JLabel getLblTipoEvento() {
		return lblTipoEvento;
	}

	public void setLblTipoEvento(JLabel lblTipoEvento) {
		this.lblTipoEvento = lblTipoEvento;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(JLabel lblFecha) {
		this.lblFecha = lblFecha;
	}

	public JLabel getLblCocina() {
		return lblCocina;
	}

	public void setLblCocina(JLabel lblCocina) {
		this.lblCocina = lblCocina;
	}

	public JLabel getLblnumPersonas() {
		return lblnumPersonas;
	}

	public void setLblnumPersonas(JLabel lblnumPersonas) {
		this.lblnumPersonas = lblnumPersonas;
	}

	public JLabel getLblAux1() {
		return lblAux1;
	}

	public void setLblAux1(JLabel lblAux1) {
		this.lblAux1 = lblAux1;
	}

	public JLabel getLblAux2() {
		return lblAux2;
	}

	public void setLblAux2(JLabel lblAux2) {
		this.lblAux2 = lblAux2;
	}

	public JTextField getNombre() {
		return nombre;
	}

	public void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}

	public JTextField getTelefono() {
		return telefono;
	}

	public void setTelefono(JTextField telefono) {
		this.telefono = telefono;
	}

	public JTextField getFecha() {
		return fecha;
	}

	public void setFecha(JTextField fecha) {
		this.fecha = fecha;
	}

	public JTextField getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(JTextField numPersonas) {
		this.numPersonas = numPersonas;
	}

	public JTextField getInputAux() {
		return inputAux;
	}

	public void setInputAux(JTextField inputAux) {
		this.inputAux = inputAux;
	}

	public JRadioButton getRbBanquete() {
		return rbBanquete;
	}

	public void setRbBanquete(JRadioButton rbBanquete) {
		this.rbBanquete = rbBanquete;
	}

	public JRadioButton getRbCongreso() {
		return rbCongreso;
	}

	public void setRbCongreso(JRadioButton rbCongreso) {
		this.rbCongreso = rbCongreso;
	}

	public JRadioButton getRbAux1() {
		return rbAux1;
	}

	public void setRbAux1(JRadioButton rbAux1) {
		this.rbAux1 = rbAux1;
	}

	public JRadioButton getRbAux2() {
		return rbAux2;
	}

	public void setRbAux2(JRadioButton rbAux2) {
		this.rbAux2 = rbAux2;
	}

	public ButtonGroup getRadios() {
		return radios;
	}

	public void setRadios(ButtonGroup radios) {
		this.radios = radios;
	}

	public ButtonGroup getAuxRadios() {
		return auxRadios;
	}

	public void setAuxRadios(ButtonGroup auxRadios) {
		this.auxRadios = auxRadios;
	}

	public JComboBox<String> getCocina() {
		return cocina;
	}

	public void setCocina(JComboBox<String> cocina) {
		this.cocina = cocina;
	}

	public JPanel getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(JPanel auxiliar) {
		this.auxiliar = auxiliar;
	}

	public JPanel getBotones() {
		return botones;
	}

	public void setBotones(JPanel botones) {
		this.botones = botones;
	}

	public JButton getReservar() {
		return reservar;
	}

	public void setReservar(JButton reservar) {
		this.reservar = reservar;
	}

	public JButton getLimpiar() {
		return limpiar;
	}

	public void setLimpiar(JButton limpiar) {
		this.limpiar = limpiar;
	}

	public JButton getCerrar() {
		return cerrar;
	}

	public void setCerrar(JButton cerrar) {
		this.cerrar = cerrar;
	}
	
	
	
	
	
}
