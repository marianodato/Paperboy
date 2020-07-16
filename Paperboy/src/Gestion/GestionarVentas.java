package Gestion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import Clases.Venta;
import General.BaseDatos;

public class GestionarVentas 
{
	public static Vector<Venta> ventas = new Vector<Venta>();
	
	public static void AgregarVenta(Venta venta) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			BaseDatos.NonQuery("INSERT INTO Venta VALUES ("+venta.getCodigo()+","+venta.getCod_cliente()+
					","+venta.getCod_cobro()+",'"+venta.getFecha()+"',"+venta.getCod_mercaderia()+","+venta.getCantidad()+")");
			ventas.add(new Venta(venta.getCodigo(),venta.getCod_cliente(),venta.getFecha(),venta.getCod_mercaderia(),venta.getCantidad(),venta.getCod_cobro()));
	}
	public static Vector<Venta> BuscarVenta(String value,String campo) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{				
		String string = "SELECT * FROM Venta WHERE "+campo+" LIKE '"+value+"'";
		System.out.println(string);
		return BaseDatos.VentaQuery(string);		
	}
	public static void CargarVentas() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{				
			ventas = BaseDatos.VentaQuery("SELECT * FROM Venta");		
	}

	public static void BackUp(Venta venta) throws IOException
	{
		FileWriter file = new FileWriter("ventas.txt",true);
		PrintWriter pw = new PrintWriter(file);
		pw.println(venta.getCodigo()+"/"+venta.getCod_cliente()+"/"+venta.getFecha()+"/"+venta.getCod_mercaderia()+"/"+venta.getCantidad()+"/"+venta.getCod_cobro());
		file.close();
	}
	
}
