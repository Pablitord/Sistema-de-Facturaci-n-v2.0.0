package facturacion.clases;
import facturacion.forms.FrmMenuPrincipal;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmMenuPrincipal frame = new FrmMenuPrincipal ();
            frame.setVisible(true);
        });
    }

}
