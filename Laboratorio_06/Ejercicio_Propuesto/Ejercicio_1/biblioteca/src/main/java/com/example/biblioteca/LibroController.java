package com.example.biblioteca;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    List<String> libros = new ArrayList<>();

    // Constructor - Inicializa con libros de ejemplo
    public LibroController() {
        libros.add("Java");
        libros.add("Spring Boot");
        libros.add("Redes");
    }

    // 1. LISTAR todos los libros
    @GetMapping
    public List<String> listar() {
        return libros;
    }

    // 2. REGISTRAR nuevo libro
    @PostMapping
    public ResponseEntity<String> agregar(@RequestBody String libro) {
        if (libro == null || libro.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El libro no puede estar vacío");
        }
        libros.add(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Libro agregado: " + libro);
    }

    // 3. BUSCAR libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<String> buscar(@PathVariable int id) {
        if (id >= 0 && id < libros.size()) {
            return ResponseEntity.ok(libros.get(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Libro no encontrado con ID: " + id);
        }
    }

    // 4. ELIMINAR libro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (id >= 0 && id < libros.size()) {
            String libroEliminado = libros.remove(id);
            return ResponseEntity.ok("Libro eliminado: " + libroEliminado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Libro no encontrado con ID: " + id);
        }
    }
}