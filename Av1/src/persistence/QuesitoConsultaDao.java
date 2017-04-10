package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Quesito;
import model.QuesitoConsulta;

public class QuesitoConsultaDao {
	private Connection c;
	private JComboBox<Quesito> comboBox_q;
	
	public QuesitoConsultaDao(JComboBox<Quesito> comboBox_q) throws ClassNotFoundException, SQLException{
		IGenericDao gDao=new GenericDao();
		c = gDao.getConnection();
		this.comboBox_q = comboBox_q;
	}
	
	
	
	public List<QuesitoConsulta> consultaQuesitoQuesito()throws SQLException{
		List<QuesitoConsulta> listaQuesitoConsulta=new ArrayList<QuesitoConsulta>();
			//try {
			
					String sql = "exec pc_verificaQ ?";
					
			CallableStatement cs = c.prepareCall(sql);
			cs.setString(1,(String) comboBox_q.getSelectedItem().toString());
		    
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
			QuesitoConsulta qc = new QuesitoConsulta();
			qc.setNome_e(rs.getString("Escola"));
			qc.setNome_q(rs.getString("Quesito"));
			qc.setNota1(rs.getDouble("nota1"));
			qc.setNota2(rs.getDouble("nota2"));
			qc.setNota3(rs.getDouble("nota3"));
			qc.setNota4(rs.getDouble("nota4"));
			qc.setNota5(rs.getDouble("nota5"));
			qc.setMaior(rs.getDouble("Maior_Descartada"));
			qc.setMenor(rs.getDouble("Menor_Descarta"));
			qc.setTotal(rs.getDouble("Total"));
			listaQuesitoConsulta.add(qc);
			}
			System.out.println("oi");
			cs.execute();
			rs.close();	
			cs.close();
			
			//} catch (SQLException ex) {
//JOptionPane.showMessageDialog(null, ex.getStackTrace(),"Erro", JOptionPane.ERROR_MESSAGE);
			//}
			return listaQuesitoConsulta;
			}

}
