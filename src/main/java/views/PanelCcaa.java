package views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import entities.Ccaa;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCcaa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCode;
	private JTextField jtfLabel;
	private Ccaa ccaa;
	JComboBox<Ccaa> jcbCcaa;

	/**
	 * Create the panel.
	 */
	public PanelCcaa(Ccaa ccaa, JComboBox<Ccaa> jcbCcaa) {

		this.ccaa = ccaa;
		this.jcbCcaa = jcbCcaa;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblGe = new JLabel("Gestión de CCAA");
		GridBagConstraints gbc_lblGe = new GridBagConstraints();
		gbc_lblGe.insets = new Insets(0, 0, 5, 5);
		gbc_lblGe.gridx = 3;
		gbc_lblGe.gridy = 0;
		add(lblGe, gbc_lblGe);

		JLabel lblNewLabel = new JLabel("Code:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		jtfCode = new JTextField();
		GridBagConstraints gbc_jtfCode = new GridBagConstraints();
		gbc_jtfCode.gridwidth = 5;
		gbc_jtfCode.insets = new Insets(0, 0, 5, 0);
		gbc_jtfCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCode.gridx = 2;
		gbc_jtfCode.gridy = 1;
		add(jtfCode, gbc_jtfCode);
		jtfCode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		jtfLabel = new JTextField();
		GridBagConstraints gbc_jtfLabel = new GridBagConstraints();
		gbc_jtfLabel.gridwidth = 5;
		gbc_jtfLabel.insets = new Insets(0, 0, 5, 0);
		gbc_jtfLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfLabel.gridx = 2;
		gbc_jtfLabel.gridy = 2;
		add(jtfLabel, gbc_jtfLabel);
		jtfLabel.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guardar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.gridx = 6;
		gbc_btnGuardar.gridy = 3;
		add(btnGuardar, gbc_btnGuardar);

		mostrarDatosCcaa();
	}

	private void mostrarDatosCcaa() {
		this.jtfCode.setText(this.ccaa.getCode());
		this.jtfLabel.setText(this.ccaa.getLabel());
	}

	private void guardar() throws Exception {
		
		Ccaa c = new Ccaa();
		
		c.setCode(this.jtfCode.getText());
		c.setLabel(this.jtfLabel.getText());
	}

}
