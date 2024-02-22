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

public class Login implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Login");
	Label lblUsuario = new Label("Usuario:");
	Label lblClave = new Label("Clave:");
	Label lblError = new Label("Credenciales Incorrectas");
	TextField txtUsuario = new TextField(20);
	TextField txtClave = new TextField(20);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog dlgError = new Dialog(ventana, "Error", true);

	Login()
	{
		ventana.setLayout(new FlowLayout());

		ventana.addWindowListener(this);
		btnLimpiar.addActionListener(this);
		btnAceptar.addActionListener(this);

		txtClave.setEchoChar('*');

		ventana.add(lblUsuario);
		ventana.add(txtUsuario);
		ventana.add(lblClave);
		ventana.add(txtClave);
		ventana.add(btnAceptar);
		ventana.add(btnLimpiar);

		ventana.setSize(220, 190);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	public static void main(String[] args)
	{
		new Login();
	}


	@Override
	public void windowClosing(WindowEvent e)
	{
		// Si se pulsa X del diálogo
		if(dlgError.isActive())
		{
			dlgError.setVisible(false);
		}
		else // Sisepulsa X del Frame
		{
			System.exit(0);
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
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		if(actionEvent.getSource().equals(btnLimpiar))
		{
			txtUsuario.setText("");
			txtClave.setText("");
			txtUsuario.requestFocus();
		}
		else if(actionEvent.getSource().equals(btnAceptar))
		{
			// Conectar a la BD
			Datos datos = new Datos();
			if(datos.conectar()==true)
			{
				// Si OK, comprobarlascredenciales
				String usuario = txtUsuario.getText();
				String clave = txtClave.getText();

				if(datos.comprobarCredenciales(usuario, clave)== true)
				{
					// Si OK, ir al Menú Principal
					new MenuPrincipal();
					ventana.setVisible(false);
				}
				else
				{
					// Si no OK, mostrarDiálogo
					dlgError.setLayout(new FlowLayout());
					dlgError.setSize(220,190);
					dlgError.setResizable(false);
					dlgError.setLocationRelativeTo(null);
					dlgError.add(lblError);
					dlgError.addWindowListener(this);
					dlgError.setVisible(true);
				}
			}
			else
			{
				System.out.println("Conexión rechazada");
			}
		}
	}
}