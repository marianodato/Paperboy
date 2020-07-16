package Gestion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import Clases.Cobro;
import Clases.Pago;
import Clases.Venta;
import General.BaseDatos;

public class GestionarCaja
{
	public static Vector<Cobro> cobros = new Vector<Cobro>();
	public static Vector<Pago> pagos = new Vector<Pago>();
	
	public static void AgregarPago(Pago pago) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			BaseDatos.NonQuery("INSERT INTO Pago (cod_pago,cod_mercaderia,nombre_proveedor,precio) VALUES ("+pago.getCodigo()+","+pago.getCodmerca()+
					",'"+pago.getNomprovedor()+"',"+pago.getPrecio()+")");
			pagos.add(new Pago(pago.getCodigo(),pago.getCodmerca(),pago.getNomprovedor(),pago.getPrecio()));
	}
	public static void AgregarCobro(Cobro cobro) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			BaseDatos.NonQuery("INSERT INTO Cobro (cod_cobro,precio) VALUES ("+cobro.getCodigo()+",'"+cobro.getPrecio()+"')");
			cobros.add(new Cobro(cobro.getCodigo(),cobro.getPrecio()));
	}
	public static Vector<Cobro> BuscarCobro(String value,String campo) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{				
		String string = "SELECT * FROM Cobro WHERE "+campo+" LIKE '"+value+"'";
		System.out.println(string);
		return BaseDatos.CobroQuery(string);		
	}
	
	public static Vector<Pago> BuscarPago(String value,String campo) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{				
		String string = "SELECT * FROM Pago WHERE "+campo+" LIKE '"+value+"'";
		System.out.println(string);
		return BaseDatos.PagoQuery(string);		
	}
	
	public static void CargarPagos() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ConnectException
	{
		pagos = BaseDatos.PagoQuery("SELECT * FROM Pago");
	}
	public static void CargarCobros() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		cobros = BaseDatos.CobroQuery("SELECT * FROM Cobro");
	}
	public static Pago getPago(int cod)
	{
		for(int i=0;i<pagos.size();i++)
			if(cod == pagos.get(i).getCodigo())return pagos.get(i);
		return new Pago(0,0,"Nadie",0);
	}
	public static Cobro getCobro(int cod)
	{
		for(int i=0;i<cobros.size();i++)
			if(cod == cobros.get(i).getCodigo())return cobros.get(i);
		return new Cobro(0,0);
	}
	public static void BackUp(Cobro cobro) throws IOException
	{
		FileWriter file = new FileWriter("cobros.txt",true);
		PrintWriter pw = new PrintWriter(file);
		pw.println(cobro.getCodigo()+"/"+cobro.getPrecio());
		file.close();
	}
	public static void BackUp(Pago pago) throws IOException
	{
		FileWriter file = new FileWriter("pago.txt",true);
		PrintWriter pw = new PrintWriter(file);
		pw.println(pago.getCodigo()+"/"+pago.getCodmerca()+"/"+pago.getNomprovedor()+"/"+pago.getPrecio());
		file.close();
	}
}
