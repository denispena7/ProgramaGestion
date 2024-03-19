package es.studium.Gestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BajaDepartamento implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Baja departamento");
	Label lblbaja = new Label("Elegir Departamento a borrar:");
	Choice choDepartamento = new Choice();
	Button btnAceptar = new Button("Aceptar");

	Dialog dlgSeguro = new Dialog(ventana, "¿Seguro?", true);
	Label lblMensaje = new Label("¿Seguro de borrar?");
	Button btnSi = new Button("Sí");
	Button btnNo = new Button("No");
	
	Datos datos = new Datos();

	BajaDepartamento()
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		dlgSeguro.addWindowListener(this);
		btnAceptar.addActionListener(this);
		btnSi.addActionListener(this);
		btnNo.addActionListener(this);
		ventana.add(lblbaja);
		
		datos.conectar();
		String[] elementos = datos.rellenarChoiceDepartamentos();
		datos.desconectar();
		for(String elemento: elementos)
		{
			choDepartamento.add(elemento);
		}
		
		ventana.add(choDepartamento);
		ventana.add(btnAceptar);
		
		ventana.setSize(300, 250);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnAceptar))
		{
			if(choDepartamento.getSelectedIndex() != 0)
			{
				dlgSeguro.setLayout(new FlowLayout());
				dlgSeguro.addWindowListener(this);
				dlgSeguro.setSize(250,70);
				dlgSeguro.setResizable(false);
				dlgSeguro.setLocationRelativeTo(null);
				dlgSeguro.add(lblMensaje);
				dlgSeguro.add(btnSi);
				dlgSeguro.add(btnNo);
				dlgSeguro.setVisible(true);
			}
			else if(e.getSource().equals(btnSi)) 
			{
				datos.conectar();
				String elementoSeleccionado = choDepartamento.getSelectedItem().split("-")[0];
				datos.bajaDepartamento(elementoSeleccionado);
				datos.desconectar();
				dlgSeguro.setVisible(false);
				ventana.setVisible(false);
			}
			else if(e.getSource().equals(btnNo))
			{
				dlgSeguro.setVisible(false);
			}
		}
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		// Si se pulsa X del diálogo
		if(dlgSeguro.isActive())
		{
			dlgSeguro.setVisible(false);
		}
		else // Sisepulsa X del Frame
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