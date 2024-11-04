import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame{

	//JmenuBar
	private JMenuBar barramenu;
	//JMenu
	private JMenu menu;
	//JMenuItem
	private JMenuItem evaluacionItem;
	private JMenuItem salirItem;
	private JMenuItem listaAprobados;
	
	
	public MainFrame() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		
		//menuBar
		barramenu = new JMenuBar();
		this.setJMenuBar(barramenu);
		
		//menu
		menu = new JMenu("MENU");
		barramenu.add(menu);
		
		//menu Item
		evaluacionItem = new JMenuItem("Evaluacion Inicial");
		evaluacionItem.addActionListener(e-> {
			this.getContentPane().removeAll();
			InternalEvaluation ie = new InternalEvaluation();
			this.getContentPane().add(ie);
			ie.setVisible(true);
		});
		
		
		salirItem = new JMenuItem("Salir");
		salirItem.addActionListener(e->{
			System.exit(0);
		});
		
		listaAprobados = new JMenuItem("Lista de Aprovados");
		listaAprobados.addActionListener(e->{
			try {
				this.getContentPane().removeAll();
				IframeResultado result = new IframeResultado();
				this.getContentPane().add(result);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		menu.add(evaluacionItem);
		menu.add(listaAprobados);
		menu.add(salirItem);
		
		
	}

	
}
