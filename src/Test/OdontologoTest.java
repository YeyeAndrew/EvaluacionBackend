import Dao.OdontologoDao;
import Dao.iDao;
import Model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


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
        Assert.assertEquals(1, odontologos.size());
    }
}