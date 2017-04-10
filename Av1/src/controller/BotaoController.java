package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Escola;
import model.Jurado;
import model.Nota;
import model.Quesito;
import persistence.NotaDao;

public class BotaoController implements ActionListener {
	static int c=0;
	static int q=0;
	JTextField textFieldNota;
	JLabel lblEscola,lblJurado,lblQuesito,lblNota;
	JComboBox<Quesito> comboBox_q;
	JComboBox<Escola> comboBox_e;
	JComboBox<Jurado> comboBox_j;
	JButton btnVerQuesito,btnInserir,btnVerTotal;
	public BotaoController(JTextField textFieldNota,
			JLabel lblEscola,JLabel lblJurado, JLabel lblQuesito, JLabel lblNota,
			JComboBox<Quesito> comboBox_q,JComboBox<Jurado> comboBox_j,JComboBox<Escola> comboBox_e,JButton btnVerQuesito,JButton btnVerTotal,JButton btnInserir){
		this.textFieldNota=textFieldNota;
		this.lblEscola=lblEscola;
		this.lblJurado=lblJurado;
		this.lblQuesito=lblQuesito;
		this.lblNota=lblNota;
		this.comboBox_q=comboBox_q;
		this.comboBox_e=comboBox_e;
		this.comboBox_j=comboBox_j;
		this.btnInserir=btnInserir;
		this.btnVerQuesito=btnVerQuesito;
		this.btnVerTotal=btnVerTotal;
		
	}
	
	private void limpaCampos(){
		textFieldNota.setText("");
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Nota not=new Nota();
		Escola es=new Escola();
		Jurado ju=new Jurado();
		Quesito que=new Quesito();
		
		
		not.setNota(Double.parseDouble(textFieldNota.getText()));
		es.setNome_e((String) comboBox_e.getSelectedItem().toString());
		ju.setNome_j((String) comboBox_j.getSelectedItem().toString());
		que.setNome_q((String) comboBox_q.getSelectedItem().toString());
		CadastrarNota(not,es,ju,que);
	}

	private void CadastrarNota(Nota not,Escola es,Jurado ju,Quesito que) {
		
		try {
			NotaDao notaDao=new NotaDao();
			notaDao.insereNota(not,es,ju,que);
			JOptionPane.showMessageDialog(null, "Nota Inserida com Sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
		}
		trocarJ();
		trocarQ();
		trocarE();
		limpaCampos();
		// TODO Auto-generated method stub
		
	}

	private void trocarE() {
					
		
		if(q%9==0 && q!=0){
int n= comboBox_e.getSelectedIndex()+1;
			
			Object obj=comboBox_e.getItemAt(n);
			comboBox_e.setSelectedItem(obj);
			q++;
		}
		
	}

	private void trocarQ() {
		if(c<=45){
		if(c%5==0){
			int n= comboBox_q.getSelectedIndex()+1;
			
			Object obj=comboBox_q.getItemAt(n);
			comboBox_q.setSelectedItem(obj);
			q++;
		}
		}
		else{
			Object obj=comboBox_q.getItemAt(0);//c
			comboBox_j.setSelectedItem(obj);
			q=0;	
		}
		
		// TODO Auto-generated method stub
		
	}

	private void trocarJ() {
		
		if(comboBox_j.getSelectedIndex()<comboBox_j.getItemCount()-1){
		int n= comboBox_j.getSelectedIndex()+1;
		
		Object obj=comboBox_j.getItemAt(n);
		comboBox_j.setSelectedItem(obj);
		
		++c;
		System.out.println(c);
		}
		else{
			//para trocar antes dele zerar o c
			trocarQ();
			Object obj=comboBox_j.getItemAt(0);//c
			comboBox_j.setSelectedItem(obj);
			c++;
		}
		
		
		// TODO Auto-generated method stub
		
	}

}
