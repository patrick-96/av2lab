package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bateria;
import model.Competicao;

public class BateriaDao {
	private Connection c;
	public BateriaDao() throws ClassNotFoundException, SQLException{
		IGenericDao gDao=new GenericDao();
		c = gDao.getConnection();
	}
	
	public List<Bateria> consultaBateria() throws SQLException {
		List<Bateria> listaBateria=new ArrayList<Bateria>();
		String sql="select Distinct(competicao.bateria) from competicao order by competicao.bateria asc";
		PreparedStatement ps=c.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			Bateria ba= new Bateria();
			ba.setBateria(rs.getString("bateria"));
			listaBateria.add(ba);
			
		}
		rs.close();
		ps.close();
		return listaBateria;
	}

}
