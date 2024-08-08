package Dao.implementacion;

import Dao.iDao;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class OdontologoDao<T> implements iDao<T> {
public static final Logger logger = Logger.getLogger(OdontologoDao.class);

    private List<T> listOdontologo;

    public OdontologoDao() {
        this.listOdontologo = new ArrayList<>();
    }

    @Override
    public T guardar(T t) {
        this.listOdontologo.add(t);
        logger.info("odontologo guardado exitosamente");
        return t;
    }

    @Override
    public List<T> listarTodos() {
        logger.info("lista de odontologo obtenidos exitosamente");
        return new ArrayList<>(listOdontologo);
    }
}
