package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Escola;
import model.Jurado;
import model.Nota;
import model.Quesito;

public class NotaDao implements INotaDao{
	private Connection c;
	public NotaDao() throws ClassNotFoundException, SQLException{
		IGenericDao gDao=new GenericDao();
		c = gDao.getConnection();
	}
	@Override
	public void insereNota(Nota not,Escola es,Jurado ju, Quesito que) throws SQLException {
		String sql ="exec sp_inserenota ?,?,?,?";
		PreparedStatement ps=c.prepareStatement(sql);
		//talvez de erro pq tirei o id
		
		ps.setDouble(1, not.getNota());
		ps.setString(2, es.getNome_e());
		ps.setString(3,ju.getNome_j());
		ps.setString(4, que.getNome_q());
		ps.execute();
		ps.close();
		
		
	}
	
	

	
	
	
	
}
