package General;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Clases.Cliente;
import Clases.Cobro;
import Clases.Impresora;
import Clases.Mercaderia;
import Clases.Pago;
import Clases.Pedido;
import Clases.Venta;
import Gestion.GestionarCaja;
import Gestion.GestionarClientes;
import Gestion.GestionarMercaderias;
import Gestion.GestionarPedidos;
import Gestion.GestionarVentas;

public class Main {

	static JFrame windowPrincipal = new JFrame("PaperBoy");
	static JFrame windowCliente;
	static JFrame windowCaja;
	static JFrame windowMercaderias;
	static JFrame windowVentas;
	static JFrame windowPedidos;
	public static boolean connection;
	
	
public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, IOException
{
	connection = BaseDatos.Check();
	if(connection)
		{
			BaseDatos.Cargar();
			VentanaError("             Datos Cargados!");
			LoadAll();
		}
	else VentanaError("No hay conexion con la base de datos!");
	
	//Ventana Principal
	windowPrincipal.setSize(315,590);
	windowPrincipal.setLayout(null);
	
	//Botones
	JButton bCliente = Boton("Gestionar Clientes",50,50,200,50,windowPrincipal);
	JButton bMercaderias = Boton("Gestionar Mercaderias",50,150,200,50,windowPrincipal);
	JButton bVentas = Boton("Gestionar Ventas",50,250,200,50,windowPrincipal);
	JButton bPedidos = Boton("Gestionar Pedidos",50,350,200,50,windowPrincipal);
	JButton bCaja = Boton("Gestionar Caja",50,450,200,50,windowPrincipal);
	
	//Ventana Cliente
	bCliente.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			windowPrincipal.setVisible(false);
			try {
				WCliente();
			} catch (InstantiationException e1) {e1.printStackTrace();
			} catch (IllegalAccessException e1) {e1.printStackTrace();
			} catch (ClassNotFoundException e1) {e1.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ConnectException e3) {
			}
			
	}});
	
	//Ventana Caja
	bCaja.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			windowPrincipal.setVisible(false);
			try {
				WCaja();
			} catch (InstantiationException e1) {
				 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				 
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				 
				e1.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ConnectException e3) {
				
			}
	}});
	
	//Ventana Mercaderia
	bMercaderias.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			windowPrincipal.setVisible(false);
			try {
				WMercaderias();
			} catch (InstantiationException e1) { 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) { 
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) { 
				e1.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ConnectException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
	}});
	
	//Ventana Ventas
		bVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowPrincipal.setVisible(false);
				try {
					WVentas();
				} catch (InstantiationException e1) {
					 
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					 
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					 
					e1.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ConnectException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
	}});
		
	//Ventana Pedidos
		bPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowPrincipal.setVisible(false);
				try {
					WPedidos();
				} catch (InstantiationException e1) {
					 
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					 
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					 
					e1.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ConnectException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
	}});

	// Mostrar la ventana principal
	windowPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	windowPrincipal.setVisible(true);
	
}

public static void LoadAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException
{
	GestionarClientes.CargarClientes();
	GestionarMercaderias.CargarMercaderia();
	GestionarCaja.CargarCobros();
	GestionarCaja.CargarPagos();
	GestionarVentas.CargarVentas();
	GestionarPedidos.Cargar();
}

//Cuando se trata de una ventana
public static JButton Boton (String nombre, int x, int y, int ancho, int alto, JFrame window)
{
	JButton button = new JButton(nombre);
	button.setBounds(new Rectangle(x, y, ancho, alto));
	window.getContentPane().add(button);
	return button;
}
//Cuando se trata de un dialogo
public static JButton Boton (String nombre, int x, int y, int ancho, int alto, JDialog window)
{
	JButton button = new JButton(nombre);
	button.setBounds(new Rectangle(x, y, ancho, alto));
	window.getContentPane().add(button);
	return button;
}

public static JLabel Label (String nombre, int x, int y, int ancho, int alto, JDialog dialog)
{
	JLabel label = new JLabel(nombre);
    label.setBounds(x, y, ancho, alto);
    dialog.add(label);
	return label;
}

public static JTextField TextField (int x, int y, int ancho, int alto, JDialog dialog)
{
	JTextField text = new JTextField();
    text.setBounds(x, y, ancho, alto);
    dialog.add(text);
	return text;
}

public static JTextField TextField (int x, int y, int ancho, int alto, JDialog dialog, String texto)
{
	JTextField text = new JTextField(texto);
    text.setBounds(x, y, ancho, alto);
    dialog.add(text);
	return text;
}
static void VentanaError(String text)
{
	final JDialog ventanaError = new JDialog(windowCliente,"Error");
	ventanaError.setSize(350,150);
	ventanaError.setLayout(null);
	ventanaError.setVisible(true);
	Label(text,20,20,310,30,ventanaError);
	
	JButton Aceptar = Boton("Aceptar",100,60,80,35,ventanaError);
	//Modificar
			Aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						ventanaError.setVisible(false);
				
			}});
}
public static void BuscarCliente()
{
	final JDialog windowDNI = new JDialog(windowCliente,"Ingresar DNI");
	windowDNI.setSize(350,180);
	windowDNI.setLayout(null);
	windowDNI.setVisible(true);
	
	Label("Ingrese el DNI:",20,30,200,30,windowDNI);
	
	JTextField TDNI = TextField(120,30,200,30,windowDNI);
	
	JButton Buscar = Boton("Buscar",120,80,100,50,windowDNI);
	//Buscar
			Buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//BUSCAR!!!!
					windowDNI.setVisible(false);
			}});
}

