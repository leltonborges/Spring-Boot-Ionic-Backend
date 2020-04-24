package com.dev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dev.course.domain.Category;
import com.dev.course.dto.CategoryDTO;
import com.dev.course.repositories.CategoryRepository;
import com.dev.course.services.exceptions.DataIntegrityException;
import com.dev.course.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: "+id+ " type: "+ Category.class.getSimpleName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		Category newCat = find(obj.getId());
		updateData(newCat, obj);
		return repo.save(newCat);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete, as the object has associations");
		}
	}
	
	public List<Category> findAll(){
		return repo.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPages, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO dto) {
		return new Category(dto.getId(), dto.getName());
	}
	
	private void updateData(Category newCat, Category obj) {
		newCat.setName(obj.getName());
	}
}