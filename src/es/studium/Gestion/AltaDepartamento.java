package es.studium.Gestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AltaDepartamento implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Alta Departamentos");
	
	Label lblNombre = new Label("Nombre Departamento");
	TextField txtNombre = new TextField(20);
	Label lblLocalidad = new Label("Localidad Departamento");
	TextField txtLocalidad = new TextField(20);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	
	Dialog dlMensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensaje = new Label("Departamento creado correctamente");
	
	Datos datos = new Datos();
	
	String usuario;
	
	AltaDepartamento(String u) 
	{
		usuario = u;
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblLocalidad);
		ventana.add(txtLocalidad);
		ventana.add(btnAceptar);
		ventana.add(btnLimpiar);
		
		ventana.setSize(210, 200);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent evento)
	{
		if(evento.getSource().equals(btnAceptar))
		{
			datos.conectar();
			boolean altaCorrecta = datos.altaDepartamento(txtNombre.getText(), txtLocalidad.getText());
			
			dlMensaje.setLayout(new FlowLayout());
			dlMensaje.addWindowListener(this);
			dlMensaje.setSize(300, 100);
			dlMensaje.setLocationRelativeTo(null);
			dlMensaje.setResizable(false);
			
			if(altaCorrecta == false)
			{
				lblMensaje.setText("Se ha producido un error");
			}
			else if (altaCorrecta == true)
			{
				lblMensaje.setText("Departamento creado correctamente");
			}
			dlMensaje.add(lblMensaje);
			dlMensaje.setVisible(true);
			datos.desconectar();
			
		}
		else if(evento.getSource().equals(btnLimpiar))
		{
			txtNombre.setText("");
			txtLocalidad.setText("");
			txtNombre.requestFocus();
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if(dlMensaje.isActive())
		{
			dlMensaje.setVisible(false);
			txtNombre.setText("");
			txtLocalidad.setText("");
			txtNombre.requestFocus();
		}
		else
		{
			ventana.setVisible(false);	
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowDeactivated(WindowEvent e){}
}