public static void WCliente() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ConnectException
{
	windowCliente = new JFrame("Gestionar Clientes");
	windowCliente.setSize(275,500);
	windowCliente.setLayout(null);
	windowCliente.setVisible(true);
	JButton bClienteAgregar = Boton("Agregar Cliente",50,50,150,50,windowCliente);
	JButton bClienteBuscar = Boton("Buscar Cliente",50,150,150,50,windowCliente);
	JButton bClienteModificar = Boton("Modificar Cliente",50,250,150,50,windowCliente);
	JButton bClienteEliminar = Boton("Eliminar Cliente",50,350,150,50,windowCliente);
	
	//Agregar Cliente
		bClienteAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WAgregarCliente();
				VentanaError("Los cambios seran backupeados");
		}});
	//Buscar Cliente
			bClienteBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(connection)WBuscarCliente();
						else VentanaError("No hay conexion con la Base de Datos!");
					} catch (InstantiationException e1) { 
						e1.printStackTrace();
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) { 
						e1.printStackTrace();
					}
			}});
	//Modificar Cliente
			bClienteModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(connection)WModificarCliente();
					else VentanaError("No hay conexion con la Base de Datos!");
			}});			
	//Eliminar Cliente
			bClienteEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(connection)WEliminarCliente();
					else VentanaError("No hay conexion con la Base de Datos!");
			}});
	
			
	// Hacer que al cerrarse la secundaria con la x de arriba a la derecha, se muestre la primaria
	windowCliente.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowCliente.setVisible(false);
		}
	
		public void windowClosed(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowCliente.setVisible(false);
		}
	});	
	}
	public static void WAgregarCliente()
	{
		JDialog windowAgregarCliente = new JDialog(windowCliente,"Agregar Cliente");
		windowAgregarCliente.setSize(500,470);
		windowAgregarCliente.setLayout(null);
		windowAgregarCliente.setVisible(true);
		
		Label("DNI:",50,30,100,30,windowAgregarCliente);
		Label("Nombre:",50,100,100,30,windowAgregarCliente);
		Label("Apellido:",50,170,100,30,windowAgregarCliente);
		Label("Direccion:",50,240,100,30,windowAgregarCliente);
		Label("Telefono:",50,320,100,30,windowAgregarCliente);
		
		final JTextField DNI = TextField(150,30,200,30,windowAgregarCliente);
		final JTextField nombre = TextField(150,100,200,30,windowAgregarCliente);
		final JTextField apellido = TextField(150,170,200,30,windowAgregarCliente);
		final JTextField direccion = TextField(150,240,200,30,windowAgregarCliente);
		final JTextField telefono = TextField(150,320,200,30,windowAgregarCliente);
		
		JButton Agregar = Boton("Agregar",350,360,100,50,windowAgregarCliente);
		//Agregar
				Agregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
	
						boolean encontrado = false;
						try {
							if(connection)for(int i=0;i<GestionarClientes.clientes.size();i++)if(Integer.parseInt(DNI.getText())==GestionarClientes.clientes.get(i).getDni())encontrado = true;
							if(!encontrado)
							{
								if(connection)GestionarClientes.AgregarCliente(new Cliente(Integer.parseInt(DNI.getText()),nombre.getText(),apellido.getText(),direccion.getText(),Integer.parseInt(telefono.getText())));			
								else GestionarClientes.BackUp(new Cliente(Integer.parseInt(DNI.getText()),nombre.getText(),apellido.getText(),direccion.getText(),Integer.parseInt(telefono.getText())));
								windowPrincipal.setVisible(true);
								windowCliente.setVisible(false);
							}else {VentanaError("DNI existente!");}
	
						} catch (ClassNotFoundException e1) { 
							e1.printStackTrace();
						} catch (NumberFormatException e2) { 
							VentanaError("	        Campo Inv�lido!");
						} catch (InstantiationException e3) { 
							e3.printStackTrace();
						} catch (IllegalAccessException e4) { 
							e4.printStackTrace();} 
						catch (SQLException e5) {} catch (IOException e5) {
							e5.printStackTrace();
						}
				}});
		
	}
	public static void WBuscarCliente() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowBuscarCliente = new JDialog(windowCliente,"Buscar Cliente");
		windowBuscarCliente.setSize(280,200);
		windowBuscarCliente.setLayout(null);
		windowBuscarCliente.setVisible(true);
		
		final JTextField value = TextField(25,30,100,30,windowBuscarCliente);
		String[] param = {"cod_cliente","nombre","apellido","telefono","direccion"};
		final JComboBox combo = new JComboBox(param);
		combo.setLocation(145,30);
		combo.setSize(100,30);
		combo.setSelectedIndex(0);
		combo.setVisible(true);
		windowBuscarCliente.getContentPane().add(combo);
		
		JButton Buscar = Boton("Buscar",100,80,80,50,windowBuscarCliente);
		//Buscar
				Buscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					Vector<Cliente> clientes;
					try {
						clientes = GestionarClientes.BuscarCliente(value.getText(),(String)combo.getSelectedItem());
						if(clientes.size() == 0)VentanaError("Cliente no encontrado!");
						else {windowBuscarCliente.setVisible(false);MostrarCliente(clientes,1);}
					} catch (NumberFormatException e1) { 
						VentanaError("	        Campo Inv�lido!");
					} catch (InstantiationException e1) { 
						e1.printStackTrace();
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) { 
						e1.printStackTrace();
					} catch (SQLException e1) {	 
						e1.printStackTrace();
					}	
				}});
	}
	public static void MostrarCliente(final Vector<Cliente> clientes, int num) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowMostrarCliente = new JDialog(windowCliente,"Mostrar Cliente");
	
		windowMostrarCliente.setSize(775,120+(50*clientes.size())+80);
		windowMostrarCliente.setLayout(null);
		windowMostrarCliente.setVisible(true);
		
		Label("DNI",50,15,100,30,windowMostrarCliente);
		Label("Nombre",150,15,100,30,windowMostrarCliente);
		Label("Apellido",300,15,100,30,windowMostrarCliente);
		Label("Direccion",450,15,100,30,windowMostrarCliente);
		Label("Telefono",600,15,100,30,windowMostrarCliente);
		for(int i=0;i<775;i++)Label("-",i,50,800,30,windowMostrarCliente);
		for(int i=0;i<775;i++)Label("-",i,(120+(50*clientes.size())-60),800,30,windowMostrarCliente);
		
			for(int i=0;i<clientes.size();i++)
			{
				Label(Integer.toString(clientes.get(i).getDni()),50,80+(30*i),200,30,windowMostrarCliente);
				Label(clientes.get(i).getNombre(),150,80+(30*i),200,30,windowMostrarCliente);
				Label(clientes.get(i).getApellido(),300,80+(30*i),200,30,windowMostrarCliente);
				Label(clientes.get(i).getDireccion(),450,80+(30*i),200,30,windowMostrarCliente);
				Label(Integer.toString(clientes.get(i).getTelefono()),600,80+(30*i),200,30,windowMostrarCliente);
			}		
			if (num==3)
			{
				final JTextField dni = TextField(20,(120+(50*clientes.size())-30),100,30,windowMostrarCliente,"Dni");
				final JTextField nombre = TextField(150,(120+(50*clientes.size())-30),100,30,windowMostrarCliente,"Nombre");
				final JTextField apellido = TextField(280,(120+(50*clientes.size())-30),100,30,windowMostrarCliente,"Apellido");
				final JTextField direccion = TextField(410,(120+(50*clientes.size())-30),100,30,windowMostrarCliente,"Direccion");
				final JTextField telefono = TextField(540,(120+(50*clientes.size())-30),100,30,windowMostrarCliente,"Telefono");
				
				JButton Aceptar = Boton("Modificar",650,(120+(50*clientes.size())-20),100,50,windowMostrarCliente);
				Aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					try {
						for(int i=0;i<=GestionarClientes.clientes.size();i++)
						{
							if(Integer.parseInt((dni.getText())) == clientes.get(i).getDni())
								GestionarClientes.ModificarCliente(new Cliente(Integer.parseInt(dni.getText()),nombre.getText(),apellido.getText(),direccion.getText(),Integer.parseInt(telefono.getText())));
							else if(i+1 == GestionarMercaderias.mercaderias.size()) VentanaError("     Mercaderia no encontrada!");
						}
					} catch (InstantiationException e1) { 
						e1.printStackTrace();
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) { 
						e1.printStackTrace();
					} catch (NumberFormatException e1) { 
						VentanaError("	        Campo Inv�lido!");
					} catch (SQLException e1) {	 
						e1.printStackTrace();
					}
					windowMostrarCliente.setVisible(false);
				}});			
			}
		
		
		if(num==1)
		{
			JButton Aceptar = Boton("Aceptar",650,(120+(50*clientes.size())-20),100,50,windowMostrarCliente);
			Aceptar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				windowMostrarCliente.setVisible(false);
			}});
				
		}else if (num==2)
		{
			final JTextField dni = TextField(330,(120+(50*clientes.size())-30),100,30,windowMostrarCliente,"Dni");
			
			JButton Aceptar = Boton("Eliminar",650,(120+(50*clientes.size())-20),100,50,windowMostrarCliente);
			Aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				try {
					for(int i=0;i<=GestionarClientes.clientes.size();i++)
					{
						if(Integer.parseInt((dni.getText())) == clientes.get(i).getDni())
							GestionarClientes.EliminarCliente(Integer.parseInt(dni.getText()));
						else if(i+1 == GestionarMercaderias.mercaderias.size()) VentanaError("     Mercaderia no encontrada!");
					}
				} catch (InstantiationException e1) { 
					e1.printStackTrace();
				} catch (IllegalAccessException e1) { 
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) { 
					e1.printStackTrace();
				} catch (SQLException e1) {	 
					e1.printStackTrace();
				}
				windowMostrarCliente.setVisible(false);
			}});		
		} 	
	}
	public static void WModificarCliente()
	{
		final JDialog windowModificarCliente = new JDialog(windowCliente,"Ingresar DNI");
		windowModificarCliente.setSize(280,200);
		windowModificarCliente.setLayout(null);
		windowModificarCliente.setVisible(true);
		
		final JTextField value = TextField(25,30,100,30,windowModificarCliente);
		String[] param = {"cod_cliente","nombre","apellido","telefono","direccion"};
		final JComboBox combo = new JComboBox(param);
		combo.setLocation(145,30);
		combo.setSize(100,30);
		combo.setSelectedIndex(0);
		combo.setVisible(true);
		windowModificarCliente.getContentPane().add(combo);
		
		JButton Buscar = Boton("Buscar",100,80,80,50,windowModificarCliente);
		//Buscar
		Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Vector<Cliente> clientes;
			try {
				clientes = GestionarClientes.BuscarCliente(value.getText(),(String)combo.getSelectedItem());
				if(clientes.size() == 0)VentanaError("Cliente no encontrado!");
				else {windowModificarCliente.setVisible(false);MostrarCliente(clientes,3);}
			} catch (NumberFormatException e1) { 
				VentanaError("	        Campo Inv�lido!");
			} catch (InstantiationException e1) { 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) { 
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) { 
				e1.printStackTrace();
			} catch (SQLException e1) { 
				e1.printStackTrace();
			}	
		}});
	}
	public static void WEliminarCliente()
	{
		final JDialog windowEliminarCliente = new JDialog(windowCliente,"Ingresar DNI");
		windowEliminarCliente.setSize(280,200);
		windowEliminarCliente.setLayout(null);
		windowEliminarCliente.setVisible(true);
		
		final JTextField value = TextField(25,30,100,30,windowEliminarCliente);
		String[] param = {"cod_cliente","nombre","apellido","telefono","direccion"};
		final JComboBox combo = new JComboBox(param);
		combo.setLocation(145,30);
		combo.setSize(100,30);
		combo.setSelectedIndex(0);
		combo.setVisible(true);
		windowEliminarCliente.getContentPane().add(combo);
		
		JButton Buscar = Boton("Buscar",100,80,80,50,windowEliminarCliente);
		//Buscar
		Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Vector<Cliente> clientes;
			try {
				clientes = GestionarClientes.BuscarCliente(value.getText(),(String)combo.getSelectedItem());
				if(clientes.size() == 0)VentanaError("Cliente no encontrado!");
				else {windowEliminarCliente.setVisible(false);MostrarCliente(clientes,2);}
			} catch (NumberFormatException e1) { 
				VentanaError("	        Campo Inv�lido!");
			} catch (InstantiationException e1) { 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) { 
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) { 
				e1.printStackTrace();
			} catch (SQLException e1) { 
				e1.printStackTrace();
			}	
		}});	
	}

