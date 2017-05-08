package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Bateria;
import model.Competicao;
import model.CompeticaoResultado;
import model.Provas;
import persistence.CompeticaoResultadoDao;



public class CompeticaoResultadoController {
	private JTable table;
	private JComboBox<Provas> comboBoxProva;
	private JComboBox<Competicao> comboBoxFase;
	private JComboBox<Bateria> comboBoxBateria;

	public CompeticaoResultadoController(JTable table,JComboBox<Provas> comboBoxProva,JComboBox<Competicao> comboBoxFase,JComboBox<Bateria> comboBoxBateria){
		this.table=table;
		this.comboBoxProva=comboBoxProva;
		this.comboBoxFase=comboBoxFase;
		this.comboBoxBateria=comboBoxBateria;
	}
	
	
	public void tabelaVerResultado() {
		if(table!=null){
			DefaultTableModel modelo= (DefaultTableModel) table.getModel();
	if(modelo.getRowCount()>0){
		modelo.setRowCount(0);
	}

	try {
		CompeticaoResultadoDao crDao = new CompeticaoResultadoDao (comboBoxProva, comboBoxFase, comboBoxBateria);
		List<CompeticaoResultado> listaCompeticaoResultado=crDao.consultaCompeticao();
	    for(CompeticaoResultado cr :listaCompeticaoResultado){
	    	Object[] linha=new Object[5];
	    			linha[0]=cr.getNomeProva();
	    			linha[1]=cr.getNomeAtleta();
	    			linha[2]=cr.getFase();
	    			linha[3]=cr.getBateria();
	    			linha[4]=cr.getResultado();
	    				    			
	    	modelo.addRow(linha);
	    }
	} catch (ClassNotFoundException | SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO",
				JOptionPane.ERROR_MESSAGE);
	}

		}
		
	}


}
