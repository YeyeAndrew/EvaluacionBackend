package Dao;

import Model.Odontologo;

import java.util.List;

public interface iDao<T> {
    T guardar (T t);
    List<T> listarTodos();

}
