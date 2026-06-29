package com.example.demo.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class CursoController {

    private final List<Curso> cursos = List.of(
            new Curso(1, "JavaScript Avanzado", true),
            new Curso(2, "Base de Datos", true),
            new Curso(3, "Arquitectura de Computadoras", false));

    @GetMapping("/cursos")
    public List<Curso> listarCursos() {
        return cursos;
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<Curso> buscarCursosPorId(@PathVariable int id) {
        return cursos.stream()
                .filter(e -> e.id() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cursos/filtrar")
    public List<Curso> filtrarPorActivo(@RequestParam boolean activo) {
        return cursos.stream()
                .filter(c -> c.activo() == activo)
                .toList();
    }

    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo";
    }

    @GetMapping("/saludo")
    public String holaSaludo(@RequestParam String param) {
        return "Hola " + param + "Bienvenido";
    }

    @GetMapping("/saludo2/{nombre}")
    public String holaSaludo2(@PathVariable String nombre) {
        return "Hola " + nombre + "Bienvenido";
    }

}

record Curso(int id, String nomcurso, Boolean activo) {
}