public static void WMercaderias() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ConnectException
{
	 
	windowMercaderias = new JFrame("Gestionar Mercaderias");
	windowMercaderias.setSize(275,400);
	windowMercaderias.setLayout(null);
	windowMercaderias.setVisible(true);
	JButton bAgregarMercaderia = Boton("Agregar Mercaderia",50,50,150,50,windowMercaderias);
	JButton bModificarStock = Boton("Modificar Stock",50,150,150,50,windowMercaderias);
	JButton bBuscarMercaderia = Boton("Buscar Mercaderia",50,250,150,50,windowMercaderias);
	
	//Agregar Mercaderia
	bAgregarMercaderia.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			WAgregarMercaderia();
	}});
	//Modificar Stock
	bModificarStock.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			WModificarMercaderia();
	}});
	//Buscar Mercaderia
	bBuscarMercaderia.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		try {
			WBuscarMercaderia();
		} catch (InstantiationException e1) {e1.printStackTrace();
		} catch (IllegalAccessException e1) {e1.printStackTrace();
		} catch (ClassNotFoundException e1) {e1.printStackTrace();} 		
	}});
	
	// Hacer que al cerrarse la secundaria con la x de arriba a la derecha, se muestre la primaria
	windowMercaderias.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowMercaderias.setVisible(false);
		}
	
		public void windowClosed(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowMercaderias.setVisible(false);
		}
	});	
}
	public static void WAgregarMercaderia()
	{
		JDialog windowAgregarMercaderia = new JDialog(windowMercaderias,"Agregar Mercaderia");
		windowAgregarMercaderia.setSize(500,510);
		windowAgregarMercaderia.setLayout(null);
		windowAgregarMercaderia.setVisible(true);
		
		Label("Codigo:",50,30,100,30,windowAgregarMercaderia);
		Label("Nombre:",50,100,100,30,windowAgregarMercaderia);
		Label("Nombre Proov.:",50,170,100,30,windowAgregarMercaderia);
		Label("Descripcion:",50,240,100,30,windowAgregarMercaderia);
		Label("Precio Unit.:",50,310,310,30,windowAgregarMercaderia);
		Label("Stock:",50,380,100,30,windowAgregarMercaderia);
		
		final JTextField codigo = TextField(150,30,200,30,windowAgregarMercaderia);
		final JTextField nombre = TextField(150,100,200,30,windowAgregarMercaderia);
		final JTextField nombreprov = TextField(150,170,200,30,windowAgregarMercaderia);
		final JTextField descripcion = TextField(150,240,200,30,windowAgregarMercaderia);
		final JTextField precio = TextField(150,310,200,30,windowAgregarMercaderia);
		final JTextField stock = TextField(150,380,200,30,windowAgregarMercaderia);

		JButton Agregar = Boton("Agregar",370,410,100,50,windowAgregarMercaderia);
		//Agregar
				Agregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Pago pago = new Pago(Integer.parseInt(codigo.getText()),nombreprov.getText(),Integer.parseInt(precio.getText())*Integer.parseInt(stock.getText()));
							if(connection)GestionarCaja.AgregarPago(pago);
							else GestionarCaja.BackUp(pago);
							if(connection)GestionarMercaderias.AgregarMercaderia(new Mercaderia(Integer.parseInt(codigo.getText()),nombre.getText(),descripcion.getText(),Float.parseFloat(precio.getText()),Integer.parseInt(stock.getText())));			
							else GestionarMercaderias.BackUp(new Mercaderia(Integer.parseInt(codigo.getText()),nombre.getText(),descripcion.getText(),Float.parseFloat(precio.getText()),Integer.parseInt(stock.getText())));
							windowPrincipal.setVisible(true);
							windowMercaderias.setVisible(false);
						} catch (ClassNotFoundException e1) { 
							e1.printStackTrace();
						} catch (NumberFormatException e2) { 
							VentanaError("	        Campo Inv�lido!");
						} catch (InstantiationException e3) { 
							e3.printStackTrace();
						} catch (IllegalAccessException e4) { 
							e4.printStackTrace();} 
						catch (SQLException e5) {	 
							e5.printStackTrace();} catch (IOException e7) {
							// TODO Auto-generated catch block
							e7.printStackTrace();
						}
				}});
		
	}
	public static void WModificarMercaderia()
	{
		final JDialog windowModificarStock = new JDialog(windowMercaderias,"Ingresar el codigo");
		windowModificarStock.setSize(280,200);
		windowModificarStock.setLayout(null);
		windowModificarStock.setVisible(true);
		
		final JTextField value = TextField(25,30,100,30,windowModificarStock);
		String[] param = {"cod_mercaderia","nombre","descripcion","precio","stock"};
		final JComboBox combo = new JComboBox(param);
		combo.setLocation(145,30);
		combo.setSize(100,30);
		combo.setSelectedIndex(0);
		combo.setVisible(true);
		windowModificarStock.getContentPane().add(combo);
		
		JButton Buscar = Boton("Buscar",100,80,80,50,windowModificarStock);
		//Buscar
		Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Vector<Mercaderia> mercaderias;
			try {
				mercaderias = GestionarMercaderias.BuscarMercaderia(value.getText(),(String)combo.getSelectedItem());
				if(mercaderias.size() == 0)VentanaError("Mercaderia no encontrada!");
				else {windowModificarStock.setVisible(false);MostrarMercaderia(mercaderias,3);}
			} catch (NumberFormatException e1) { 
				VentanaError("	        Campo Inv�lido!");
			} catch (InstantiationException e1) { 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) { 
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) { 
				e1.printStackTrace();
			}	
		}});
	}
	public static void MostrarMercaderia(final Vector<Mercaderia> mercaderias, int num) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowMostrarMercaderia = new JDialog(windowMercaderias,"Mostrar Mercaderia");
	
		windowMostrarMercaderia.setSize(775,120+(50*mercaderias.size())+80);
		windowMostrarMercaderia.setLayout(null);
		windowMostrarMercaderia.setVisible(true);
		
		Label("Codigo",50,15,100,30,windowMostrarMercaderia);
		Label("Nombre",150,15,100,30,windowMostrarMercaderia);
		Label("Descripcion",300,15,100,30,windowMostrarMercaderia);
		Label("Precio",450,15,100,30,windowMostrarMercaderia);
		Label("Stock",600,15,100,30,windowMostrarMercaderia);
		for(int i=0;i<775;i++)Label("-",i,50,800,30,windowMostrarMercaderia);
		for(int i=0;i<775;i++)Label("-",i,(120+(50*mercaderias.size())-60),800,30,windowMostrarMercaderia);
		
			for(int i=0;i<mercaderias.size();i++)
			{
				Label(Integer.toString(mercaderias.get(i).getCodigo()),50,80+(30*i),200,30,windowMostrarMercaderia);
				Label(mercaderias.get(i).getNombre(),150,80+(30*i),200,30,windowMostrarMercaderia);
				Label(mercaderias.get(i).getDescripcion(),300,80+(30*i),200,30,windowMostrarMercaderia);
				Label(Float.toString(mercaderias.get(i).getPrecio()),450,80+(30*i),200,30,windowMostrarMercaderia);
				Label(Integer.toString(mercaderias.get(i).getStock()),600,80+(30*i),200,30,windowMostrarMercaderia);
			}		
			if (num==3)
			{
				final JTextField cod = TextField(200,(120+(50*mercaderias.size())-30),100,30,windowMostrarMercaderia,"Codigo");
				final JTextField stock = TextField(330,(120+(50*mercaderias.size())-30),100,30,windowMostrarMercaderia,"Nuevo Stock");
				
				JButton Aceptar = Boton("Modificar",650,(120+(50*mercaderias.size())-20),100,50,windowMostrarMercaderia);
				Aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					try {
						for(int i=0;i<=GestionarMercaderias.mercaderias.size();i++)
						{
							if(Integer.parseInt((cod.getText())) == mercaderias.get(i).getCodigo())
								GestionarMercaderias.ModificarStock(Integer.parseInt(cod.getText()),Integer.parseInt(stock.getText()));

							else if(i+1 == GestionarMercaderias.mercaderias.size()) VentanaError("     Mercaderia no encontrada!");
						}
					} catch (InstantiationException e1) { 
						e1.printStackTrace();
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) { 
						e1.printStackTrace();
					} catch (NumberFormatException e1) { 
						VentanaError("	        Campo Inv�lido!");
					}
					windowMostrarMercaderia.setVisible(false);
				}});			
			}
		
		if(num==1)
		{
			JButton Aceptar = Boton("Aceptar",650,(120+(50*mercaderias.size())-20),100,50,windowMostrarMercaderia);
			Aceptar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				windowMostrarMercaderia.setVisible(false);
			}});
		}		
	}
	static void WBuscarMercaderia() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowBuscarMercaderia = new JDialog(windowMercaderias,"Buscar Mercaderia");
		windowBuscarMercaderia.setSize(280,200);
		windowBuscarMercaderia.setLayout(null);
		windowBuscarMercaderia.setVisible(true);
		
		final JTextField value = TextField(25,30,100,30,windowBuscarMercaderia);
		String[] param = {"cod_mercaderia","nombre","descripcion","precio","stock"};
		final JComboBox combo = new JComboBox(param);
		combo.setLocation(145,30);
		combo.setSize(100,30);
		combo.setSelectedIndex(0);
		combo.setVisible(true);
		windowBuscarMercaderia.getContentPane().add(combo);
		
		JButton Buscar = Boton("Buscar",100,80,80,50,windowBuscarMercaderia);
		//Buscar
		Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Vector<Mercaderia> mercaderias;
			try {
				mercaderias = GestionarMercaderias.BuscarMercaderia(value.getText(),(String)combo.getSelectedItem());
				if(mercaderias.size() == 0)VentanaError("Mercaderia no encontrada!");
				else {windowBuscarMercaderia.setVisible(false);MostrarMercaderia(mercaderias,1);}
					} catch (NumberFormatException e1) { 
						VentanaError("	        Campo Inv�lido!");
					} catch (InstantiationException e1) { 
						e1.printStackTrace();
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) { 
						e1.printStackTrace();
					}	
				}});
	}

