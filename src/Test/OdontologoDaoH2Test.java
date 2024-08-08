package Dao;

import Model.Odontologo;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OdontologoDaoH2Test {
    private  static IDao<Odontologo> odontologoIDaoH2 = new OdontologoDAOH2();
    @BeforeClass
    public static void guardarOdontologos() {
        odontologoIDaoH2.guardar(new Odontologo("Pepe", "Ruiz", "12345"));
        odontologoIDaoH2.guardar(new Odontologo("Jimmy", "Gonzales", "1yu345"));
        odontologoIDaoH2.guardar(new Odontologo("Julia", "Arguedas", "1yu2i45"));
    }

    @Test
    public  void guardarYBuscarOdontologo() {
        odontologoIDaoH2.guardar(new Odontologo("Ana", "Casas", "12935"));
        Odontologo odontologoNuevo = odontologoIDaoH2.buscar("Ana");
        Assert.assertNotNull(odontologoNuevo);
        Assert.assertEquals(odontologoNuevo.getNombre(), "Ana");

    }

    @Test
    public  void listarTodosLosOdontologosTest() {
        List<Odontologo> odontologos = odontologoIDaoH2.listarTodos();
        Assert.assertTrue(odontologos.size() > 0);
    }
}

