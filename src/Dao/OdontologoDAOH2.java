package Dao;

import Model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OdontologoDAOH2 implements iDao<Odontologo>{
    public static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, NUMERO_MATRICULA) " +
            "VALUES(?,?,?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGO WHERE ID=? ";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("iniciando las operaciones de guardado de un odontologo");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getNombre());
            psInsert.setString(2, odontologo.getApellido());
            psInsert.setString(3, odontologo.getNumMatricula());
            psInsert.execute(); //se van a generar ID
            ResultSet rs= psInsert.getGeneratedKeys();
            while(rs.next()){
                odontologo.setId(rs.getInt(1));
            }

        }catch (Exception e){
            logger.error("conexion fallida: "+e.getMessage());
        }
        return odontologo;
    }
    public List<Odontologo> listarTodos() {

        String sql = "SELECT * FROM ODONTOLOGOS";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Odontologo odontologo = new Odontologo();
                    odontologo.setId(resultSet.getInt("id"));
                    odontologo.setNumMatricula(resultSet.getString("numero_matricula"));
                    odontologo.setNombre(resultSet.getString("nombre"));
                    odontologo.setApellido(resultSet.getString("apellido"));
                    odontologos.add(odontologo);
                }
                logger.info("Lista de odontologos obtenida: " + odontologos);
            } catch (SQLException e) {
                logger.error("Error al listar los odontologos.", e);
            }
            return odontologos;

    }
}
