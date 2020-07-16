package Clases;

public class Pedido 
{
	int codigo, cod_cliente, cod_mercaderia;
	String fecha_entrega;
	
	public Pedido(int codigo, int cod_cliente, int cod_mercaderia,
			String fecha_entrega) {
		super();
		this.codigo = codigo;
		this.cod_cliente = cod_cliente;
		this.cod_mercaderia = cod_mercaderia;
		this.fecha_entrega = fecha_entrega;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public int getCod_mercaderia() {
		return cod_mercaderia;
	}

	public void setCod_mercaderia(int cod_mercaderia) {
		this.cod_mercaderia = cod_mercaderia;
	}

	public String getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	
	
}
