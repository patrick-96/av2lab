package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Jurado;

public class JuradoDao implements IJuradoDao{
	private Connection c;
	public JuradoDao() throws ClassNotFoundException, SQLException{
		IGenericDao gDao=new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public List<Jurado> consultaJurados() throws SQLException {
		List<Jurado> listaJurado=new ArrayList<Jurado>();
		String sql="Select id_j,nome_j from Jurado";
		PreparedStatement ps=c.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			Jurado ju= new Jurado();
			ju.setNome_j(rs.getString("nome_j"));
			listaJurado.add(ju);
			
		}
		rs.close();
		ps.close();
		return listaJurado;
	}
	

}

