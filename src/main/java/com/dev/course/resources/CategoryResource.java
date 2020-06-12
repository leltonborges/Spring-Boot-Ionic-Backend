package com.dev.course.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.course.domain.Category;
import com.dev.course.dto.CategoryDTO;
import com.dev.course.services.CategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@ApiOperation(value="Busca por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) {
		Category obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value="Insere categoria")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDTO) {
		Category obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value="Atualiza categoria")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO objDTO){
		Category obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Deleta categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Não é possível excluir uma categoria que possui produtos"),
			@ApiResponse(code = 404, message = "Código inexistente") })
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Retorna todas as categorias")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<Category> list = service.findAll();
		List<CategoryDTO> listDTO= list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation(value="Retorna todas as categorias com paginação")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPages, 
			@RequestParam(value = "ordeBy", defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<Category> list = service.findPage(page, linesPerPages, orderBy, direction);
		Page<CategoryDTO> listDTO = list.map(obj -> new CategoryDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
}
