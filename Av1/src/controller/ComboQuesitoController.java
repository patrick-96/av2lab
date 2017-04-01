package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Quesito;

public class ComboQuesitoController implements ActionListener {
	JTextField textFieldNota;
	JLabel lblEscola,lblJurado,lblQuesito,lblNota;
	JComboBox<Quesito> comboBox_q;
	public ComboQuesitoController(JTextField textFieldNota,
			JLabel lblEscola,JLabel lblJurado, JLabel lblQuesito, JLabel lblNota,
			JComboBox<Quesito> comboBox_q){
		this.textFieldNota=textFieldNota;
		this.lblEscola=lblEscola;
		this.lblJurado=lblJurado;
		this.lblQuesito=lblQuesito;
		this.lblNota=lblNota;
		this.comboBox_q=comboBox_q;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("oi");		
		IQuesitoController qController=new QuesitoController(comboBox_q);
		qController.listaQuesito();
		
		
	}

}
