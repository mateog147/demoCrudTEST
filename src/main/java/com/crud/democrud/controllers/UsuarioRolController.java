package com.crud.democrud.controllers;

import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.services.UsuarioRolService;
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

    @GetMapping()
    public ArrayList<UsuarioRolModel> obtenerUsuarios() {
        return usuarioRolService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioRolModel guardarUsuario(@RequestBody UsuarioRolModel rol) {
        return this.usuarioRolService.guardarUsuario(rol);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioRolModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
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



}
