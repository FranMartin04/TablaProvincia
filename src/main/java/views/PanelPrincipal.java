package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class PanelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfid;
	private JTextField tfCode;
	private JTextField tfNombre;
	private JTextField tffirma;
	private JTextField tfParentCode;
	private DefaultTableModel dtm = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPrincipal frame = new PanelPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Mongodb inicializando parámetros.
		        int port_no = 27017;
		        String host_name = "localhost", db_name = "ComunidadesProvinciasPoblaciones", 
		        		db_coll_name = "provincias";
		 
		        // Mongodb creando la cadena de conexión.
		        String client_url = "mongodb://" + host_name + ":" + port_no + "/" + db_name;
		        MongoClientURI uri = new MongoClientURI(client_url);
		 
		        // Conectando y obteniendo un cliente.
		        MongoClient mongo_client = new MongoClient(uri);
		 
		        // Obteniendo un enlace a la base de datos.
		        MongoDatabase db = mongo_client.getDatabase(db_name);
		 
		        // Obteniendo la colección de la base de datos
		        MongoCollection<Document> coll = db.getCollection(db_coll_name);
			}
		});
	}

	public PanelPrincipal() {
		JFrame frame = new JFrame("Gestion de Contratos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(806, 535);

		// Crear los componentes que irán dentro del SplitPane
		JPanel panelSuperior = new JPanel(new BorderLayout());

		JPanel panelInferior = new JPanel();

		// Crear el JSplitPane y configurarlo
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelSuperior, panelInferior);

		// this.dtm = getDefaultTableModelNoEditable();
		JTable jTable = new JTable(dtm);

		// dtm = getDefaultTableModelNoEditable();
		jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = jTable.getSelectedRow();
					if (selectedRow != -1) { // Asegurarse de que se ha seleccionado una fila
						// Obtener los datos de la fila seleccionada
						String id = jTable.getValueAt(selectedRow, 0).toString();
						String descripcion = (String) jTable.getValueAt(selectedRow, 1);
						String saldo = jTable.getValueAt(selectedRow, 2).toString();
						String limite = jTable.getValueAt(selectedRow, 3).toString();
						String tipoContrato = (String) jTable.getValueAt(selectedRow, 4);
						String usuario = (String) jTable.getValueAt(selectedRow, 5);
						String fechafirma = jTable.getValueAt(selectedRow, 6).toString();

						tfid.setText(id);
						tfParentCode.setText(descripcion);
						tfCode.setText(saldo);
						tfNombre.setText(limite);
						tffirma.setText(fechafirma);

					}
				}
			}

		});

		JScrollPane scrollTable = new JScrollPane(jTable);
		panelSuperior.add(scrollTable, BorderLayout.CENTER);
		GridBagLayout gbl_panelInferior = new GridBagLayout();
		gbl_panelInferior.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelInferior.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelInferior.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelInferior.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelInferior.setLayout(gbl_panelInferior);

		JLabel lblGestionDeProvincia = new JLabel("Gestion de Provincia");
		GridBagConstraints gbc_lblGestionDeProvincia = new GridBagConstraints();
		gbc_lblGestionDeProvincia.insets = new Insets(0, 0, 5, 5);
		gbc_lblGestionDeProvincia.gridx = 7;
		gbc_lblGestionDeProvincia.gridy = 0;
		panelInferior.add(lblGestionDeProvincia, gbc_lblGestionDeProvincia);
				
				
				
						JLabel lblNewLabel_3 = new JLabel("Code:");
						GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
						gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
						gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
						gbc_lblNewLabel_3.gridx = 1;
						gbc_lblNewLabel_3.gridy = 1;
						panelInferior.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
				tfCode = new JTextField();
				GridBagConstraints gbc_tfCode = new GridBagConstraints();
				gbc_tfCode.gridwidth = 13;
				gbc_tfCode.insets = new Insets(0, 0, 5, 0);
				gbc_tfCode.fill = GridBagConstraints.HORIZONTAL;
				gbc_tfCode.gridx = 2;
				gbc_tfCode.gridy = 1;
				panelInferior.add(tfCode, gbc_tfCode);
				tfCode.setColumns(10);
		
				JLabel lblNewLabel_4 = new JLabel("Nombre:");
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_4.gridx = 1;
				gbc_lblNewLabel_4.gridy = 2;
				panelInferior.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
				tfNombre = new JTextField();
				GridBagConstraints gbc_tfNombre = new GridBagConstraints();
				gbc_tfNombre.gridwidth = 13;
				gbc_tfNombre.insets = new Insets(0, 0, 5, 0);
				gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
				gbc_tfNombre.gridx = 2;
				gbc_tfNombre.gridy = 2;
				panelInferior.add(tfNombre, gbc_tfNombre);
				tfNombre.setColumns(10);
		
				JLabel lblNewLabel_2 = new JLabel("Ccaa:");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 1;
				gbc_lblNewLabel_2.gridy = 3;
				panelInferior.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
				JComboBox jcbCcaa = new JComboBox();
				GridBagConstraints gbc_jcbCcaa = new GridBagConstraints();
				gbc_jcbCcaa.gridwidth = 12;
				gbc_jcbCcaa.insets = new Insets(0, 0, 5, 5);
				gbc_jcbCcaa.fill = GridBagConstraints.HORIZONTAL;
				gbc_jcbCcaa.gridx = 2;
				gbc_jcbCcaa.gridy = 3;
				panelInferior.add(jcbCcaa, gbc_jcbCcaa);
		
				JButton btnGestionarCA = new JButton("Comunidad Autonoma");
				btnGestionarCA.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				GridBagConstraints gbc_btnGestionarCA = new GridBagConstraints();
				gbc_btnGestionarCA.insets = new Insets(0, 0, 5, 0);
				gbc_btnGestionarCA.gridx = 14;
				gbc_btnGestionarCA.gridy = 3;
				panelInferior.add(btnGestionarCA, gbc_btnGestionarCA);
		
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
				gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
				gbc_btnGuardar.gridx = 7;
				gbc_btnGuardar.gridy = 4;
				panelInferior.add(btnGuardar, gbc_btnGuardar);

		splitPane.setResizeWeight(0.5); // Configuracion de la distribución inicial de tamaño entre los componentes

		// Añadir el JSplitPane al frame y hacerlo visible
		frame.getContentPane().add(splitPane);
		frame.setVisible(true);

	}

}
