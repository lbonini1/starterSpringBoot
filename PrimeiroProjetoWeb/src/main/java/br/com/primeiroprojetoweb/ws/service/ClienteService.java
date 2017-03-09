package br.com.primeiroprojetoweb.ws.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeiroprojetoweb.ws.model.Cliente;
import br.com.primeiroprojetoweb.ws.repository.ClienteRepository;

@Service
public class ClienteService {
		
		@Autowired
		ClienteRepository clienteRepository;
		
		public Cliente cadastrar(Cliente cliente){
			return clienteRepository.save(cliente);
		}
		
		public Collection<Cliente> buscarResultadosClientes(){
			return clienteRepository.findAll();
		}
		
		public Cliente buscarClientePorId(Integer id) {
			return clienteRepository.findOne(id);
		}
		
		public void excluir(Cliente cliente){
			clienteRepository.delete(cliente);
		}
		
		public Cliente alterar(Cliente cliente){
			return clienteRepository.save(cliente);
		}
}
