package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Provas;

public class ProvasDao {
	private Connection c;
	public ProvasDao() throws ClassNotFoundException, SQLException{
		IGenericDao gDao=new GenericDao();
		c = gDao.getConnection();
	}
	public List<Provas> consultaProvas() throws SQLException {
		List<Provas> listaProvas=new ArrayList<Provas>();
		String sql="select provas.NomeProva from provas";
		PreparedStatement ps=c.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			Provas pv= new Provas();
			pv.setNomeProva(rs.getString("nomeProva"));
			listaProvas.add(pv);
			
		}
		rs.close();
		ps.close();
		return listaProvas;
	}

}
