import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Escucha implements ActionListener {

	private Ventana v;
	
	
	public Escucha(Ventana v) {
		super();
		this.v = v;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "Cerrar":
			System.exit(0);
			break;
			
			
		case "Limpiar":
			limpiar();
			v.getContentPane().remove(v.getAuxiliar());
			v.setSize(400,250);
			break;
			
			
		case "Reservar":
			if(v.validaVacios() && v.validaFecha()) {
				try {
					escribirtxt();
					JOptionPane.showMessageDialog(v, "Reserva generada con exito!");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		}
	}
	
	
	private void limpiar() {
		v.getNombre().setText("");
		v.getTelefono().setText("");
		v.getRadios().clearSelection();
		v.getAuxRadios().clearSelection();
		v.getFecha().setText("");
		v.getCocina().setSelectedIndex(-1);
		v.getNumPersonas().setText("");
		v.getInputAux().setText("");
	}
	
	private void escribirtxt() throws IOException {
		File txt = new File("reserva.txt");
		
		FileWriter fw = new FileWriter(txt,true);
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println("Nombre: " + v.getNombre().getText());
		pw.println("Telefono: " + v.getTelefono().getText());
		if(v.getRbCongreso().isSelected()) {
			pw.println("Tipo de evento: " + v.getRbCongreso().getText());
		}else {
			pw.println("Tipo de evento: " + v.getRbBanquete().getText());
		}
		
		pw.println("Fecha de reserva: " + v.getFecha().getText());
		pw.println("Tipo de cocina: " + v.getCocina().getSelectedItem());
		pw.println("Numero de personas : " + v.getTelefono().getText());
		
		if(v.getLblAux1().getText().contains("comensales")) {
			pw.println("Numero de comensales: " + v.getInputAux().getText());
			if(v.getRbAux1().isSelected()) {
				pw.println("Tipo de mesa: " + v.getRbAux1().getText());				
			}else {
				pw.println("Tipo de mesa : " + v.getRbAux2().getText());
			}

		}else {
			pw.println("Numero de jornadas: " + v.getInputAux().getText());
			if(v.getRbAux1().isSelected()) {
				pw.println("Habitacion: " + v.getRbAux1().getText());				
			}else {
				pw.println("Habitacion: " + v.getRbAux2().getText());
			}
		}
				
		pw.close();
		fw.close();
	}

}
