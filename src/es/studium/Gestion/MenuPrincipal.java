package es.studium.Gestion;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Principal");
	MenuBar barraMenu = new MenuBar();
	
	Menu mnuDepartamentos = new Menu("Departamentos");
	Menu mnuEmpleados = new Menu("Empleados");
	
	MenuItem mniConsultaDepartamento = new MenuItem("Consulta");
	MenuItem mniAltaDepartamento = new MenuItem("Alta");
	MenuItem mniBajaDepartamento = new MenuItem("Baja");
	MenuItem mniEditarDepartamento = new MenuItem("Editar");
	
	MenuItem mniConsultaEmpleado = new MenuItem("Consulta");
	MenuItem mniAltaEmpleado = new MenuItem("Alta");
	MenuItem mniBajaEmpleado = new MenuItem("Baja");
	MenuItem mniEditarEmpleado = new MenuItem("Editar");
	
	MenuPrincipal ()
	{
		ventana.setLayout(new FlowLayout());
		
		ventana.addWindowListener(this);
		mniConsultaDepartamento.addActionListener(this);
		
		ventana.setMenuBar(barraMenu);
		
		barraMenu.add(mnuDepartamentos);
		mnuDepartamentos.add(mniConsultaDepartamento);
		mnuDepartamentos.add(mniAltaDepartamento);
		mnuDepartamentos.add(mniBajaDepartamento);
		mnuDepartamentos.add(mniEditarDepartamento);
		
		barraMenu.add(mnuEmpleados);
		mnuEmpleados.add(mniConsultaEmpleado);
		mnuEmpleados.add(mniAltaEmpleado);
		mnuEmpleados.add(mniBajaEmpleado);
		mnuEmpleados.add(mniEditarEmpleado);
		
		ventana.setSize(250, 200);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
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

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		if(actionEvent.getSource().equals(mniConsultaDepartamento))
		{
			new ConsultaDepartamento();
		}
	}
	
}
