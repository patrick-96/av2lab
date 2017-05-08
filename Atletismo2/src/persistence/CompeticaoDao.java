package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Competicao;
import model.Provas;

public class CompeticaoDao {
	private Connection c;
	public CompeticaoDao() throws ClassNotFoundException, SQLException{
		IGenericDao gDao=new GenericDao();
		c = gDao.getConnection();
	}
	public List<Competicao> consultaFases() throws SQLException {
		List<Competicao> listaCompeticao=new ArrayList<Competicao>();
		String sql="select Distinct(competicao.fase) from competicao ORDER BY competicao.fase desc";
		PreparedStatement ps=c.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			Competicao cc= new Competicao();
			cc.setFase(rs.getString("fase"));
			listaCompeticao.add(cc);
			
		}
		rs.close();
		ps.close();
		return listaCompeticao;
	}
	
	
	


}
