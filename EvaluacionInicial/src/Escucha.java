import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Escucha implements ActionListener {
	
	private InternalEvaluation ie;
	
	
	public Escucha(InternalEvaluation ie) {
		this.ie = ie;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		switch(e.getActionCommand()) {
		case "Calificar":
			
			if(ie.estanVaciosTodosCampos() == true) {
				JOptionPane.showMessageDialog(ie, "Rellene todos los campos");
			}else {
				 if(ie.fechaCorrecta()) {
					 ie.mostrarPuntuacion();
					 JOptionPane.showMessageDialog(ie, "Cuando revise sus respuesta pulse Aceptar");
					 try {
						 if(ie.existNombre()) {
							 if(ie.tieneIntentos()) {
								 ie.actualizarDatos();
							 }else {
								 JOptionPane.showMessageDialog(ie, "No tiene mas intentos");
							 }
						 }else {
							 ie.insertDatos();
						 }
					 }catch(ClassNotFoundException | SQLException e1) {
						 e1.printStackTrace();
					 }					 
				 }
				 ie.resetearPreguntas();
			}
			
			break;
		case "Buscar":
			try {
				ie.buscarNombre();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
	}
	
	

}
