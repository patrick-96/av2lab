package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Escola;


public class EscolaDao implements IEscolaDao{
	private Connection c;
	public EscolaDao() throws ClassNotFoundException, SQLException{
		IGenericDao gDao=new GenericDao();
		c = gDao.getConnection();
	}
	
	@Override
	public List<Escola> consultaEscolas() throws SQLException {
		List<Escola> listaEscola=new ArrayList<Escola>();
		String sql="Select id_e,nome_e,total_pontos from Escola";
		PreparedStatement ps=c.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()){
			Escola es= new Escola();
			es.setNome_e(rs.getString("nome_e"));
			listaEscola.add(es);
			
		}
		rs.close();
		ps.close();
		return listaEscola;
	}
	
	public List<Escola> consultaTotal()throws SQLException{
	List<Escola> listaEscola=new ArrayList<Escola>();
	String sql="select nome_e,dbo.fn_total(id_e) as total_pontos from Escola order by total_pontos";
	PreparedStatement ps=c.prepareStatement(sql);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		Escola es=new Escola();
		es.setNome_e(rs.getString("nome_e"));
		es.setTotal_pontos(rs.getDouble("total_pontos"));
		listaEscola.add(es);
	}
	rs.close();
	ps.close();
	return listaEscola;
	
	}
	
	

}
