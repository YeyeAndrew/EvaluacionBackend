
package Dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {
    /*  private Integer id;
        private String numeroMatricula;
        private String nombre;
        private String apellido;
;*/
    private static final Logger logger= Logger.getLogger(BD.class);
    private static final String SQL_CREATE_ODONTOLOGOS ="DROP TABLE IF EXISTS ODONTOLOGOS; " +
            "CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL, NUMERO_MATRICULA VARCHAR(100) NOT NULL)";
    private static final String SQL_INSERT_PRUEBA = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, NUMERO_MATRICULA) VALUES ('Maria','Caceres','345678');";
    public static void crearTabla(){
        Connection connection= null;
        try{
            connection=getConnection();
            Statement statement= connection.createStatement();
            statement.execute(SQL_CREATE_ODONTOLOGOS);
            statement.execute(SQL_INSERT_PRUEBA);
            logger.info("datos cargados con exito");

        }catch (Exception e){
            logger.error("fallo en la BD: "+e.getMessage());
        }

    }
    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:~/ClinicaOdontologicaC3","sa","sa");
    }
}
