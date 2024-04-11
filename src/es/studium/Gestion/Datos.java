package es.studium.Gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datos
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/gestion";
	String login = "root";
	String password = "Studium2023;";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	Utilidades utilidades = new Utilidades();

	Datos() {}

	public boolean conectar()
	{
		boolean conexionCorrecta = true;
		//Cargar el Driver
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
			conexionCorrecta = false;
		}

		//Establecer la conexión con la base de datos
		try
		{
			connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			conexionCorrecta = false;
		}
		return conexionCorrecta;
	}


	public boolean comprobarCredenciales(String usuario, String clave)
	{
		boolean credencialesCorrectas = true;
		String sentencia = "SELECT * FROM usuarios "
				+ "WHERE nombreUsuario='"+usuario+"' "
				+ "AND claveUsuario = SHA2('"+clave+"', 256);";

		try
		{
			statement =
					connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			if(!rs.next())
			{
				// Credenciales incorrectas
				credencialesCorrectas = false;
			}
		}

		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
		}

		return credencialesCorrectas;
	}

	public void desconectar()
	{
		try
		{
			statement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			System.out.println("Error al cerrar " + e.toString());
		}
	}

	public String dameDepartamentos ()
	{
		String contenido = "";
		String sentencia = "SELECT * FROM departamentos;";

		try
		{
			statement =
					connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				contenido = contenido + rs.getString("nombreDepartamento")+ "\n";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
		}

		return contenido;
	}

	public boolean altaDepartamento(String nombre, String localidad)
	{
		boolean altaCorrecta = true;
		String sentenciaSQL = "INSERT INTO departamentos VALUES (NULL, '" + nombre + "', '" + localidad + "');";
		System.out.println(sentenciaSQL);
		utilidades.guardarLog("?", sentenciaSQL);

		try
		{
			statement =
					connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentenciaSQL);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
			altaCorrecta = false;
		}

		return altaCorrecta;
	}

	public int dameTipo(String usuario)
	{
		String sentencia = "SELECT tipoUsuario FROM usuarios WHERE usuario = '"+usuario + "';";
		return 0;
	}

	public String[] rellenarChoiceDepartamentos()
	{
		String elementosCadena = "Elegir un departamento...*"; 
		String sentencia = "SELECT * FROM departamentos;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while(rs.next())
			{
				elementosCadena = elementosCadena + rs.getString("idDepartamento") + "-" + rs.getString("nombreDepartamento")
				+ rs.getString("localidadDepartamento") + "*";
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL: " + e.toString());
		}
		return elementosCadena.split("\\*");
	}

	public void bajaDepartamento(String elementoSeleccionado)
	{
		String sentencia = "DELETE FROM departamentos WHERE idDepartamento = " + elementoSeleccionado;

		try
		{
			statement =
					connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
		}
	}
}