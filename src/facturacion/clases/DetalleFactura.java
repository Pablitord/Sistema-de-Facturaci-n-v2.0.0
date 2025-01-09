package facturacion.clases;

public class DetalleFactura {
	
	 private Producto producto;
	    private int cantidad;

	    public DetalleFactura(Producto producto, int cantidad) {
	        this.producto = producto;
	        this.cantidad = cantidad;
	    }

	    public Producto getProducto() {
	        return producto;
	    }

	    public int getCantidad() {
	        return cantidad;
	    }

	    public double calcularTotal() {
	        return producto.getPrecio() * cantidad;
	    }
}

