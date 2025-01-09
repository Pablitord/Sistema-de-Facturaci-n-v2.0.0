package facturacion.forms;
 import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import facturacion.clases.Producto;
import java.awt.Font;
import java.awt.Color;

class FrmProductos extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private List<Producto> listaProductos;
    private JTable table;
    private String[] columnNames = {"Código", "Nombre", "Precio", "Stock"};

    public FrmProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
        setTitle("Gestión de Productos");
        setSize(617, 398);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 601, 370);
        table = new JTable();
        actualizarTabla();
        getContentPane().setLayout(null);
        panel.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 44, 584, 315);
        panel.add(scrollPane);
        getContentPane().add(panel);
        
                JButton btnNuevoProducto = new JButton("Agregar Producto");
                btnNuevoProducto.setBackground(new Color(245, 183, 247));
                btnNuevoProducto.setFont(new Font("Century Gothic", Font.BOLD, 13));
                btnNuevoProducto.setBounds(26, 5, 159, 27);
                JButton btnEditarProducto = new JButton("Editar Producto");
                btnEditarProducto.setBackground(new Color(245, 183, 247));
                btnEditarProducto.setFont(new Font("Century Gothic", Font.BOLD, 13));
                btnEditarProducto.setBounds(230, 6, 148, 25);
                JButton btnEliminarProducto = new JButton("Eliminar Producto");
                btnEliminarProducto.setBackground(new Color(245, 183, 247));
                btnEliminarProducto.setFont(new Font("Century Gothic", Font.BOLD, 13));
                btnEliminarProducto.setBounds(425, 7, 159, 25);
                
                        JPanel panelBotones = new JPanel();
                        panelBotones.setBounds(0, 0, 594, 43);
                        panel.add(panelBotones);
                        panelBotones.setLayout(null);
                        panelBotones.add(btnNuevoProducto);
                        panelBotones.add(btnEditarProducto);
                        panelBotones.add(btnEliminarProducto);

        btnNuevoProducto.addActionListener(e -> {
            FrmNuevoProducto dialogo = new FrmNuevoProducto(listaProductos);
            dialogo.setVisible(true);
            actualizarTabla();
        });

        btnEditarProducto.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Producto producto = listaProductos.get(selectedRow);
                FrmNuevoProducto dialogo = new FrmNuevoProducto(producto, listaProductos);
                dialogo.setVisible(true);
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnEliminarProducto.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    listaProductos.remove(selectedRow);
                    actualizarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void actualizarTabla() {
        Object[][] data = new Object[listaProductos.size()][4];
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto producto = listaProductos.get(i);
            data[i][0] = producto.getCodigo();
            data[i][1] = producto.getNombre();
            data[i][2] = String.format("%.2f", producto.getPrecio());
            data[i][3] = producto.getStock();
        }
        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacemos que la tabla sea no editable
            }
        });

        // Centrar columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
