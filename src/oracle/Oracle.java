package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleSetup {

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

            // Crear Tablespace
            String createTablespace = "CREATE TABLESPACE my_tablespace DATAFILE 'C:\\oraclexe\\oradata\\XE\\my_tablespace01.dbf' SIZE 100M AUTOEXTEND ON";
            statement.execute(createTablespace);
            System.out.println("Tablespace creado exitosamente.");

            // Crear Usuario
            String createUser = "CREATE USER my_user IDENTIFIED BY my_password DEFAULT TABLESPACE my_tablespace";
            statement.execute(createUser);
            System.out.println("Usuario creado exitosamente.");

            // Asignar Roles y Permisos al Usuario
            String grantRoles = "GRANT CONNECT, RESOURCE TO my_user";
            statement.execute(grantRoles);
            System.out.println("Roles asignados exitosamente.");

            String grantPermissions = "GRANT CREATE TABLE, CREATE VIEW TO my_user";
            statement.execute(grantPermissions);
            System.out.println("Permisos asignados exitosamente.");

            // Crear Tablas
            String createTable = "CREATE TABLE my_table (" +
                                 "id NUMBER PRIMARY KEY," +
                                 "name VARCHAR2(50)," +
                                 "description VARCHAR2(255)" +
                                 ") TABLESPACE my_tablespace";
            statement.execute(createTable);
            System.out.println("Tabla creada exitosamente.");

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
