package teste;

import java.sql.Connection;
import java.sql.SQLException;

import persistence.GenericDao;

public class TestaConexao {
	public static void main(String[]args) throws SQLException{
		 Connection conexao=new GenericDao().getConnection();
		 System.out.println("OLA Marilene");
		 conexao.close();
		
		
	}

}
