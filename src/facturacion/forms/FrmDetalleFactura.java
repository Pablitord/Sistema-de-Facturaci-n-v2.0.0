package facturacion.forms;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import facturacion.clases.DetalleFactura;
import facturacion.clases.Factura;
import java.awt.Font;

class FrmDetalleFactura extends JDialog {
    private static final long serialVersionUID = 1L;

    public FrmDetalleFactura(Factura factura) {
        setTitle("Detalles de Factura");
        getContentPane().setLayout(new BorderLayout(10, 10));

        String[] columnNames = {"Producto", "Cantidad", "Subtotal"};
        Object[][] data = new Object[factura.getDetalles().size()][3];

        for (int i = 0; i < factura.getDetalles().size(); i++) {
            DetalleFactura detalle = factura.getDetalles().get(i);
            data[i][0] = detalle.getProducto().getNombre();
            data[i][1] = detalle.getCantidad();
            data[i][2] = detalle.calcularTotal();
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        table.setFont(new Font("Tahoma", Font.BOLD, 13));
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelResumen = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel label = new JLabel("Subtotal:");
        label.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelResumen.add(label);
        JLabel label_3 = new JLabel(String.format("%.2f", factura.calcularSubtotal()));
        label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        panelResumen.add(label_3);
        JLabel label_1 = new JLabel("IVA:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelResumen.add(label_1);
        JLabel label_4 = new JLabel(String.format("%.2f", factura.calcularIVA()));
        label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
        panelResumen.add(label_4);
        JLabel label_2 = new JLabel("Total:");
        label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        panelResumen.add(label_2);
        JLabel label_5 = new JLabel(String.format("%.2f", factura.calcularTotal()));
        label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
        panelResumen.add(label_5);

        getContentPane().add(panelResumen, BorderLayout.SOUTH);

        setSize(500, 400);
        setLocationRelativeTo(null);
    }
}
