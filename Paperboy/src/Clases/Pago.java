package Clases;

import java.sql.SQLException;
import java.util.Vector;

import Gestion.GestionarCaja;
import Gestion.GestionarMercaderias;

public class Pago {

	private int codigo;
	private int codmerca;
	private String nomprovedor;
	private float precio;
	
	public Pago(int codigo, int codmerca, String nomprovedor, float precio) {
		super();
		this.codigo = codigo;
		this.codmerca = codmerca;
		this.nomprovedor = nomprovedor;
		this.precio = precio;
	}
	public Pago(int codmerca, String nomprovedor, float precio) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		this.codmerca = codmerca;
		this.nomprovedor = nomprovedor;
		this.precio = precio;
		
		codigo = -1;
		for(int i=0;true;i++)
		{
			for(int j=0;j<GestionarCaja.pagos.size();j++)
			{
				if(GestionarCaja.pagos.get(j).getCodigo() == i) break;
				else if(j+1 == GestionarCaja.pagos.size()) codigo = i;
			}
			if(codigo != -1) break;
		}
		GestionarCaja.AgregarPago(new Pago(codigo,codmerca,nomprovedor,precio));
		GestionarCaja.pagos.add(new Pago(codigo,codmerca,nomprovedor,precio));
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodmerca() {
		return codmerca;
	}

	public void setCodmerca(int codmerca) {
		this.codmerca = codmerca;
	}

	public String getNomprovedor() {
		return nomprovedor;
	}

	public void setNomprovedor(String nomprovedor) {
		this.nomprovedor = nomprovedor;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
	
	
}
