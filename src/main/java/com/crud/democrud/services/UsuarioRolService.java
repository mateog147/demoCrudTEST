package com.crud.democrud.services;


import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioRolService{
    @Autowired
    RolRepository usuarioRolRepository;

    public ArrayList<UsuarioRolModel> obtenerUsuarios(){
        return (ArrayList<UsuarioRolModel>) usuarioRolRepository.findAll();
    }

    public UsuarioRolModel guardarUsuario(UsuarioRolModel usuarioRolModel){
        return usuarioRolRepository.save(usuarioRolModel);
    }

    public Optional<UsuarioRolModel> obtenerPorId(Long id){
        return usuarioRolRepository.findById(id);
    }


    public boolean eliminarUsuario(Long id) {
        try{
            usuarioRolRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }



}
