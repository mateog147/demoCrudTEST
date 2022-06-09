package com.crud.democrud.repositories;

import com.crud.democrud.models.UsuarioRolModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RolRepository extends CrudRepository<UsuarioRolModel, Long> {
    //public abstract ArrayList<UsuarioRolModel> findRolByUsuario(Long usuario);
}
