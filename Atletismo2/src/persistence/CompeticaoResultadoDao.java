package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Bateria;
import model.Competicao;
import model.CompeticaoResultado;
import model.Provas;

public class CompeticaoResultadoDao {
	private Connection c;
	private JComboBox<Provas> comboBoxProva;
	private JComboBox<Competicao> comboBoxFase;
	private JComboBox<Bateria> comboBoxBateria;
	public CompeticaoResultadoDao(JComboBox<Provas> comboBoxProva,JComboBox<Competicao> comboBoxFase,JComboBox<Bateria> comboBoxBateria) throws ClassNotFoundException, SQLException{
		IGenericDao gDao=new GenericDao();
		c = gDao.getConnection();
		this.comboBoxProva=comboBoxProva;
		this.comboBoxFase=comboBoxFase;
		this.comboBoxBateria=comboBoxBateria;
	}
	
	
	public List<CompeticaoResultado> consultaCompeticao() throws SQLException {
		List<CompeticaoResultado> listaCompeticaoResultado=new ArrayList<CompeticaoResultado>();
		try {
			
    	String sql="select * from fn_tabela(?,?,?) ORDER BY resultado asc ";
	PreparedStatement ps = c.prepareStatement(sql);
	
	ps.setString(1,comboBoxProva.getSelectedItem().toString());
	ps.setString(2, comboBoxFase.getSelectedItem().toString());
	ps.setString(3, comboBoxBateria.getSelectedItem().toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
		CompeticaoResultado cpr= new CompeticaoResultado();
		cpr.setNomeProva(rs.getString("nomeProva"));
		cpr.setNomeAtleta(rs.getString("nomeAtleta"));
		cpr.setFase(rs.getString("fase"));
		cpr.setBateria(rs.getString("bateria"));
		cpr.setResultado(rs.getString("resultado"));
		listaCompeticaoResultado.add(cpr);
		}
	
	rs.close();
	ps.close();
	c.close();
		
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Função não localizada /n" + e.getMessage(),"Atenção",1); 	
		}
		
		
    
		return listaCompeticaoResultado;
	}
	
	

}
