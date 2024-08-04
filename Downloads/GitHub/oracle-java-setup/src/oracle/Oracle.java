package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Oracle {

    public static void main(String[] args) {
        // Información de conexión
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambia esto si tu configuración es diferente
        String username = "system"; // Cambia esto según tu usuario de Oracle
        String password = "orcl";   // Cambia esto según tu contraseña de Oracle

        Connection connection = null;
        Statement statement = null;

        try {
            // Cargar el controlador JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establecer la conexión
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Conexión exitosa.");

            // Crear un Statement para ejecutar comandos SQL
            statement = connection.createStatement();

            // Ejemplo de comando SQL para crear un tablespace
            String sql = "CREATE TABLESPACE my_tablespace DATAFILE 'C:\\oraclexe\\oradata\\XE\\my_tablespace01.dbf' SIZE 100M AUTOEXTEND ON";
            statement.execute(sql);
            System.out.println("Tablespace creado exitosamente.");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver JDBC no encontrado.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en la conexión a la base de datos.");
        } finally {
            // Cerrar recursos
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
