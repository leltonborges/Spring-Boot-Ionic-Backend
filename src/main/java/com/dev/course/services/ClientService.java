package com.dev.course.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dev.course.domain.Address;
import com.dev.course.domain.City;
import com.dev.course.domain.Client;
import com.dev.course.domain.enums.Profile;
import com.dev.course.domain.enums.TypeClient;
import com.dev.course.dto.ClientDTO;
import com.dev.course.dto.ClientNewDTO;
import com.dev.course.repositories.AddressRepository;
import com.dev.course.repositories.ClientRepository;
import com.dev.course.security.UserSS;
import com.dev.course.services.exceptions.AuthorizationException;
import com.dev.course.services.exceptions.DataIntegrityException;
import com.dev.course.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repo;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	@Autowired
	private S3Service s3Service;
	
	public Client find(Integer id) {
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Not found with id: " + id + ", type: " + Client.class.getSimpleName()));
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	@Transactional
	public Client insert(Client cli) {
		cli.setId(null);
		
		cli = repo.save(cli);
		addressRepository.saveAll(cli.getAddresses());
		return cli;
	}

	public List<Client> findAll() {
		return repo.findAll();
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"It is not possible to exclude a CLIENT with related ordes, as the object has associations ID: " + id);
		}
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public Client fromClient(ClientDTO cliDTO) {
		return new Client(cliDTO.getId(), cliDTO.getName(), cliDTO.getEmail(), null, null, null);
	}
	public Client fromClient(ClientNewDTO cliNewDTO) {
		Client cli = new Client(null, cliNewDTO.getName(), cliNewDTO.getEmail(), cliNewDTO.getCpfOuCnpj(),
				TypeClient.toEnum(cliNewDTO.getTipo()), passEncoder.encode(cliNewDTO.getPasswoed()));
		City city = new City(cliNewDTO.getCidadeId(), null, null);
		Address addres = new Address(null, cliNewDTO.getLogradouro(), cliNewDTO.getNumero(), cliNewDTO.getComplemento(),
				cliNewDTO.getBairro(), cliNewDTO.getCep(), cli, city);
		cli.getAddresses().add(addres);
		cli.getPhones().add(cliNewDTO.getTelefone1());
		if (cliNewDTO.getTelefone2() != null) {
			cli.getPhones().add(cliNewDTO.getTelefone2());
		}
		if (cliNewDTO.getTelefone3() != null) {
			cli.getPhones().add(cliNewDTO.getTelefone3());
		}
		return cli;
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile);
	}
}
