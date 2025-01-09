package facturacion.clases;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    
    @Override
    public String toString() {
        return this.nombre; 
    }


    
    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    
    public Producto() {
        this.codigo = "";
        this.nombre = "";
        this.precio = 0.0;
        this.stock = 0;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

   
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
