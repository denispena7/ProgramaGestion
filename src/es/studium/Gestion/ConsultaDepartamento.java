package es.studium.Gestion;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ConsultaDepartamento implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Listado Departamentos");

	TextArea listado = new TextArea(5,40);
	Button btnVolver = new Button("Volver");
	
	Datos datos = new Datos();

	ConsultaDepartamento ()
	{
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		btnVolver.addActionListener(this);
		
		// Conectar BD
		datos.conectar();
		listado.append(datos.dameDepartamentos());
		datos.desconectar();
		
		ventana.add(listado);
		ventana.add(btnVolver);
		ventana.setSize(350, 200);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ventana.setVisible(false);
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		ventana.setVisible(false);
		
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