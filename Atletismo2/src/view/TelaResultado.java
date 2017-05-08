package view;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CompeticaoResultadoController;
import model.Bateria;
import model.Competicao;
import model.Provas;

public class TelaResultado extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static JComboBox<Provas> comboBoxProva;
	static JComboBox<Bateria> comboBoxBateria;
	static JComboBox<Competicao> comboBoxFase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaResultado frame = new TelaResultado(comboBoxProva, comboBoxFase, comboBoxBateria);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param comboBoxBateria 
	 * @param comboBoxFase 
	 * @param comboBoxProva 
	 */
	public TelaResultado(JComboBox<Provas> comboBoxProva, JComboBox<Competicao> comboBoxFase, JComboBox<Bateria> comboBoxBateria) {
		this.comboBoxProva=comboBoxProva;
		this.comboBoxFase=comboBoxFase;
		this.comboBoxBateria=comboBoxBateria;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 472, 263);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel modelo=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"nomeProva", "nomeAtleta", "fase", "bateria", "resultado"
				}
			);
		table.setModel(modelo);
		CompeticaoResultadoController cpr=new CompeticaoResultadoController(table, comboBoxProva, comboBoxFase, comboBoxBateria);
		cpr.tabelaVerResultado();
		//CompeticaoController cController=new CompeticaoController(comboBoxProva,comboBoxFase, comboBoxBateria)
		//cController.
		
	}
	

}
