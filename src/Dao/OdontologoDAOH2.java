package Dao;

import Model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, NUMERO_MATRICULA) VALUES(?,?,?)";
    private static final String SQL_SELECT_ONE = "SELECT * FROM ODONTOLOGOS WHERE NOMBRE=?";

    private Connection connection;


    public OdontologoDAOH2(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Iniciando las operaciones de guardado de un odontologo");
        try {
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getNombre());
            psInsert.setString(2, odontologo.getApellido());
            psInsert.setString(3, odontologo.getNumMatricula());
            psInsert.execute(); // Se van a generar ID
            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            logger.error("Conexion fallida: " + e.getMessage());
        }
        return odontologo;
    }

    public Odontologo buscar(String nombre) {
        Odontologo odontologo = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ONE);
            preparedStatement.setString(1, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                odontologo = new Odontologo();
                odontologo.setId(resultSet.getInt("ID"));
                odontologo.setNumMatricula(resultSet.getString("NUMERO_MATRICULA"));
                odontologo.setNombre(resultSet.getString("NOMBRE"));
                odontologo.setApellido(resultSet.getString("APELLIDO"));
            }
        } catch (SQLException e) {
            logger.error("Error al buscar el odontologo.", e);
        }
        return odontologo;
    }

    public List<Odontologo> listarTodos() {
        String sql = "SELECT * FROM ODONTOLOGOS";
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo();
                odontologo.setId(resultSet.getInt("ID"));
                odontologo.setNumMatricula(resultSet.getString("NUMERO_MATRICULA"));
                odontologo.setNombre(resultSet.getString("NOMBRE"));
                odontologo.setApellido(resultSet.getString("APELLIDO"));
                odontologos.add(odontologo);
            }
            logger.info("Lista de odontologos obtenida: " + odontologos);
        } catch (SQLException e) {
            logger.error("Error al listar los odontologos.", e);
        }
        return odontologos;
    }
}
