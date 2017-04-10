package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import model.Escola;
import model.Quesito;
import model.QuesitoConsulta;

public class QuesitoConsultaDao {
	private Connection c;
	private JComboBox comboBox_q;
	
	public QuesitoConsultaDao(JComboBox comboBox_q) throws ClassNotFoundException, SQLException{
		IGenericDao gDao=new GenericDao();
		c = gDao.getConnection();
		this.comboBox_q = comboBox_q;
	}
	
	
	
	public List<QuesitoConsulta> consultaQuesitoQuesito()throws SQLException{
		List<QuesitoConsulta> listaQuesitoConsulta=new ArrayList<QuesitoConsulta>();
		try {
			String sql = "exec pc_verificaQ ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, ((String) comboBox_q.getSelectedItem()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			QuesitoConsulta qc = new QuesitoConsulta();
			qc.setNome_e(rs.getString("nome_e"));
			qc.setNome_q(rs.getString("nome_q"));
			qc.setNota1(rs.getDouble("nota1"));
			qc.setNota2(rs.getDouble("nota2"));
			qc.setNota3(rs.getDouble("nota3"));
			qc.setNota4(rs.getDouble("nota4"));
			qc.setNota5(rs.getDouble("nota5"));
			qc.setMaior(rs.getDouble("maior"));
			qc.setMenor(rs.getDouble("menor"));
			qc.setTotal(rs.getDouble("total"));
			listaQuesitoConsulta.add(qc);
			}
			ps.execute();
			ps.close();
			} catch (SQLException ex) {

			}
			return listaQuesitoConsulta;
			}

}