public static void WCaja() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ConnectException
{
	 
	windowCaja = new JFrame("Gestionar Caja");
	windowCaja.setSize(275,500);
	windowCaja.setLayout(null);
	windowCaja.setVisible(true);
	JButton bBuscarPago = Boton("Buscar Pago",50,50,150,50,windowCaja);
	JButton bBuscarCobro = Boton("Buscar Cobro",50,150,150,50,windowCaja);
	JButton bImprimirPago = Boton("Imprimir Pago",50,250,150,50,windowCaja);
	JButton bImprimirCobro = Boton("Imprimir Cobro",50,350,150,50,windowCaja);
	
	//Buscar Pago
	bBuscarPago.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				WBuscarPago(1);
			} catch (InstantiationException e1) { 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) { 
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) { 
				e1.printStackTrace();
			}
	}});
	
	//Buscar Cobro
		bBuscarCobro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					WBuscarCobro(2);
				} catch (InstantiationException e1) { 
					e1.printStackTrace();
				} catch (IllegalAccessException e1) { 
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) { 
					e1.printStackTrace();
				}
		}});
		
	//Imprimir Pago
	bImprimirPago.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				WBuscarPago(2);
			} catch (InstantiationException e1) { 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) { 
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) { 
				e1.printStackTrace();
			}
	}});
	
	
	//Imprimir Cobro
	bImprimirCobro.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				WBuscarCobro(2);
			} catch (InstantiationException e1) { 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) { 
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) { 
				e1.printStackTrace();
			}
	}});
	
	// Hacer que al cerrarse la secundaria con la x de arriba a la derecha, se muestre la primaria
	windowCaja.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowCaja.setVisible(false);
		}
	
		public void windowClosed(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowCaja.setVisible(false);
		}
	});	
}
	public static void WBuscarCobro(final int opc) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowBuscarCobro = new JDialog(windowCaja,"Buscar Cobro");
		windowBuscarCobro.setSize(280,200);
		windowBuscarCobro.setLayout(null);
		windowBuscarCobro.setVisible(true);
		
		final JTextField value = TextField(25,30,100,30,windowBuscarCobro);
		String[] param = {"cod_cobro","precio"};
		final JComboBox combo = new JComboBox(param);
		combo.setLocation(145,30);
		combo.setSize(100,30);
		combo.setSelectedIndex(0);
		combo.setVisible(true);
		windowBuscarCobro.getContentPane().add(combo);
		
		JButton Buscar = Boton("Buscar",100,80,80,50,windowBuscarCobro);
		//Buscar
				Buscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					Vector<Cobro> cobros;
					try {
						cobros = GestionarCaja.BuscarCobro(value.getText(),(String)combo.getSelectedItem());
						if(cobros.size() == 0)VentanaError("Cobro no encontrado!");
						else {windowBuscarCobro.setVisible(false);MostrarCobros(cobros,opc);}
					} catch (NumberFormatException e1) { 
						VentanaError("	        Campo Inv�lido!");
					} catch (InstantiationException e1) { 
						e1.printStackTrace();
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) { 
						e1.printStackTrace();
					}	
				}});
	}
	public static void WBuscarPago(final int opc) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowBuscarPago = new JDialog(windowCaja,"Buscar Pago");
		windowBuscarPago.setSize(280,200);
		windowBuscarPago.setLayout(null);
		windowBuscarPago.setVisible(true);
		
		final JTextField value = TextField(25,30,100,30,windowBuscarPago);
		String[] param = {"cod_pago","cod_mercaderia","nombre_proveedor","precio"};
		final JComboBox combo = new JComboBox(param);
		combo.setLocation(135,30);
		combo.setSize(120,30);
		combo.setSelectedIndex(0);
		combo.setVisible(true);
		windowBuscarPago.getContentPane().add(combo);
		
		JButton Buscar = Boton("Buscar",100,80,80,50,windowBuscarPago);
		//Buscar
				Buscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					Vector<Pago> pagos;
					try {
						pagos = GestionarCaja.BuscarPago(value.getText(),(String)combo.getSelectedItem());
						if(pagos.size() == 0)VentanaError("Pago no encontrado!");
						else {windowBuscarPago.setVisible(false);MostrarPagos(pagos,opc);}
					} catch (NumberFormatException e1) { 
						VentanaError("	        Campo Inv�lido!");
					} catch (InstantiationException e1) { 
						e1.printStackTrace();
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) { 
						e1.printStackTrace();
					}
				}});
	}
	public static void MostrarCobros(final Vector<Cobro> cobros, int opc) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowMostrarCobro = new JDialog(windowCaja,"Mostrar Cobro");
	
		windowMostrarCobro.setSize(300,120+(50*cobros.size())+80);
		windowMostrarCobro.setLayout(null);
		windowMostrarCobro.setVisible(true);
		
		Label("Codigo",50,15,100,30,windowMostrarCobro);
		Label("Precio",150,15,100,30,windowMostrarCobro);
		for(int i=0;i<290;i++)Label("-",i,50,800,30,windowMostrarCobro);
		for(int i=0;i<290;i++)Label("-",i,(120+(50*cobros.size())-60),800,30,windowMostrarCobro);
		
			for(int i=0;i<cobros.size();i++)
			{
				Label(Integer.toString(cobros.get(i).getCodigo()),50,80+(30*i),200,30,windowMostrarCobro);
				Label(Float.toString(cobros.get(i).getPrecio()),150,80+(30*i),200,30,windowMostrarCobro);
			}		
			if(opc == 1)
			{
			
			JButton Aceptar = Boton("Aceptar",170,(120+(50*cobros.size())-20),100,50,windowMostrarCobro);
			Aceptar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					windowMostrarCobro.setVisible(false);
			}});
			}else if(opc == 2)
			{
				final JTextField cod = TextField(20,(120+(50*cobros.size())-30),100,30,windowMostrarCobro,"Codigo");
				
				JButton Imprimir = Boton("Imprimir",520,(120+(50*cobros.size())-20),100,50,windowMostrarCobro);
				Imprimir.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try{
							for(int i=0;i<=GestionarCaja.cobros.size();i++)
							{
								if(Integer.parseInt((cod.getText())) == cobros.get(i).getCodigo())ImprimirCobro(Integer.parseInt((cod.getText())));
								else if(i+1 == GestionarCaja.cobros.size()) VentanaError("     Cobro no encontrado!");
							}
							
						} catch (NumberFormatException e1) { 
							VentanaError("	        Campo Inv�lido!");
						}
						
						windowMostrarCobro.setVisible(false);
				}});
			}
				
	}
	public static void MostrarPagos(final Vector<Pago> pagos, int opc) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowMostrarPago = new JDialog(windowCaja,"Mostrar Pago");
	
		windowMostrarPago.setSize(650,120+(50*pagos.size())+80);
		windowMostrarPago.setLayout(null);
		windowMostrarPago.setVisible(true);
		
		Label("Codigo",50,15,100,30,windowMostrarPago);
		Label("Codigo Mercaderia",150,15,150,30,windowMostrarPago);
		Label("Nombre Proveedor",300,15,150,30,windowMostrarPago);
		Label("Precio",450,15,100,30,windowMostrarPago);
		for(int i=0;i<650;i++)Label("-",i,50,800,30,windowMostrarPago);
		for(int i=0;i<650;i++)Label("-",i,(120+(50*pagos.size())-60),800,30,windowMostrarPago);
		
			for(int i=0;i<pagos.size();i++)
			{
				Label(Integer.toString(pagos.get(i).getCodigo()),50,80+(30*i),200,30,	windowMostrarPago);
				Label(Integer.toString(pagos.get(i).getCodmerca()),150,80+(30*i),200,30,	windowMostrarPago);
				Label(pagos.get(i).getNomprovedor(),300,80+(30*i),200,30,	windowMostrarPago);
				Label(Float.toString(pagos.get(i).getPrecio()),450,80+(30*i),200,30,	windowMostrarPago);
			}			
		
		if(opc == 1)
		{
			JButton Aceptar = Boton("Aceptar",520,(120+(50*pagos.size())-20),100,50,windowMostrarPago);
			Aceptar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					windowMostrarPago.setVisible(false);
			}});
		}
		else if(opc == 2)
		{
			final JTextField cod = TextField(20,(120+(50*pagos.size())-30),100,30,windowMostrarPago,"Codigo");
			
			JButton Imprimir = Boton("Imprimir",520,(120+(50*pagos.size())-20),100,50,windowMostrarPago);
			Imprimir.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try{
						for(int i=0;i<GestionarCaja.pagos.size();i++)
						{
							if(Integer.parseInt((cod.getText())) == pagos.get(i).getCodigo())ImprimirPago(Integer.parseInt((cod.getText())));
							else if(i+1 == GestionarCaja.pagos.size()) VentanaError("     Pago no encontrado!");
						}
						
					} catch (NumberFormatException e1) { 
						VentanaError("	        Campo Inv�lido!");
					}
					
					windowMostrarPago.setVisible(false);
			}});
		}
	}
	public static void ImprimirPago(int cod)
	{
		PrinterJob job = PrinterJob.getPrinterJob();
		Impresora impresora = new Impresora();
		Pago p = GestionarCaja.getPago(cod);
		String text1, text2, text3, text4;
		text1 = "Codigo de Pago: "+p.getCodigo();
		text2= "Codigo de Mercaderia: "+p.getCodmerca();
		text3="Nombre del Proveedor: "+p.getNomprovedor();
		text4= "Precio: "+p.getPrecio();
		
		impresora.setText1(text1);
		impresora.setText2(text2);
		impresora.setText3(text3);
		impresora.setText4(text4);
		job.setPrintable(impresora);
		try 
		{
		   job.print();
		} 
		catch (PrinterException e) 
		{
		   e.printStackTrace();
		   VentanaError("Error conexion impresora");
		}
	}
	public static void ImprimirCobro(int cod)
	{
		PrinterJob job = PrinterJob.getPrinterJob();
		Impresora impresora = new Impresora();
		Cobro c = GestionarCaja.getCobro(cod);
		String text1, text2;
		text1 = "Codigo de Cobro: "+c.getCodigo();
		text2= "Precio: "+c.getPrecio();
		
		impresora.setText1(text1);
		impresora.setText2(text2);
		job.setPrintable(impresora);
		try 
		{
		   job.print();
		} 
		catch (PrinterException e) 
		{
		   e.printStackTrace();
		   VentanaError("Error conexion impresora");
		}
	}

