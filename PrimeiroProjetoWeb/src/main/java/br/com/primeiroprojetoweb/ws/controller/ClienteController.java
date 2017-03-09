package br.com.primeiroprojetoweb.ws.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeiroprojetoweb.ws.model.Cliente;
import br.com.primeiroprojetoweb.ws.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	//End points
	
	//Cadastrar
	@RequestMapping(method=RequestMethod.POST, value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
		Cliente clienteCadastrado = clienteService.cadastrar(cliente);
		return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);
	}
	
	//Buscar
	@RequestMapping(method=RequestMethod.GET, value="/clientes", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarClientes(){
		Collection<Cliente> clientesEncontrados = clienteService.buscarResultadosClientes();
		return new ResponseEntity<>(clientesEncontrados, HttpStatus.OK);
	}
	
	//Excluir
	@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id){
		//Carrega o objeto a ser excluído baseado no id do Cliente
		Cliente clienteParaExclusao = clienteService.buscarClientePorId(id);
		//verifica se o objeto foi encontrado pela requisição, caso contrario devolve status NOT FOUND
		if(clienteParaExclusao==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// caso o objeto seja encontrado, realiza a exclusão do mesmo na lista e devolve status OK
		clienteService.excluir(clienteParaExclusao);
		return new ResponseEntity<Cliente>(HttpStatus.OK);	
	}
	
	//Alterar
	@RequestMapping(method=RequestMethod.PUT, value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente){
		Cliente clienteAlterado = clienteService.alterar(cliente);
		return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);
	}

}

