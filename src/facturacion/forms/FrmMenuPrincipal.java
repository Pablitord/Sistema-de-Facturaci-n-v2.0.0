package facturacion.forms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import facturacion.clases.Cliente;
import facturacion.clases.Factura;
import facturacion.clases.Producto;
import java.awt.Font;

public class FrmMenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Producto> listaProductos = new ArrayList<>();
    private List<Factura> listaFacturas = new ArrayList<>();

    public FrmMenuPrincipal() {
        setTitle("Sistema de Facturación v2.0.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        JDesktopPane desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        // Personalizar el color de la barra de menú
        UIManager.put("MenuBar.background", Color.LIGHT_GRAY);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(249, 191, 235));
        menuBar.setForeground(new Color(249, 191, 235));
        setJMenuBar(menuBar);

        JMenu menuClientes = new JMenu("Clientes");
        menuClientes.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
        menuClientes.setBackground(new Color(255, 13, 255));
        JMenu menuProductos = new JMenu("Productos");
        menuProductos.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
        menuProductos.setBackground(new Color(255, 113, 255));
        JMenu menuFacturas = new JMenu("Facturas");
        menuFacturas.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
        menuFacturas.setBackground(new Color(255, 157, 255));
        
                // Botón de "Salir"
                JButton btnSalir = new JButton("Salir");
                btnSalir.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
                btnSalir.setBackground(new Color(202, 0, 202));
                menuBar.add(btnSalir);
                // Acción para cerrar la aplicación
                btnSalir.addActionListener(e -> System.exit(0));

        menuBar.add(menuClientes);
        menuBar.add(menuProductos);
        menuBar.add(menuFacturas);

        JMenuItem itemGestionClientes = new JMenuItem("Lista de Clientes");
        itemGestionClientes.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
        JMenuItem itemGestionProductos = new JMenuItem("Gestionar Productos");
        itemGestionProductos.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
        JMenuItem itemGestionFacturas = new JMenuItem("Gestionar Facturas");
        itemGestionFacturas.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));

        menuClientes.add(itemGestionClientes);
        menuProductos.add(itemGestionProductos);
        menuFacturas.add(itemGestionFacturas);

        // Acciones de los menús
        itemGestionClientes.addActionListener(e -> {
            FrmClientes clientesFrame = new FrmClientes(listaClientes);
            desktopPane.add(clientesFrame);
            clientesFrame.setVisible(true);
        });

        itemGestionProductos.addActionListener(e -> {
            FrmProductos productosFrame = new FrmProductos(listaProductos);
            desktopPane.add(productosFrame);
            productosFrame.setVisible(true);
        });

        itemGestionFacturas.addActionListener(e -> {
            FrmGestionFacturas facturasFrame = new FrmGestionFacturas(listaClientes, listaProductos, listaFacturas);
            desktopPane.add(facturasFrame);
            facturasFrame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmMenuPrincipal frmMenuPrincipal = new FrmMenuPrincipal();
            frmMenuPrincipal.setVisible(true);
        });
    }
}