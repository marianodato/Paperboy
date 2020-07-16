package General;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;

import Clases.Cliente;
import Clases.Cobro;
import Clases.Mercaderia;
import Clases.Pago;
import Clases.Pedido;
import Clases.Venta;
import Gestion.GestionarCaja;
import Gestion.GestionarClientes;
import Gestion.GestionarMercaderias;
import Gestion.GestionarPedidos;
import Gestion.GestionarVentas;

import com.mysql.jdbc.*;

public class BaseDatos 
{
	protected static Connection conn;
	protected static Statement stmt;
	protected static ResultSet rs;
	
	public void BaseDatos()
	{
		// Creo una conexion
		Connection conn = null;
		// Creo una instruccion
		Statement stmt = null;
		// Creo un set de resultados
		ResultSet rs = null;
	}
	
	public static boolean Check() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, ConnectException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		try{
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/phpmyadmin?user=root");
			conn.close();
			return true;
		}
		catch(Exception e){return false;}
	}
	
	public static void Cargar() throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{	
		CargarClientes();
		CargarMercaderias();
		CargarPedidos();
		CargarVentas();
		CargarCobros();
		CargarPagos();
	}
	public static void CargarClientes() throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		File file = new File("clientes.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String linea;
		while((linea=br.readLine())!=null)
		{
			String[] lineas = linea.split("/");
			GestionarClientes.AgregarCliente(new Cliente(Integer.parseInt(lineas[0]),lineas[1],lineas[2],lineas[3],Integer.parseInt(lineas[4])));
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.close();
		fr.close();
	}
	
	public static void CargarMercaderias() throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		File file = new File("mercaderias.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String linea;
		while((linea=br.readLine())!=null)
		{
			String[] lineas = linea.split("/");
			GestionarMercaderias.AgregarMercaderia(new Mercaderia(Integer.parseInt(lineas[0]),lineas[1],lineas[2],Float.parseFloat(lineas[3]),Integer.parseInt(lineas[4])));
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.close();
		fr.close();
	}
	public static void CargarVentas() throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		File file = new File("ventas.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String linea;
		while((linea=br.readLine())!=null)
		{
			String[] lineas = linea.split("/");
			GestionarVentas.AgregarVenta(new Venta(Integer.parseInt(lineas[0]),Integer.parseInt(lineas[1]),lineas[2],Integer.parseInt(lineas[3]),Integer.parseInt(lineas[4]),Integer.parseInt(lineas[5])));
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.close();
		fr.close();
	}
	public static void CargarCobros() throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		File file = new File("cobros.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String linea;
		while((linea=br.readLine())!=null)
		{
			String[] lineas = linea.split("/");
			GestionarCaja.AgregarCobro(new Cobro(Integer.parseInt(lineas[0]),Float.parseFloat(lineas[1])));
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.close();
		fr.close();
	}
	public static void CargarPagos() throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		File file = new File("pagos.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String linea;
		while((linea=br.readLine())!=null)
		{
			String[] lineas = linea.split("/");
			GestionarCaja.AgregarPago(new Pago(Integer.parseInt(lineas[0]),Integer.parseInt(lineas[1]),lineas[2],Integer.parseInt(lineas[3])));
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.close();
		fr.close();
	}
	public static void CargarPedidos() throws NumberFormatException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		File file = new File("pedidos.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String linea;
		while((linea=br.readLine())!=null)
		{
			String[] lineas = linea.split("/");
			GestionarPedidos.AgregarPedido(new Pedido(Integer.parseInt(lineas[0]),Integer.parseInt(lineas[1]),Integer.parseInt(lineas[2]),lineas[3]));
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.close();
		fr.close();
	}
	
	public static void NonQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try{
		// Creo una nueva instancia de driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Obtengo la conexi�n
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/phpmyadmin?user=root");
		// Instanci�n la instrucci�n
		stmt = conn.createStatement();	
		// Cargo un set de resultados ejecutando la instrucci�n
		stmt.execute(text);
		// Cuando termin�, siempre cierro la conexi�n
		conn.close();
		}
		catch(SQLException e1){System.out.println(e1);}
	}
	public static Vector<Cliente> ClienteQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
		Vector<Cliente> cliente = new Vector<Cliente>();
		// Creo una nueva instancia de driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Obtengo la conexi�n
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/phpmyadmin?user=root");
		// Instanci�n la instrucci�n
		stmt = conn.createStatement();
		// Cargo un set de resultados ejecutando la instrucci�n
		rs = stmt.executeQuery(text);
		while(rs.next())
		{		
			cliente.add(new Cliente(rs.getInt("cod_cliente"),rs.getString("nombre"),rs.getString("apellido"),
					rs.getString("direccion"),rs.getInt("telefono")));
		}
		// Cuando termin�, siempre cierro la conexi�n
		conn.close();
		return cliente;
		} catch(SQLException ex){System.out.println(ex);}
		return new Vector<Cliente>();	
	}
	public static Vector<Mercaderia> MercaderiaQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<Mercaderia> mercaderias = new Vector<Mercaderia>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexi�n
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/phpmyadmin?user=root");
			// Instanci�n la instrucci�n
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucci�n
			rs = stmt.executeQuery(text);
			while(rs.next())
			{
				mercaderias.add(new Mercaderia(rs.getInt("cod_mercaderia"),rs.getString("nombre"),rs.getString("descripcion"),rs.getFloat("precio")
						,rs.getInt("stock")));
			}
			// Cuando termin�, siempre cierro la conexi�n
			conn.close();
			return mercaderias;
			} catch(SQLException ex){System.out.println(ex);}
			return new Vector<Mercaderia>();
	}
	
	public static Vector<Cobro> CobroQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<Cobro> cobros = new Vector<Cobro>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexi�n
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/phpmyadmin?user=root");
			// Instanci�n la instrucci�n
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucci�n
			rs = stmt.executeQuery(text);
			while(rs.next())
			{
				cobros.add(new Cobro(rs.getInt("cod_cobro"),rs.getFloat("precio")));
			}
			// Cuando termin�, siempre cierro la conexi�n
			conn.close();
			return cobros;
			} catch(SQLException ex){System.out.println(ex);}
			return new Vector<Cobro>();
	}
	
	public static Vector<Pago> PagoQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<Pago> pagos = new Vector<Pago>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexi�n
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/phpmyadmin?user=root");
			// Instanci�n la instrucci�n
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucci�n
			rs = stmt.executeQuery(text);
			while(rs.next())
			{
				pagos.add(new Pago(rs.getInt("cod_pago"),rs.getInt("cod_mercaderia"),rs.getString("nombre_proveedor"),rs.getFloat("precio")));
			}
			// Cuando termin�, siempre cierro la conexi�n
			conn.close();
			return pagos;
			} catch(SQLException ex){System.out.println(ex);}
			return new Vector<Pago>();
	}
	
	public static Vector<Venta> VentaQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
			Vector<Venta> ventas = new Vector<Venta>();
			// Creo una nueva instancia de driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Obtengo la conexi�n
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/phpmyadmin?user=root");
			// Instanci�n la instrucci�n
			stmt = conn.createStatement();
			// Cargo un set de resultados ejecutando la instrucci�n
			rs = stmt.executeQuery(text);
			while(rs.next())
			{
				ventas.add(new Venta(rs.getInt("cod_venta"),rs.getInt("cod_cliente"),rs.getString("fecha")
						,rs.getInt("cod_mercaderia"),rs.getInt("cantidad"),rs.getInt("cod_cobro")));
			}
			// Cuando termin�, siempre cierro la conexi�n
			conn.close();
			return ventas;
			} catch(SQLException ex){System.out.println(ex);}
			return new Vector<Venta>();
	}
	
	public static Vector<Pedido> PedidoQuery(String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try {
		Vector<Pedido> pedido = new Vector<Pedido>();
		// Creo una nueva instancia de driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Obtengo la conexi�n
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/phpmyadmin?user=root");
		// Instanci�n la instrucci�n
		stmt = conn.createStatement();
		// Cargo un set de resultados ejecutando la instrucci�n
		rs = stmt.executeQuery(text);
		while(rs.next())
		{		
			pedido.add(new Pedido(rs.getInt("cod_pedido"),rs.getInt("cod_cliente"),rs.getInt("cod_mercaderia"),
					rs.getString("dia_entrega")));
		}
		// Cuando termin�, siempre cierro la conexi�n
		conn.close();
		return pedido;
		} catch(SQLException ex){System.out.println(ex);}
		return new Vector<Pedido>();	
	}
	
}