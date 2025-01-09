package facturacion.forms;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import facturacion.clases.Cliente;
import facturacion.clases.DetalleFactura;
import facturacion.clases.Factura;
import facturacion.clases.Producto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class FrmFactura extends JDialog {
    private static final long serialVersionUID = 1L;
    private List<Cliente> listaClientes;
    private List<Producto> listaProductos;
    private List<Factura> listaFacturas;
    private List<DetalleFactura> detallesFactura = new ArrayList<>();
    private JTable table;
    private JTextField txtCodigo;

    public FrmFactura(List<Cliente> listaClientes, List<Producto> listaProductos, List<Factura> listaFacturas) {
        this.listaClientes = listaClientes;
        this.listaProductos = listaProductos;
        this.listaFacturas = listaFacturas;
        setTitle("Nueva Factura");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setModal(true);
        initComponents();
    }

    private void initComponents() {
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Panel para seleccionar cliente
        JPanel panelCliente = new JPanel();
        JLabel lblCliente = new JLabel("Cliente:");
        JComboBox<Cliente> comboClientes = new JComboBox<>(listaClientes.toArray(new Cliente[0]));
        JLabel lblCedula = new JLabel("Cédula:");
        JTextField txtCedula = new JTextField(10);
        JButton btnBuscarCliente = new JButton("Buscar");
        
        panelCliente.add(lblCliente);
        panelCliente.add(comboClientes);
        panelCliente.add(lblCedula);
        panelCliente.add(txtCedula);
        panelCliente.add(btnBuscarCliente);

        // Panel para agregar productos
        JPanel panelProductos = new JPanel();
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(10, 21, 72, 14);
        JComboBox<Producto> comboProductos = new JComboBox<>(listaProductos.toArray(new Producto[0]));
        comboProductos.setBounds(92, 17, 72, 22);
        JTextField txtCantidad = new JTextField(5);
        txtCantidad.setBounds(507, 15, 46, 20);
        JButton btnAgregarProducto = new JButton("Agregar");
        btnAgregarProducto.setBounds(563, 12, 86, 23);
        panelProductos.setLayout(null);

        panelProductos.add(lblProducto);
        panelProductos.add(comboProductos);
        JLabel label = new JLabel("Cantidad:");
        label.setBounds(444, 17, 60, 14);
        panelProductos.add(label);
        panelProductos.add(txtCantidad);
        panelProductos.add(btnAgregarProducto);

        // Tabla para mostrar productos seleccionados
        String[] columnNames = {"Producto", "Cantidad", "Subtotal"};
        table = new JTable(new DefaultTableModel(new Object[0][3], columnNames));
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel de botones para guardar o cancelar
        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        // Agregar paneles al layout
        getContentPane().add(panelCliente, BorderLayout.NORTH);
        getContentPane().add(panelProductos, BorderLayout.CENTER);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);
        getContentPane().add(panelBotones, BorderLayout.PAGE_END);

        // Acciones de botones
        btnAgregarProducto.addActionListener(e -> agregarProducto(comboProductos, txtCantidad));
        
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(190, 15, 46, 14);
        panelProductos.add(lblCodigo);
        
        txtCodigo = new JTextField(10);
        txtCodigo.setBounds(236, 12, 86, 20);
        panelProductos.add(txtCodigo);
        btnGuardar.addActionListener(e -> guardarFactura(comboClientes));
        btnCancelar.addActionListener(e -> dispose());
        
        JButton btnbuscarProducto = new JButton("Buscar");
        btnbuscarProducto.setBounds(332, 12, 86, 23);
        panelProductos.add(btnbuscarProducto);
        btnBuscarCliente.addActionListener(e -> buscarCliente(txtCedula, comboClientes));
        btnbuscarProducto.addActionListener(e -> buscarProducto(txtCodigo, comboProductos));
    }

    // Método para buscar cliente por cédula
    private void buscarCliente(JTextField txtCedula, JComboBox<Cliente> comboClientes) {
        String cedula = txtCedula.getText().trim();
        Cliente clienteEncontrado = listaClientes.stream()
            .filter(cliente -> cliente.getCedula().equals(cedula))
            .findFirst()
            .orElse(null);

        if (clienteEncontrado != null) {
            comboClientes.setSelectedItem(clienteEncontrado);
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarProducto(JTextField txtCodigo, JComboBox<Producto> comboProductos ) {
    	String codigo = txtCodigo.getText().trim();
    	Producto productoEncontrado = listaProductos.stream()
    			.filter(producto -> producto.getCodigo().equals(codigo))
    			.findFirst()
    			.orElse(null);
    	if (productoEncontrado != null) {
    		comboProductos.setSelectedItem(productoEncontrado);
    	} else {
    		JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	
    	
    }


    private void agregarProducto(JComboBox<Producto> comboProductos, JTextField txtCantidad) {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            Producto producto = (Producto) comboProductos.getSelectedItem();

            if (producto != null && producto.getStock() >= cantidad) {
                DetalleFactura detalle = new DetalleFactura(producto, cantidad);
                detallesFactura.add(detalle);
                producto.setStock(producto.getStock() - cantidad);
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Stock insuficiente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarFactura(JComboBox<Cliente> comboClientes) {
        Cliente cliente = (Cliente) comboClientes.getSelectedItem();
        if (cliente != null && !detallesFactura.isEmpty()) {
            Factura factura = new Factura("Fecha Actual", cliente);
            detallesFactura.forEach(factura::agregarDetalle);
            listaFacturas.add(factura);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Complete todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla() {
        String[] columnNames = {"Producto", "Cantidad", "Subtotal"};
        Object[][] data = new Object[detallesFactura.size()][3];

        for (int i = 0; i < detallesFactura.size(); i++) {
            DetalleFactura detalle = detallesFactura.get(i);
            data[i][0] = detalle.getProducto().getNombre();
            data[i][1] = detalle.getCantidad();
            data[i][2] = detalle.calcularTotal();
        }

        table.setModel(new DefaultTableModel(data, columnNames));
    }
}
