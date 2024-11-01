import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	private Connection con;
	
	public Conexion() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/ BibliotecaSwing ";
			con = DriverManager.getConnection(url, "root", "");
	}
	
	public ResultSet consulta(String sql) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public int actualizar(String sql) throws SQLException {
		int check = -1 ;
		Statement stmt = con.createStatement();
		check = stmt.executeUpdate(sql);
		return check;
	}
	
	public void cerrar() throws SQLException {
		con.close();
	}
	
	
}
