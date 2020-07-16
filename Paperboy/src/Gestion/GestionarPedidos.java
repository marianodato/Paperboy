package Gestion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import Clases.Cliente;
import Clases.Mercaderia;
import Clases.Pago;
import Clases.Pedido;
import Clases.Venta;
import General.BaseDatos;

public class GestionarPedidos 
{
	public static Vector<Pedido> pedidos = new Vector<Pedido>();
	
	public static void AgregarPedido(Pedido pedido) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			BaseDatos.NonQuery("INSERT INTO pedido (cod_pedido,cod_cliente,cod_mercaderia,dia_entrega) VALUES ("+pedido.getCodigo()+","+pedido.getCod_cliente()+
					","+pedido.getCod_mercaderia()+",'"+pedido.getFecha_entrega()+"')");
			pedidos.add(new Pedido(pedido.getCodigo(),pedido.getCod_cliente(),pedido.getCod_mercaderia(),pedido.getFecha_entrega()));
	}
	public static Vector<Pedido> BuscarPedido(String value,String campo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
			String string = "SELECT * FROM Pedido WHERE "+campo+" LIKE '"+value+"'";
			System.out.println(string);
			return BaseDatos.PedidoQuery(string);	
	}
	public static void EliminarPedido(Pedido p) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
			String string = "DELETE FROM Pedido WHERE cod_pedido="+p.getCodigo();
			System.out.println(string);
			BaseDatos.NonQuery(string);	
	}
	public static void Cargar() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException
	{
		pedidos = BaseDatos.PedidoQuery("SELECT * FROM Pedido");
		System.out.println(pedidos.size());
		for(int i=0; i<pedidos.size();i++)
		{
			String[] fecha1 = pedidos.get(i).getFecha_entrega().split("-");
			Calendar cal = Calendar.getInstance();
			if(Integer.parseInt(fecha1[1])<=cal.get(Calendar.MONTH)+1) GenerarVenta(pedidos.get(i));
			else if(Integer.parseInt(fecha1[1])==cal.get(Calendar.MONTH)+1 && Integer.parseInt(fecha1[2])<=cal.get(Calendar.DATE))GenerarVenta(pedidos.get(i));
		}
	}
	public static void GenerarVenta(Pedido p) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException
	{
		int cod_venta = -1;
		for(int i=0;true;i++)
		{
			for(int j=0;j<GestionarVentas.ventas.size();j++)
			{
				if(GestionarVentas.ventas.get(j).getCodigo() == i) break;
				else if(j+1 == GestionarVentas.ventas.size()) cod_venta = i;
			}
			if(cod_venta != -1) break;
		}

		GestionarVentas.AgregarVenta(new Venta(cod_venta,p.getCod_cliente(),p.getFecha_entrega(),p.getCod_mercaderia(),1));
		GestionarPedidos.EliminarPedido(p);
	}
	public static void BackUp(Pedido pedido) throws IOException
	{
		FileWriter file = new FileWriter("pedidos.txt",true);
		PrintWriter pw = new PrintWriter(file);
		pw.println(pedido.getCodigo()+"/"+pedido.getCod_cliente()+"/"+pedido.getCod_mercaderia()+"/"+pedido.getFecha_entrega());
		file.close();
	}
}
