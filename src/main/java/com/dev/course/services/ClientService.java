package com.dev.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dev.course.domain.Client;
import com.dev.course.dto.ClientDTO;
import com.dev.course.repositories.ClientRepository;
import com.dev.course.services.exceptions.DataIntegrityException;
import com.dev.course.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repo;
	
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Not found with id: "+ id + " type: " + Client.class.getSimpleName()));
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public Client insert(Client cli) {
		cli.setId(null);
		return repo.save(cli);
	}
	
	public List<Client> findAll(){
		return repo.findAll();
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete CLIENT, as the object has associations ID: "+id);
		}
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public Client fromClient(ClientDTO cliDTO) {
		return new Client(cliDTO.getId(), cliDTO.getName(), cliDTO.getEmail(), null, null);
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}
