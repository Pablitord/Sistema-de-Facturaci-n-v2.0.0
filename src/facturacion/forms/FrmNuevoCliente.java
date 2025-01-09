package facturacion.forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import facturacion.clases.Cliente;


class FrmNuevoCliente extends JDialog {
    private static final long serialVersionUID = 1L;
    private List<Cliente> listaClientes;
    private Cliente cliente;
    private JTextField txtCedula, txtNombres, txtApellidos, txtDireccion, txtTelefono, txtEmail;

    /**
     * @wbp.parser.constructor
     */
    public FrmNuevoCliente(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        setTitle("Nuevo Cliente");
        setSize(410, 300);
        setLocationRelativeTo(null);
        setModal(true);
        initComponents();
    }

    public FrmNuevoCliente(Cliente cliente, List<Cliente> listaClientes) {
        this(listaClientes);
        this.cliente = cliente;
        setTitle("Editar Cliente");
        cargarDatos();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 394, 228);
        
        txtCedula = new JTextField();
        txtCedula.setBounds(148, 11, 192, 24);
        txtCedula.setHorizontalAlignment(SwingConstants.LEFT);
        txtNombres = new JTextField();
        txtNombres.setBounds(148, 45, 192, 24);
        txtNombres.setHorizontalAlignment(SwingConstants.LEFT);
        txtApellidos = new JTextField();
        txtApellidos.setBounds(148, 79, 192, 24);
        txtApellidos.setHorizontalAlignment(SwingConstants.LEFT);
        txtDireccion = new JTextField();
        txtDireccion.setBounds(148, 113, 192, 24);
        txtDireccion.setHorizontalAlignment(SwingConstants.LEFT);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(148, 147, 192, 24);
        txtTelefono.setHorizontalAlignment(SwingConstants.LEFT);
        txtEmail = new JTextField();
        txtEmail.setBounds(148, 181, 192, 24);
        txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
        panel.setLayout(null);

        JLabel label = new JLabel("Cédula:");
        label.setBounds(0, 11, 138, 24);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        panel.add(txtCedula);
        JLabel label_1 = new JLabel("Nombres:");
        label_1.setBounds(0, 45, 138, 24);
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label_1);
        panel.add(txtNombres);
        JLabel label_2 = new JLabel("Apellidos:");
        label_2.setBounds(0, 79, 138, 24);
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label_2);
        panel.add(txtApellidos);
        JLabel label_3 = new JLabel("Dirección:");
        label_3.setBounds(0, 113, 138, 24);
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label_3);
        panel.add(txtDireccion);
        JLabel label_4 = new JLabel("Teléfono:");
        label_4.setBounds(0, 147, 138, 24);
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label_4);
        panel.add(txtTelefono);
        JLabel label_5 = new JLabel("Email:");
        label_5.setBounds(0, 181, 138, 24);
        label_5.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label_5);
        panel.add(txtEmail);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(187, 5, 94, 23);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(291, 5, 93, 23);
        
        btnGuardar.addActionListener(e -> guardarCliente());
        btnCancelar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.setBounds(0, 228, 394, 33);
        panelBotones.setLayout(null);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        getContentPane().setLayout(null);

        getContentPane().add(panel);
        getContentPane().add(panelBotones);
    }

    private void cargarDatos() {
    	if (cliente != null) {
            txtCedula.setText(cliente.getCedula());
            txtNombres.setText(cliente.getNombre());
            txtApellidos.setText(cliente.getApellido());
            txtDireccion.setText(cliente.getDireccion());
            txtTelefono.setText(cliente.getTelefono());
            txtEmail.setText(cliente.getEmail());
        }
    }

    private void guardarCliente() {
        if (validarDatos()) {
            if (cliente == null) {
                cliente = new Cliente();
                listaClientes.add(cliente);
            }
            // Asignar los datos del formulario al cliente
            cliente.setCedula(txtCedula.getText());
            cliente.setNombre(txtNombres.getText());
            cliente.setApellido(txtApellidos.getText());
            cliente.setDireccion(txtDireccion.getText());
            cliente.setTelefono(txtTelefono.getText());
            cliente.setEmail(txtEmail.getText());

            JOptionPane.showMessageDialog(this, "Cliente guardado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private boolean validarDatos() {
        if (txtCedula.getText().isEmpty() || txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty() ||
                txtDireccion.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!txtEmail.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "El email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtTelefono.getText().length() < 7) {
            JOptionPane.showMessageDialog(this, "El número de teléfono debe tener al menos 7 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}