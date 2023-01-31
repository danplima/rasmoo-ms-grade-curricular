package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> listarMaterias() {
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> consultarMateria(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.consultar(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody MateriaDto materia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.cadastrar(materia));
    }

    @PutMapping
    public ResponseEntity<Boolean> atualizar(@Valid @RequestBody MateriaDto materia) {
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.atualizar(materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> excluir(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.excluir(id));
    }
}
