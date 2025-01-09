package facturacion.forms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import facturacion.clases.Producto;




    public class FrmNuevoProducto extends JDialog {
        private static final long serialVersionUID = 1L;

        private List<Producto> listaProductos;
        private Producto producto;

        private JTextField txtCodigo;
        private JTextField txtNombre;
        private JTextField txtPrecio;
        private JTextField txtStock;

        /**
         * @wbp.parser.constructor
         */
        public FrmNuevoProducto(List<Producto> listaProductos) {
            this.listaProductos = listaProductos;
            setTitle("Nuevo Producto");
            setSize(450, 300);
            setLocationRelativeTo(null);
            setModal(true);
            initComponents();
        }

        public FrmNuevoProducto(Producto producto, List<Producto> listaProductos) {
            this(listaProductos);
            this.producto = producto;
            setTitle("Editar Producto");
            cargarDatos();
        }

        private void initComponents() {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.weightx = 1.0;

            JLabel lblCodigo = new JLabel("Código:");
            txtCodigo = new JTextField();
            txtCodigo.setPreferredSize(new Dimension(200, 25));

            JLabel lblNombre = new JLabel("Nombre:");
            txtNombre = new JTextField();
            txtNombre.setPreferredSize(new Dimension(200, 25));

            JLabel lblPrecio = new JLabel("Precio:");
            txtPrecio = new JTextField();
            txtPrecio.setPreferredSize(new Dimension(200, 25));

            JLabel lblStock = new JLabel("Stock:");
            txtStock = new JTextField();
            txtStock.setPreferredSize(new Dimension(200, 25));

            // Agregar componentes al panel con GridBagLayout
            gbc.gridx = 0; gbc.gridy = 0;
            panel.add(lblCodigo, gbc);
            gbc.gridx = 1;
            panel.add(txtCodigo, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            panel.add(lblNombre, gbc);
            gbc.gridx = 1;
            panel.add(txtNombre, gbc);

            gbc.gridx = 0; gbc.gridy = 2;
            panel.add(lblPrecio, gbc);
            gbc.gridx = 1;
            panel.add(txtPrecio, gbc);

            gbc.gridx = 0; gbc.gridy = 3;
            panel.add(lblStock, gbc);
            gbc.gridx = 1;
            panel.add(txtStock, gbc);

            // Panel para los botones
            JPanel panelBotones = new JPanel();
            JButton btnGuardar = new JButton("Guardar");
            btnGuardar.setBounds(187, 5, 94, 23);
            JButton btnCancelar = new JButton("Cancelar");
            btnCancelar.setBounds(291, 5, 93, 23);

            btnGuardar.addActionListener(e -> guardarProducto());
            btnCancelar.addActionListener(e -> dispose());

            panelBotones.add(btnGuardar);
            panelBotones.add(btnCancelar);

            // Agregar paneles al contenido principal
            getContentPane().add(panel, BorderLayout.CENTER);
            getContentPane().add(panelBotones, BorderLayout.SOUTH);
        }

        private void cargarDatos() {
            if (producto != null) {
                txtCodigo.setText(producto.getCodigo());
                txtNombre.setText(producto.getNombre());
                txtPrecio.setText(String.valueOf(producto.getPrecio()));
                txtStock.setText(String.valueOf(producto.getStock()));
            }
        }

        private void guardarProducto() {
            if (validarDatos()) {
                if (producto == null) {
                    producto = new Producto(txtCodigo.getText(), txtNombre.getText(),
                            Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()));
                    listaProductos.add(producto);
                } else {
                    producto.setCodigo(txtCodigo.getText());
                    producto.setNombre(txtNombre.getText());
                    producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    producto.setStock(Integer.parseInt(txtStock.getText()));
                }

                JOptionPane.showMessageDialog(this, "Producto guardado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }

        private boolean validarDatos() {
            if (txtCodigo.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() ||
                    txtPrecio.getText().trim().isEmpty() || txtStock.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            try {
                Double.parseDouble(txtPrecio.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            try {
                Integer.parseInt(txtStock.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El stock debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }}