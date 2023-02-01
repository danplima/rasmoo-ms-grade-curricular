package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private static final String DELETE = "DELETE";

    private static final String UPDATE = "UPDATE";
    @Autowired
    private IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<Response<List<MateriaDto>>> listarMaterias() {
        Response<List<MateriaDto>> response = new Response<>();
        response.setData(this.materiaService.listar());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias())
                .withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MateriaDto>> consultarMateria(@PathVariable Long id) {
        Response<MateriaDto> response = new Response<>();

        MateriaDto materiaDto = this.materiaService.consultar(id);

        response.setData(materiaDto);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).consultarMateria(id))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).excluirMateria(id))
                .withRel(DELETE));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizaMateriar(materiaDto))
                .withRel(UPDATE));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody MateriaDto materia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.cadastrar(materia));
    }

    @PutMapping
    public ResponseEntity<Boolean> atualizaMateriar(@Valid @RequestBody MateriaDto materia) {
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.atualizar(materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> excluirMateria(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(materiaService.excluir(id));
    }
}
