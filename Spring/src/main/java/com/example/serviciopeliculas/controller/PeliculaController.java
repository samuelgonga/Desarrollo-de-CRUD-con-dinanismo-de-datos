package com.example.serviciopeliculas.controller;

import com.example.serviciopeliculas.entity.Pelicula;
import com.example.serviciopeliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "http://localhost:5173/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
public class PeliculaController {

    @Autowired
    PeliculaService service;

    
    @GetMapping
    public List<Pelicula> getAll(){
        return service.getListPelicula();
    }

    @GetMapping("{id}")
    public Pelicula getByIdPelicula(@PathVariable Long id){
        return service.getById(id).orElse(null);
    }

    @PostMapping
    public Pelicula savePelicula(@RequestBody Pelicula bean){
        return service.savePelicula(bean);
    }


}
