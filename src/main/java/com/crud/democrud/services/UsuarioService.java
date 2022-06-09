package com.crud.democrud.services;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }


    public ArrayList<UsuarioModel>  obtenerPorPrioridad(Integer prioridad) {
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public boolean eliminarUsuario(Long id) {
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public UsuarioModel actualizarUsuario(Long id, UsuarioModel usuario){
        Logger logger = Logger.getLogger("logger");
        return usuarioRepository.findById(id)
                .map(user -> {
                    logger.info("Lo encontre");
                    user.setNombre(usuario.getNombre());
                    user.setEmail(usuario.getEmail());
                    user.setPrioridad(usuario.getPrioridad());
                    return usuarioRepository.save(user);
                })
                .orElseGet(() -> {
                    logger.info("Me armo otro");
                    usuario.setId(id);
                    return usuarioRepository.save(usuario);
                });
    }

    public UsuarioModel agregarRol(Long id, UsuarioRolModel rol){
        return usuarioRepository.findById(id)
                .map(user -> {
                    user.addRol(rol);
                    return usuarioRepository.save(user);
                })
                .orElseGet(() -> {

                    return null;
                });
    }


    
}