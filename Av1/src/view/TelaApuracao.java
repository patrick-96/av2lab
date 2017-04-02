package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ComboQuesitoController;
import controller.QuesitoController;
import model.Escola;
import model.Jurado;
import model.Quesito;

public class TelaApuracao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaApuracao frame = new TelaApuracao();
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
	public TelaApuracao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<Escola> comboBox_e = new JComboBox<Escola>();
		comboBox_e.setBounds(223, 40, 203, 20);
		contentPane.add(comboBox_e);

		JComboBox<Jurado> comboBox_j = new JComboBox<Jurado>();
		comboBox_j.setBounds(223, 87, 180, 20);
		contentPane.add(comboBox_j);

		JComboBox<Quesito> comboBox_q = new JComboBox<Quesito>();
		comboBox_q.setBounds(223, 132, 180, 20);
		contentPane.add(comboBox_q);

		textFieldNota = new JTextField();
		textFieldNota.setBounds(123, 253, 86, 20);
		contentPane.add(textFieldNota);
		textFieldNota.setColumns(10);

		JLabel lblNota = new JLabel("Nota:");
		lblNota.setBounds(48, 256, 38, 14);
		contentPane.add(lblNota);

		JLabel lblQuesito = new JLabel("Quesito:");
		lblQuesito.setBounds(104, 135, 56, 14);
		contentPane.add(lblQuesito);

		JLabel lblJurado = new JLabel("Jurado:");
		lblJurado.setBounds(104, 90, 56, 14);
		contentPane.add(lblJurado);

		JLabel lblEscola = new JLabel("Escola:");
		lblEscola.setBounds(104, 43, 56, 14);
		contentPane.add(lblEscola);

		JButton btnVerQuesito = new JButton("Ver Quesito");
		btnVerQuesito.setBounds(104, 312, 120, 23);
		contentPane.add(btnVerQuesito);

		JButton btnVerTotal = new JButton("Ver Total");
		btnVerTotal.setBounds(328, 312, 98, 23);
		contentPane.add(btnVerTotal);

		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(232, 252, 80, 23);
		contentPane.add(btnInserir);

		QuesitoController qController = new QuesitoController(comboBox_q);
		qController.listaQuesito();

	}
}
