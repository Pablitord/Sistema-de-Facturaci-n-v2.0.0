package facturacion.forms;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import facturacion.clases.Cliente;
import facturacion.clases.Factura;
import facturacion.clases.Producto;
import java.awt.Font;
import java.awt.Color;

class FrmGestionFacturas extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    private List<Factura> listaFacturas;

    public FrmGestionFacturas(List<Cliente> listaClientes, List<Producto> listaProductos, List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;

        setTitle("Gestión de Facturas");
        setSize(1015, 648);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 984, 670);
        String[] columnNames = {"Número", "Fecha", "Cliente", "Subtotal", "IVA", "Total"};
        Object[][] data = {};
        getContentPane().setLayout(null);
        panel.setLayout(null);
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 45, 974, 556);
        panel.add(scrollPane);

        JButton btnNuevaFactura = new JButton("Nueva Factura");
        btnNuevaFactura.setBackground(new Color(245, 183, 247));
        btnNuevaFactura.setFont(new Font("Century Gothic", Font.BOLD, 13));
        btnNuevaFactura.setBounds(32, 5, 133, 28);
        JButton btnDetalleFactura = new JButton("Ver Detalle");
        btnDetalleFactura.setBackground(new Color(245, 183, 247));
        btnDetalleFactura.setFont(new Font("Century Gothic", Font.BOLD, 13));
        btnDetalleFactura.setBounds(466, 5, 111, 28);
        JButton btnAnularFactura = new JButton("Anular Factura");
        btnAnularFactura.setBackground(new Color(245, 183, 247));
        btnAnularFactura.setFont(new Font("Century Gothic", Font.BOLD, 13));
        btnAnularFactura.setBounds(812, 5, 139, 28);

        JPanel panelBotones = new JPanel();
        panelBotones.setBounds(10, 0, 984, 44);
        panelBotones.setLayout(null);
        panelBotones.add(btnNuevaFactura);
        panelBotones.add(btnDetalleFactura);
        panelBotones.add(btnAnularFactura);

        panel.add(panelBotones);
        getContentPane().add(panel);

        btnNuevaFactura.addActionListener(e -> {
            FrmFactura dialogo = new FrmFactura(listaClientes, listaProductos, listaFacturas);
            dialogo.setVisible(true);
            actualizarTabla(table, columnNames);
        });

        btnDetalleFactura.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();	
            if (selectedRow >= 0) {
                Factura factura = listaFacturas.get(selectedRow);
                new FrmDetalleFactura(factura).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una factura para ver el detalle.");
            }
        });

        btnAnularFactura.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea anular esta factura?", "Confirmar Anulación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    listaFacturas.remove(selectedRow);
                    actualizarTabla(table, columnNames);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una factura para anular.");
            }
        });
    }

    private void actualizarTabla(JTable table, String[] columnNames) {
        Object[][] data = new Object[listaFacturas.size()][6];
        for (int i = 0; i < listaFacturas.size(); i++) {
            Factura factura = listaFacturas.get(i);
            data[i][0] = i + 1; // Número de factura
            data[i][1] = factura.getFecha();
            data[i][2] = factura.getCliente().getNombre();
            data[i][3] = String.format("%.2f", factura.calcularSubtotal());
            data[i][4] = String.format("%.2f", factura.calcularIVA());
            data[i][5] = String.format("%.2f", factura.calcularTotal());
        }
        table.setModel(new DefaultTableModel(data, columnNames));
    }
}
