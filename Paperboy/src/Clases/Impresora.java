package Clases;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Vector;

public class Impresora implements Printable
{
	String text1;
	String text2;
	String text3;
	String text4;
	Vector<String> textos = new Vector<String>();
	public int print(Graphics g, PageFormat f, int index)throws PrinterException 
	{
	      if (index == 0) 
	      {
	         g.drawString(text1, 100,100);
	         g.drawString(text2, 150,200);
	         g.drawString(text3, 200,300);
	         g.drawString(text4, 250,400);
	         
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	}
	
	public int printVarios(Graphics g, PageFormat f, int index)throws PrinterException 
	{
	      if (index == 0) 
	      {
	    	g.drawString(text1,100,100);
	    	for(int i=0;i<textos.size();i++)
	        { 	
	    	  g.drawString(textos.get(i),150+(i*50),150+(i*100));
	        }        
	        textos = new Vector<String>();
	        return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	}
	
	public void setText1(String tx) 
	{
		text1 = tx;
	}
	public void setTexto(String tx) 
	{
		textos.add(tx);
	}
	public void setText2(String tx) 
	{
		text2 = tx;
	}
	public void setText3(String tx) 
	{
		text3 = tx;
	}
	
	public void setText4(String tx) 
	{
		text4 = tx;
	}
}