public static void WVentas() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ConnectException
{
	 
	windowVentas = new JFrame("Gestionar Ventas");
	windowVentas.setSize(275,295);
	windowVentas.setLayout(null);
	windowVentas.setVisible(true);
	JButton bAgregarVenta = Boton("Agregar Venta",50,45,150,50,windowVentas);
	JButton bBuscarVenta = Boton("Buscar Venta",50,160,150,50,windowVentas);
	
	//Agregar Venta
			bAgregarVenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WAgregarVenta();
			}});
	//Agregar Venta
	bBuscarVenta.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				WBuscarVenta();
			} catch (InstantiationException e1) {
				 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				 
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				 
				e1.printStackTrace();
			}
	}});
	
	// Hacer que al cerrarse la secundaria con la x de arriba a la derecha, se muestre la primaria
	windowVentas.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowVentas.setVisible(false);
		}
	
		public void windowClosed(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowVentas.setVisible(false);
		}
	});	
}
	public static void WAgregarVenta()
	{
		JDialog windowAgregarVenta = new JDialog(windowVentas,"Agregar Venta");
		windowAgregarVenta.setSize(500,510);
		windowAgregarVenta.setLayout(null);
		windowAgregarVenta.setVisible(true);
		
		Label("Codigo Venta:",50,30,100,30,windowAgregarVenta);
		Label("Codigo Cliente:",50,100,100,30,windowAgregarVenta);
		Label("Codigo Mercaderia:",30,170,150,30,windowAgregarVenta);
		Label("Fecha(dd-mm):",40,240,100,30,windowAgregarVenta);
		Label("Cantidad:",50,310,100,30,windowAgregarVenta);
		
		final JTextField Cod = TextField(150,30,200,30,windowAgregarVenta);
		final JTextField CodCli = TextField(150,100,200,30,windowAgregarVenta);
		final JTextField CodMer = TextField(150,170,200,30,windowAgregarVenta);
		final JTextField Fecha = TextField(150,240,200,30,windowAgregarVenta);
		final JTextField Cantidad = TextField(150,310,200,30,windowAgregarVenta);
		
		JButton Agregar = Boton("Agregar",370,410,100,50,windowAgregarVenta);
		//Agregar
				Agregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
	
						String error = "";
						try {
							if(!connection)
								{for(int i=0;i<GestionarVentas.ventas.size();i++)
								{
									System.out.println(Integer.parseInt(Cod.getText()) +" "+  GestionarVentas.ventas.get(i).getCodigo());
									if(Integer.parseInt(Cod.getText()) == GestionarVentas.ventas.get(i).getCodigo()) error = "Venta ya existente!";
								}
		
								for(int i=0;i<GestionarClientes.clientes.size();i++)
								{
									if(GestionarClientes.clientes.get(i).getDni() == Integer.parseInt(CodCli.getText())) break;
									if(i+1 == GestionarClientes.clientes.size())
											error = "     Cliente no encontrado!";
								}
								
								for(int i=0;i<GestionarMercaderias.mercaderias.size();i++)
								{
									int ok = 0;
									if(GestionarMercaderias.mercaderias.get(i).getCodigo() == Integer.parseInt(CodMer.getText()))
									{
										if (GestionarMercaderias.mercaderias.get(i).getStock() < Integer.parseInt(Cantidad.getText())){error="    Stock insuficiente!";ok=1;}
										else ok=1;
									}
									if(ok == 1)break;
									if(i+1 == GestionarMercaderias.mercaderias.size())
											error = "   Mercaderia no encontrada!";
								}
								
								if(error == "")
								{
									GestionarVentas.AgregarVenta(new Venta(Integer.parseInt(Cod.getText()),Integer.parseInt(CodCli.getText()),Fecha.getText(),Integer.parseInt(CodMer.getText()),Integer.parseInt(Cantidad.getText())));			
									windowPrincipal.setVisible(true);
									windowVentas.setVisible(false);
								}else VentanaError(error);
						}else 
							{
							GestionarVentas.BackUp(new Venta(Integer.parseInt(Cod.getText()),Integer.parseInt(CodCli.getText()),Fecha.getText(),Integer.parseInt(CodMer.getText()),Integer.parseInt(Cantidad.getText())));
							windowPrincipal.setVisible(true);
							windowVentas.setVisible(false);
							}
						} catch (ClassNotFoundException e1) { 
							e1.printStackTrace();
						} catch (NumberFormatException e2) { 
							VentanaError("	        Campo Inv�lido!");
						} catch (InstantiationException e3) { 
							e3.printStackTrace();
						} catch (IllegalAccessException e4) { 
							e4.printStackTrace();} 
						catch (SQLException e5) {	 
							e5.printStackTrace();} catch (IOException e13) {
							// TODO Auto-generated catch block
							e13.printStackTrace();
						}
				}});
		
	}
	public static void WBuscarVenta() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowBuscarVenta = new JDialog(windowCaja,"Buscar Venta");
		windowBuscarVenta.setSize(280,200);
		windowBuscarVenta.setLayout(null);
		windowBuscarVenta.setVisible(true);
		
		final JTextField value = TextField(25,30,100,30,windowBuscarVenta);
		String[] param = {"cod_venta","cod_cliente","cod_mercaderia","cantidad","fecha"};
		final JComboBox combo = new JComboBox(param);
		combo.setLocation(135,30);
		combo.setSize(120,30);
		combo.setSelectedIndex(0);
		combo.setVisible(true);
		windowBuscarVenta.getContentPane().add(combo);
		
		JButton Buscar = Boton("Buscar",100,80,80,50,windowBuscarVenta);
		//Buscar
				Buscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					Vector<Venta> ventas;
					try {
						ventas = GestionarVentas.BuscarVenta(value.getText(),(String)combo.getSelectedItem());
						if(ventas.size() == 0)VentanaError("Venta no encontrado!");
						else {windowBuscarVenta.setVisible(false);MostrarVentas(ventas);}
					} catch (NumberFormatException e1) { 
						VentanaError("	        Campo Inv�lido!");
					} catch (InstantiationException e1) { 
						e1.printStackTrace();
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) { 
						e1.printStackTrace();
					}
				}});
	}
	public static void MostrarVentas(final Vector<Venta> ventas) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowMostrarPago = new JDialog(windowCaja,"Mostrar Venta");
	
		windowMostrarPago.setSize(870,120+(50*ventas.size())+80);
		windowMostrarPago.setLayout(null);
		windowMostrarPago.setVisible(true);
		
		Label("Codigo Venta",20,15,100,30,windowMostrarPago);
		Label("Codigo Cliente",130,15,150,30,windowMostrarPago);
		Label("Codigo Mercaderia",270,15,150,30,windowMostrarPago);
		Label("Fecha",450,15,100,30,windowMostrarPago);
		Label("Cantidad",550,15,100,30,windowMostrarPago);
		Label("Codigo Cobro",650,15,100,30,windowMostrarPago);
		Label("Importe",750,15,100,30,windowMostrarPago);
		for(int i=0;i<870;i++)Label("-",i,50,5,30,windowMostrarPago);
		for(int i=0;i<870;i++)Label("-",i,(120+(50*ventas.size())-60),5,30,windowMostrarPago);
		
		for(int i=0;i<ventas.size();i++)
		{
			Label(Integer.toString(ventas.get(i).getCodigo()),50,80+(30*i),200,30,	windowMostrarPago);
			Label(Integer.toString(ventas.get(i).getCod_cliente()),150,80+(30*i),200,30,	windowMostrarPago);
			Label(Integer.toString(ventas.get(i).getCod_mercaderia()),300,80+(30*i),200,30,	windowMostrarPago);
			Label(ventas.get(i).getFecha(),450,80+(30*i),200,30,	windowMostrarPago);
			Label(Integer.toString(ventas.get(i).getCantidad()),550,80+(30*i),200,30,	windowMostrarPago);
			Label(Integer.toString(ventas.get(i).getCod_cobro()),650,80+(30*i),200,30,	windowMostrarPago);
			Label(Float.toString(GestionarCaja.cobros.get(ventas.get(i).getCod_cobro()).getPrecio()),750,80+(30*i),200,30,	windowMostrarPago);
		}			
		
		JButton Aceptar = Boton("Aceptar",730,(120+(50*ventas.size())-20),100,50,windowMostrarPago);
		Aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				windowMostrarPago.setVisible(false);
		}});		
	}
	
	
