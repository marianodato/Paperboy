package Gestion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import Clases.Cliente;
import Clases.Mercaderia;
import General.BaseDatos;

public class GestionarMercaderias 
{
	public static Vector<Mercaderia> mercaderias= new Vector<Mercaderia>();
	public static void AgregarMercaderia(Mercaderia mercaderia)  throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{				
			BaseDatos.NonQuery("INSERT INTO Mercaderia (cod_mercaderia,nombre,descripcion,precio,stock) VALUES ("+mercaderia.getCodigo()+
					",'"+mercaderia.getNombre()+"','"+mercaderia.getDescripcion()+"',"+mercaderia.getPrecio()+",'"+mercaderia.getStock()+"')");
			mercaderias.add(new Mercaderia(mercaderia.getCodigo(),mercaderia.getNombre(),mercaderia.getDescripcion(),mercaderia.getPrecio(),mercaderia.getStock()));
	}
	public static Vector<Mercaderia> BuscarMercaderia(String value,String campo) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{				
		String string = "SELECT * FROM Mercaderia WHERE "+campo+" LIKE '"+value+"'";
		System.out.println(string);
		return BaseDatos.MercaderiaQuery(string);		
	}
	public static void ModificarStock(int id, int stock) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{				
			BaseDatos.NonQuery("UPDATE Mercaderia SET stock="+stock+" WHERE cod_mercaderia="+id);
	}
	public static void CargarMercaderia() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{				
			mercaderias = BaseDatos.MercaderiaQuery("SELECT * FROM Mercaderia");		
	}
	public static void BackUp(Mercaderia mercaderia) throws IOException
	{
		FileWriter file = new FileWriter("mercaderias.txt",true);
		PrintWriter pw = new PrintWriter(file);
		pw.println(mercaderia.getCodigo()+"/"+mercaderia.getNombre()+"/"+mercaderia.getDescripcion()+"/"+mercaderia.getPrecio()+"/"+mercaderia.getStock());
		file.close();
	}
}
