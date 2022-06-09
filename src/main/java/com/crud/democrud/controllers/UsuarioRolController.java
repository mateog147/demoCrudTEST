package com.crud.democrud.controllers;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.services.UsuarioRolService;
import com.crud.democrud.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/rol")
public class UsuarioRolController {
    @Autowired
    UsuarioRolService usuarioRolService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioRolModel> obtenerRoles() {
        return usuarioRolService.obtenerRoles();
    }

    @PostMapping()
    public UsuarioRolModel guardarRoles(@RequestBody UsuarioRolModel rol) {
        return this.usuarioRolService.guardarRol(rol);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioRolModel> obtenerRolPorId(@PathVariable("id") Long id) {
        return this.usuarioRolService.obtenerPorId(id);
    }


    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.usuarioRolService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el rol con id " + id;
        } else {
            return "No pudo eliminar el rol con id " + id;
        }
    }

    /*@GetMapping("/usuario/{id}")
    public ArrayList<UsuarioRolModel> obtenerRolesPorId(@PathVariable(value = "id") Long id) {
        try {
            if (!usuarioService.obtenerPorId(id).isPresent()) {
                throw new Exception("Usuario invalido");
            }
            ArrayList<UsuarioRolModel> roles = usuarioRolService.rolesPorUsuario(id);
            return  roles;
        }catch (Exception e){
            return new ArrayList<UsuarioRolModel>();
        }
    }*/


    @PostMapping(path = "/usuario/{id}")
    public String agregarRol(@PathVariable("id") Long id, @RequestBody UsuarioRolModel rolRequest) {
        Optional<UsuarioRolModel > rol = usuarioService.obtenerPorId(id).map(user -> {
            long rolId = rolRequest.getId();
            if(rolId != 0L){
                UsuarioRolModel _rol = usuarioRolService.obtenerPorId(rolId).orElse(null);
                usuarioService.agregarRol(id, _rol);
                return _rol;
            }
            UsuarioRolModel newRol = usuarioRolService.guardarRol(rolRequest);
            usuarioService.agregarRol(id, newRol);
            return newRol;
        });

        if(rol.isPresent()){
            return "Guardado con exito";
        }

        return "Error al guardar";

    }



}
