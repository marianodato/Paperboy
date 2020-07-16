package Gestion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import Clases.Cliente;
import General.BaseDatos;

public class GestionarClientes
{
	public static Vector<Cliente> clientes = new Vector<Cliente>();
	public static void AgregarCliente(Cliente cliente) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			BaseDatos.NonQuery("INSERT INTO Cliente (cod_cliente,nombre,apellido,telefono,direccion) VALUES ("+cliente.getDni()+",'"+cliente.getNombre()+
					"','"+cliente.getApellido()+"',"+cliente.getTelefono()+",'"+cliente.getDireccion()+"')");
			clientes.add(cliente);
	}
	public static Vector<Cliente> BuscarCliente(String value,String campo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{		
			String string = "SELECT * FROM Cliente WHERE "+campo+" LIKE '"+value+"'";
			System.out.println(string);
			return BaseDatos.ClienteQuery(string);	
	}
	public static void EliminarCliente(int dni) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			BaseDatos.NonQuery("DELETE FROM Cliente WHERE cod_cliente="+dni);
			for(int i=0;i<clientes.size();i++) if(clientes.get(i).getDni() == dni) clientes.remove(i);
	}
	public static void ModificarCliente(Cliente cliente) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			BaseDatos.NonQuery("UPDATE Cliente SET nombre='"+cliente.getNombre()+"',apellido='"+cliente.getApellido()+"',telefono="+cliente.getTelefono()
					+",direccion='"+cliente.getDireccion()+"' WHERE cod_cliente="+cliente.getDni());
			for(int i=0;i<clientes.size();i++)
				{
					if(clientes.get(i).getDni() == cliente.getDni())
					{
						clientes.get(i).setNombre(cliente.getNombre());
						clientes.get(i).setApellido(cliente.getApellido());
						clientes.get(i).setDireccion(cliente.getDireccion());
						clientes.get(i).setTelefono(cliente.getTelefono());
					}
				}
	}
	public static void CargarClientes() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		clientes = BaseDatos.ClienteQuery("SELECT * FROM Cliente");
	}
	public static void BackUp(Cliente cliente) throws IOException
	{
		FileWriter file = new FileWriter("clientes.txt",true);
		PrintWriter pw = new PrintWriter(file);
		pw.println(cliente.getDni()+"/"+cliente.getNombre()+"/"+cliente.getApellido()+"/"+cliente.getDireccion()+"/"+cliente.getTelefono());
		file.close();
	}
}