public static void WPedidos() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ConnectException
{
	 
	windowPedidos = new JFrame("Gestionar Pedidos");
	windowPedidos.setSize(275,400);
	windowPedidos.setLayout(null);
	windowPedidos.setVisible(true);
	JButton bAgregarPedido = Boton("Agregar Pedido",50,50,150,50,windowPedidos);
	JButton bBuscarPedido = Boton("Buscar Pedido",50,150,150,50,windowPedidos);
	JButton bImprimirPedido = Boton("Imprimir Pedido",50,250,150,50,windowPedidos);
		
	//Agregar Pedido
			bAgregarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WAgregarPedido();
			}});
	//Buscar Pedido
				bBuscarPedido.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							WBuscarPedido(1);
						} catch (InstantiationException e1) { 
							e1.printStackTrace();
						} catch (IllegalAccessException e1) { 
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) { 
							e1.printStackTrace();
						}
				}});
				
				//Imprimir Pedido
				bImprimirPedido.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							WBuscarPedido(2);
						} catch (InstantiationException e1) { 
							e1.printStackTrace();
						} catch (IllegalAccessException e1) { 
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) { 
							e1.printStackTrace();
						}
				}});
	
				// Hacer que al cerrarse la secundaria con la x de arriba a la derecha, se muestre la primaria
	windowPedidos.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowPedidos.setVisible(false);
		}
	
		public void windowClosed(WindowEvent e) {
			windowPrincipal.setVisible(true);
			windowPedidos.setVisible(false);
		}
	});	
}
	public static void WAgregarPedido()
	{
		JDialog windowAgregarPedido = new JDialog(windowPedidos,"Agregar Pedido");
		windowAgregarPedido.setSize(500,380);
		windowAgregarPedido.setLayout(null);
		windowAgregarPedido.setVisible(true);
		
		Label("Codigo:",50,30,100,30,windowAgregarPedido);
		Label("Codigo Cliente:",50,100,100,30,windowAgregarPedido);
		Label("Cod. Mercaderia:",35,170,100,30,windowAgregarPedido);
		Label("Fecha Entrega(dd/mm):",30,240,100,30,windowAgregarPedido);
		
		final JTextField codigo = TextField(150,30,200,30,windowAgregarPedido);
		final JTextField codcli = TextField(150,100,200,30,windowAgregarPedido);
		final JTextField codmer = TextField(150,170,200,30,windowAgregarPedido);
		final JTextField fecha = TextField(150,240,200,30,windowAgregarPedido);
		
		JButton Agregar = Boton("Agregar",370,280,100,50,windowAgregarPedido);
		//Agregar
				Agregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
	
						String error = "NOERROR";
						try {
							if(connection)
							{for(int i=0;i<GestionarPedidos.pedidos.size();i++)if(Integer.parseInt(codigo.getText())==GestionarPedidos.pedidos.get(i).getCodigo())error = "     Codigo ya existente!";
							for(int i=0;i<GestionarClientes.clientes.size();i++)
							{
								if(Integer.parseInt(codcli.getText())==GestionarClientes.clientes.get(i).getDni())break;
								if(i+1 == GestionarClientes.clientes.size()) error = "    Cliente no encontrado!!!";
							}
							for(int i=0;i<GestionarMercaderias.mercaderias.size();i++)
							{
								if(Integer.parseInt(codmer.getText())==GestionarMercaderias.mercaderias.get(i).getCodigo())break;
								if(i+1 == GestionarMercaderias.mercaderias.size()) error = "    Mercaderia no encontrada!!!";
							}							
							if(error=="NOERROR")
							{
								String[] fecha1 = fecha.getText().split("/");
								//fecha1[1] = Integer.toString(Integer.parseInt(fecha1[1])-1);
								String fechaEntrega = "2012-"+fecha1[1]+"-"+fecha1[0];
								GestionarPedidos.AgregarPedido(new Pedido(Integer.parseInt(codigo.getText()),Integer.parseInt(codcli.getText()),Integer.parseInt(codmer.getText()),fechaEntrega));			
								windowPrincipal.setVisible(true);
								windowPedidos.setVisible(false);
							}else {VentanaError(error);}
							}else 
								{
								String[] fecha1 = fecha.getText().split("/");
								String fechaEntrega = "2012-"+fecha1[1]+"-"+fecha1[0];
								GestionarPedidos.BackUp(new Pedido(Integer.parseInt(codigo.getText()),Integer.parseInt(codcli.getText()),Integer.parseInt(codmer.getText()),fechaEntrega));
								windowPrincipal.setVisible(true);
								windowPedidos.setVisible(false);
								}
						} catch (ClassNotFoundException e1) { 
							e1.printStackTrace();
						} catch (NumberFormatException e2) { 
							VentanaError("	        Campo Inv�lido!");
						} catch (InstantiationException e3) { 
							e3.printStackTrace();
						} catch (IllegalAccessException e4) { 
							e4.printStackTrace();} 
						catch (SQLException e5) {	 
							e5.printStackTrace();} catch (IOException e6) {
							// TODO Auto-generated catch block
							e6.printStackTrace();
						}
				}});
		
	}
	public static void WBuscarPedido(final int opc) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowBuscarPedido = new JDialog(windowPedidos,"Buscar Pedido");
		windowBuscarPedido.setSize(280,200);
		windowBuscarPedido.setLayout(null);
		windowBuscarPedido.setVisible(true);
		
		final JTextField value = TextField(25,30,100,30,windowBuscarPedido);
		String[] param = {"cod_pedido","cod_cliente","cod_mercaderia","dia_entrega"};
		final JComboBox combo = new JComboBox(param);
		combo.setLocation(145,30);
		combo.setSize(100,30);
		combo.setSelectedIndex(0);
		combo.setVisible(true);
		windowBuscarPedido.getContentPane().add(combo);
		
		JButton Buscar = Boton("Buscar",100,80,80,50,windowBuscarPedido);
		//Buscar
				Buscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					Vector<Pedido> pedidos;
					try {
						pedidos = GestionarPedidos.BuscarPedido(value.getText(),(String)combo.getSelectedItem());
						if(pedidos.size() == 0)VentanaError("Pedido no encontrado!");
						else {windowBuscarPedido.setVisible(false);MostrarPedidos(pedidos,opc);}
					} catch (NumberFormatException e1) { 
						VentanaError("	        Campo Inv�lido!");
					} catch (InstantiationException e1) { 
						e1.printStackTrace();
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) { 
						e1.printStackTrace();
					} catch (SQLException e1) {	 
						e1.printStackTrace();
					}	
				}});
	}
	public static void MostrarPedidos(final Vector<Pedido> pedidos, int opc) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		final JDialog windowMostrarPedido = new JDialog(windowPedidos,"Mostrar Pedido");
	
		windowMostrarPedido.setSize(775,120+(50*pedidos.size())+80);
		windowMostrarPedido.setLayout(null);
		windowMostrarPedido.setVisible(true);
		
		Label("Codigo",50,15,100,30,windowMostrarPedido);
		Label("Codigo Cliente",150,15,100,30,windowMostrarPedido);
		Label("Codigo Mercaderia",300,15,120,30,windowMostrarPedido);
		Label("Fecha Entrega",450,15,100,30,windowMostrarPedido);
		for(int i=0;i<775;i++)Label("-",i,50,800,30,windowMostrarPedido);
		for(int i=0;i<775;i++)Label("-",i,(120+(50*pedidos.size())-60),800,30,windowMostrarPedido);
		
			for(int i=0;i<pedidos.size();i++)
			{
				Label(Integer.toString(pedidos.get(i).getCodigo()),50,80+(30*i),200,30,windowMostrarPedido);
				Label(Integer.toString(pedidos.get(i).getCod_cliente()),150,80+(30*i),200,30,windowMostrarPedido);
				Label(Integer.toString(pedidos.get(i).getCod_mercaderia()),300,80+(30*i),200,30,windowMostrarPedido);
				Label(pedidos.get(i).getFecha_entrega(),450,80+(30*i),200,30,windowMostrarPedido);
			}		
			if(opc==1)
			{
			JButton Aceptar = Boton("Aceptar",650,(120+(50*pedidos.size())-20),100,50,windowMostrarPedido);
			Aceptar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					windowMostrarPedido.setVisible(false);
			}});
			}
			else if(opc == 2)
			{
				final JTextField cod = TextField(20,(120+(50*pedidos.size())-30),100,30,windowMostrarPedido,"Fecha");
				
				JButton Imprimir = Boton("Imprimir",520,(120+(50*pedidos.size())-20),100,50,windowMostrarPedido);
				Imprimir.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try{
							for(int i=0;i<GestionarPedidos.pedidos.size();i++)
							{
								if((cod.getText()) == pedidos.get(i).getFecha_entrega())ImprimirPedidos(((cod.getText())));
								else if(i+1 == GestionarPedidos.pedidos.size()) VentanaError("    Pedido no encontrado!");
							}
							
						} catch (NumberFormatException e1) { 
							VentanaError("	        Campo Inv�lido!");
						} catch (InstantiationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						windowMostrarPedido.setVisible(false);
				}});
			}
				
	}
	
	public static void ImprimirPedidos(String cod) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		PrinterJob job = PrinterJob.getPrinterJob();
		Impresora impresora = new Impresora();
		Vector<Pedido> p = GestionarPedidos.BuscarPedido(cod,"dia_entrega");
		
		impresora.setText1("Fecha de Entrega: "+p.get(0).getFecha_entrega());
		for(int i=0;i<p.size();i++)
		{	
			impresora.setTexto("Codigo de Pedido: "+p.get(i).getCodigo());
			impresora.setTexto("Cliente: "+GestionarClientes.clientes.get(p.get(i).getCod_cliente()).getNombre()+" "+
					GestionarClientes.clientes.get(p.get(i).getCod_cliente()).getApellido());
			impresora.setTexto("Mercaderia: "+GestionarMercaderias.mercaderias.get(p.get(i).getCod_mercaderia()).getNombre());
			impresora.setTexto(" ");
		}
		job.setPrintable(impresora);
		try 
		{
		   job.print();
		} 
		catch (PrinterException e) 
		{
		   e.printStackTrace();
		   VentanaError("Error conexion impresora");
		}
	}
}