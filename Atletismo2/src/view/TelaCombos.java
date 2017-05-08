package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BateriaController;
import controller.CompeticaoController;
import controller.ProvasController;
import controller.VerCompeticaoResultadoController;
import model.Bateria;
import model.Competicao;
import model.Provas;

public class TelaCombos extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame TelaCombos;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCombos frame = new TelaCombos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCombos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Provas> comboBoxProva = new JComboBox<Provas>();
		comboBoxProva.setBounds(164, 21, 194, 20);
		contentPane.add(comboBoxProva);
		
		JComboBox<Bateria> comboBoxBateria = new JComboBox<Bateria>();
		comboBoxBateria.setBounds(164, 162, 194, 20);
		contentPane.add(comboBoxBateria);
		
		JComboBox<Competicao> comboBoxFase = new JComboBox<Competicao>();
		comboBoxFase.setBounds(164, 97, 194, 20);
		contentPane.add(comboBoxFase);
		
		JButton btnExibir = new JButton("Exibir");
		btnExibir.setBounds(170, 227, 89, 23);
		contentPane.add(btnExibir);
		
		JLabel lblProva = new JLabel("Prova:");
		lblProva.setBounds(41, 24, 46, 14);
		contentPane.add(lblProva);
		
		JLabel lblFase = new JLabel("Fase:");
		lblFase.setBounds(41, 100, 46, 14);
		contentPane.add(lblFase);
		
		JLabel lblBateria = new JLabel("Bateria:");
		lblBateria.setBounds(41, 165, 46, 14);
		contentPane.add(lblBateria);
	
		CompeticaoController cController = new CompeticaoController(comboBoxFase, comboBoxBateria);
		cController.listaFases();
		//cController.listaBaterias();
		ProvasController pController = new ProvasController(comboBoxProva);
		pController.listaProvas();
		BateriaController bController=new BateriaController(comboBoxBateria);
		bController.listaBaterias();
		VerCompeticaoResultadoController vrcc= new VerCompeticaoResultadoController(TelaCombos, btnExibir, comboBoxProva, comboBoxFase, comboBoxBateria); 
		btnExibir.addActionListener(vrcc);
		
		
	
	}
}
