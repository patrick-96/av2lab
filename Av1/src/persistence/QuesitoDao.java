package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Quesito;

public  class QuesitoDao implements IQuesitoDao{
private Connection c;
public QuesitoDao() throws ClassNotFoundException, SQLException{
	IGenericDao gDao=new GenericDao();
	c = gDao.getConnection();
}
	
	@Override
	public List<Quesito> consultaQuesitos() throws SQLException {
		List<Quesito> listaQuesito=new ArrayList<Quesito>();
		String sql="Select * from Quesito";
		PreparedStatement ps=c.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
			while(rs.next()){
			Quesito que= new Quesito();
			que.setId_q(rs.getInt("id_q"));
			que.setNome_q(rs.getString("nome_q"));
			listaQuesito.add(que);
			
		}
		rs.close();
		ps.close();
		c.close();
		return listaQuesito;
	}
	

}
