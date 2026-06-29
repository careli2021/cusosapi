package com.example.demo.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpleadoController {

    private final List<Empleado> empleados = List.of(
        new Empleado(1, "Ana", "TI", 4500),
        new Empleado(2, "Luis", "RRHH", 3800),
        new Empleado(3, "María", "TI", 5200)
    );

    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados() {
        return empleados;
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable int id) {
        return empleados.stream()
                .filter(e -> e.id() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

record Empleado(int id, String nombre, String departamento, double salario) {}