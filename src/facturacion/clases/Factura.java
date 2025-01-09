package facturacion.clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Factura {
	
	private static int contador = 1;
	private int numero;
	private String fecha;
	private Cliente cliente;
	private List<DetalleFactura> detalles;
	
	public Factura(String fecha, Cliente cliente) {
        this.numero = contador++;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = LocalDate.now().format(formatter);
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
    }

	
	 public int getNumero() {
	        return numero;
	    }

	    public String getFecha() {
	        return fecha;
	    }

	    public Cliente getCliente() {
	        return cliente;
	    }

	    public List<DetalleFactura> getDetalles() {
	        return detalles;
	    }

	    public void agregarDetalle(DetalleFactura detalle) {
	        detalles.add(detalle);
	    }

	    public double calcularSubtotal() {
	        return detalles.stream().mapToDouble(DetalleFactura::calcularTotal).sum();
	    }

	    public double calcularIVA() {
	        return calcularSubtotal() * 0.12;
	    }

	    public double calcularTotal() {
	        return calcularSubtotal() + calcularIVA();
	    }
}
