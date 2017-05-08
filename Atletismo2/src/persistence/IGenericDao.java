package persistence;

import java.sql.Connection;

public interface IGenericDao {
	
	public Connection getConnection() throws ClassNotFoundException;

}
