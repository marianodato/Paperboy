package Clases;

public class Cobro {

	private int codigo;
	private float precio;
	
	public Cobro(int codigo, float precio) {
		super();
		this.codigo = codigo;
		this.precio = precio;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
	
}
