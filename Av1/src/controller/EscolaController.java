package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Escola;
import persistence.EscolaDao;

public class EscolaController implements IEscolaController {
	private JComboBox<Escola> comboBox_e;
	private JTable tblTotal;

	public EscolaController(JComboBox<Escola> comboBox_e) {
		this.comboBox_e = comboBox_e;
	}

	public EscolaController(JTable tblTotal) {
		this.tblTotal = tblTotal;
	}

	public void listaEscola() {
		try {
			EscolaDao eDao = new EscolaDao();
			List<Escola> listaEscola = eDao.consultaEscolas();
			if (comboBox_e.getItemCount() > 0) {
				comboBox_e.removeAllItems();
			}
			if (listaEscola != null) {
				for (Escola e : listaEscola) {
					comboBox_e.addItem(e);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	@Override
	public void tabelaEscolaTotal() {
		if(tblTotal!=null){
			DefaultTableModel modelo= (DefaultTableModel) tblTotal.getModel();
	if(modelo.getRowCount()>0){
		modelo.setRowCount(0);
	}
	
	try {
		EscolaDao eDao = new EscolaDao();
		List<Escola> listaEscola=eDao.consultaTotal();
        for(Escola es :listaEscola){
        	Object[] linha=new Object[2];
        			linha[0]=es.getNome_e();
        			linha[1]=es.getTotal_pontos();
        	modelo.addRow(linha);
        }
	} catch (ClassNotFoundException | SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO",
				JOptionPane.ERROR_MESSAGE);
	}
	
		}
		
	}

}
