package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Criado pelo Prof. M.Sc. Leandro Colevati dos Santos
 */
public class GenericDao implements IGenericDao {

	private static Connection c;

	public Connection getConnection() {

		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=atletismo;namedPipe=true",
					"Patrick", "pa10pa10");
			System.out.println("Conexao ok");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public void fechaConexao() {
		try {
			if (c != null)
				c.close();
			c = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
