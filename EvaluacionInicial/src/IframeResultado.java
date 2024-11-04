import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

public class IframeResultado extends JInternalFrame{

	private JTextField[] cards;

	public IframeResultado() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
		int n = contaSuspensos();
		this.setLayout(new GridLayout(n,1));
		cards = new JTextField[n];
		rellenaCards();
		this.setVisible(true);
		
	}
	
	private int contaSuspensos() throws ClassNotFoundException, SQLException {
		String sql = "Select Count(*) from resultados where Puntos < 2";
		int n = 0;
		Conexion con = new Conexion();
		ResultSet rs = con.consulta(sql);
		while(rs.next()) {
			n = rs.getInt(1);
		}
		con.cerrar();
		return n;
	}
	
	private void rellenaCards() throws ClassNotFoundException, SQLException {
		String sql = "Select Nombre from resultados where Puntos < 2";
		Conexion con = new Conexion();
		int i = 0;
		ResultSet rs = con.consulta(sql);
		while(rs.next()) {
			cards[i] = new JTextField();
			cards[i].setText(rs.getString(1));
			this.add(cards[i]);
		}
		con.cerrar();
	}
	
	
}
