package facturacion.forms;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import facturacion.clases.Cliente;
import java.awt.Font;
import java.awt.Color;

public class FrmClientes extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Cliente> listaClientes;

	
	public FrmClientes(List<Cliente> listaClientes) {
	    this.listaClientes = listaClientes;
	    setTitle("Gestión de Clientes");
	    setSize(616, 419);
	    setClosable(true);
	    setMaximizable(true);
	    setIconifiable(true);
	    setResizable(true);

	    JPanel panel = new JPanel();
	    String[] columnNames = {"Cédula", "Nombres", "Apellidos", "Teléfono", "Dirección", "Email"};
	    panel.setLayout(null);
	    JTable table = new JTable();
	    actualizarTabla(table, columnNames); // Actualizar tabla al cargar la interfaz
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(0, 41, 600, 337);
	    panel.add(scrollPane);

	    JButton btnNuevoCliente = new JButton("Agregar Cliente");
	    btnNuevoCliente.setBackground(new Color(245, 183, 247));
	    btnNuevoCliente.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    btnNuevoCliente.setBounds(27, 5, 155, 26);
	    JButton btnEditarCliente = new JButton("Editar Cliente");
	    btnEditarCliente.setBackground(new Color(245, 183, 247));
	    btnEditarCliente.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    btnEditarCliente.setBounds(240, 5, 130, 26);
	    
	    JButton btnEliminarCliente = new JButton("Eliminar Cliente");
	    btnEliminarCliente.setBackground(new Color(245, 183, 247));
	    btnEliminarCliente.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    btnEliminarCliente.setBounds(439, 5, 151, 26);

	    JPanel panelBotones = new JPanel();
	    panelBotones.setBounds(0, 0, 600, 42);
	    panelBotones.setLayout(null);
	    panelBotones.add(btnNuevoCliente);
	    panelBotones.add(btnEditarCliente);
	    panelBotones.add(btnEliminarCliente);

	    panel.add(panelBotones);
	    getContentPane().add(panel);

	    btnNuevoCliente.addActionListener(e -> {
	        FrmNuevoCliente dialogo = new FrmNuevoCliente(listaClientes);
	        dialogo.setVisible(true);
	        actualizarTabla(table, columnNames);
	    });

	    btnEditarCliente.addActionListener(e -> {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow >= 0) {
	            Cliente cliente = listaClientes.get(selectedRow);
	            FrmNuevoCliente dialogo = new FrmNuevoCliente(cliente, listaClientes);
	            dialogo.setVisible(true);
	            actualizarTabla(table, columnNames);
	        } else {
	            JOptionPane.showMessageDialog(this, "Seleccione un cliente para editar.");
	        }
	    });

	    btnEliminarCliente.addActionListener(e -> {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow >= 0) {
	            listaClientes.remove(selectedRow);
	            actualizarTabla(table, columnNames);
	        } else {
	            JOptionPane.showMessageDialog(this, "Seleccione un cliente para eliminar.");
	        }
	    });
	}

	private void actualizarTabla(JTable table, String[] columnNames) {
	    Object[][] data = new Object[listaClientes.size()][6];
	    for (int i = 0; i < listaClientes.size(); i++) {
	        Cliente cliente = listaClientes.get(i);
	        data[i][0] = cliente.getCedula();
	        data[i][1] = cliente.getNombre();
	        data[i][2] = cliente.getApellido();
	        data[i][3] = cliente.getTelefono();
	        data[i][4] = cliente.getDireccion();
	        data[i][5] = cliente.getEmail();
	    }
	    table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
	}
}