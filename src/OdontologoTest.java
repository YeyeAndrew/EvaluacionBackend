import Dao.OdontologoDao;
import Dao.iDao;
import Model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoTest {
    public static final Logger logger = Logger.getLogger(OdontologoTest.class);

    private OdontologoDao<Odontologo> dao;
    @Before
    public void guardar(){
        dao= new OdontologoDao<>();
        dao.guardar(new Odontologo("123", "Carlos", "Quintero"));

    }
    @Test
    public void guardarOdontologo(){
        List<Odontologo> odontologos = dao.listarTodos();
        Assertions.assertEquals(1, odontologos.size());
    }
}