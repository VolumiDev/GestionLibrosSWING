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
		String url = "jdbc:mysql://localhost/ Encuesta ";
		con = DriverManager.getConnection(url, "root", "");

	}
	
	
	public int actualizar(String sql) throws SQLException {
		Statement stmt = con.createStatement();
		return stmt.executeUpdate(sql);
	}
	
	public ResultSet consulta(String sql) throws SQLException {
		Statement stmt = con.createStatement();
		return stmt.executeQuery(sql);
	}
	
	public void cerrar() throws SQLException {
		con.close();
	}
}
