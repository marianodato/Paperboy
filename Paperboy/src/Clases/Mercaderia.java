package Clases;

import Gestion.GestionarMercaderias;

public class Mercaderia 
{
	private String nombre;
	private int stock;
	private String descripcion;
	private int codigo;
	private float precio;
	
	public Mercaderia(int codigo, String nombre,String descripcion, float precio, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.stock = stock;
		this.descripcion = descripcion;
		this.precio=precio;
	}
	
	

	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public int getStock() {return stock;}
	public void setStock(int cant) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		stock = stock-cant;
		GestionarMercaderias.ModificarStock(codigo,stock);
	}
	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	public int getCodigo() {return codigo;}
	public void setCodigo(int codigo) {this.codigo = codigo;}
	public float getPrecio() {return precio;}
	public void setPrecio(float precio) {this.precio = precio;}	
}
