import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ListenerGestion implements ActionListener {

	private VentanaGestion vg;
	private VentanaPrincipal vp;
	
	public ListenerGestion(VentanaGestion vg, VentanaPrincipal vp) {
		this.vg = vg;
		this.vp = vp;

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		switch(e.getActionCommand()) {
		case "Agregar Libro":
			vp.setSize(550, 550);
			vg.add(vg.getDetallesLibro());
			vg.getBotonesLibro()[0].setText("Guardar");
			break;
			
			
			
		case "Cancelar":
			vg.vaciarDetalles();
			vp.setSize(550, 350);
			vg.remove(vg.getDetallesLibro());
			break;
			
			
			
		case "Guardar":
			Libro l = new Libro();
			try {
				
				l.insertLibro(vg);
				vg.getListModel().addElement(vg.getTitulo().getText());					
				
				vg.getBotonesAccion()[2].setEnabled(false);
				
				vg.getTituloLibros(vg.getListModel());
				vg.vaciarDetalles();
				vp.setSize(550, 350);
				vg.remove(vg.getDetallesLibro());
				vg.repaint();
				vg.revalidate();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
			
		case "Eliminar libro":
			 	Libro l1 = new Libro();
			try {
				l1.eliminarLibro(vg, vg.getListaLibros().getSelectedValue());
				vg.getListModel().removeElement(vg.getListaLibros().getSelectedValue());
				vg.getBotonesAccion()[2].setEnabled(false);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break; 
			
			
		case "Editar libro":
			Libro l2 = new Libro();
			try {
				l2.getLibro(vg, vg.getListaLibros().getSelectedValue());
				
				l2.setInputs(vg);
				vp.setSize(550, 550);
				vg.add(vg.getDetallesLibro());
				vg.getBotonesLibro()[0].setText("Aceptar");
				
				
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		
			
		case "Aceptar":
			Libro l3 = new Libro();
			try {
				l3.editLibro(vg);
				vg.getTituloLibros(vg.getListModel());
				vg.getBotonesAccion()[2].setEnabled(false);
				vg.vaciarDetalles();
				vp.setSize(550, 350);
				vg.remove(vg.getDetallesLibro());
				vg.repaint();
				vg.revalidate();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
	}
	
	

}
