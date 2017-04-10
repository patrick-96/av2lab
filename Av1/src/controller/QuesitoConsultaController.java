package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Quesito;
import model.QuesitoConsulta;
import persistence.QuesitoConsultaDao;

public class QuesitoConsultaController implements IQuesitoConsultaController{
	private JTable table_Quesito;
	private JComboBox<Quesito> comboBox_q;

	public QuesitoConsultaController(JTable table_Quesito,JComboBox<Quesito> comboBox_q){
		this.table_Quesito=table_Quesito;
		this.comboBox_q=comboBox_q;
	}
	
	
	@Override
	public void tabelaVerQuesito() {
		if(table_Quesito!=null){
			DefaultTableModel modelo= (DefaultTableModel) table_Quesito.getModel();
	if(modelo.getRowCount()>0){
		modelo.setRowCount(0);
	}

	try {
		QuesitoConsultaDao qcDao = new QuesitoConsultaDao(comboBox_q);
		List<QuesitoConsulta> listaQuesitoConsulta=qcDao.consultaQuesitoQuesito();
	    for(QuesitoConsulta qcc :listaQuesitoConsulta){
	    	Object[] linha=new Object[10];
	    			linha[0]=qcc.getNome_e();
	    			linha[1]=qcc.getNome_q();
	    			linha[2]=qcc.getNota1();
	    			linha[3]=qcc.getNota2();
	    			linha[4]=qcc.getNota3();
	    			linha[5]=qcc.getNota4();
	    			linha[6]=qcc.getNota5();
	    			linha[7]=qcc.getMaior();
	    			linha[8]=qcc.getMenor();
	    			linha[9]=qcc.getTotal();
	    			
	    	modelo.addRow(linha);
	    }
	} catch (ClassNotFoundException | SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO",
				JOptionPane.ERROR_MESSAGE);
	}

		}
		
	}
}

