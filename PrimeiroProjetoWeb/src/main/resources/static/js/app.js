//Cria o modulo principal
var app = angular.module("appCliente", []);

//Cria um controlador para a pagina
app.controller("indexController", function ($scope, $http){
	
	$scope.objCliente = {};
	$scope.clientes = [];
	
	$scope.listarClientes = function (){
		$http({method:'GET', url:'http://localhost:8080/clientes'})
			.then(function retornaSucesso(response){
				$scope.clientes=response.data;
				
				console.log(response.data);
				console.log(response.status);
		},
				function retornaInsucesso(response){
				console.log(response.data);
				console.log(response.status);
		})
	}
	
	$scope.cadastrarClientes = function (){
		$http({method:'POST', url:'http://localhost:8080/clientes', data:$scope.objCliente})
			.then(function cadastradoComSucesso(response){
				$scope.clientes.push(response.data);
				$scope.listarClientes();
				$scope.objCliente = {};
		},
				function retornaInsucessoNoCadastro(response){
				console.log(response.data);
				console.log(response.status);
		})
	}
	
	$scope.excluirClientes = function (cliente){
		$http({method:'DELETE', url:'http://localhost:8080/clientes/'+cliente.id})
			.then(function cadastradoComSucesso(response){
				//carrega posição do a ser excluída do array;  
				pos = $scope.clientes.indexOf(cliente);
				// utiliza posição para excluir via splice; 
				$scope.clientes.splice(pos, 1);
				$scope.listarClientes();
		},
				function retornaInsucessoNoCadastro(response){
				console.log(response.data);
				console.log(response.status);
		})
	}
	
	
	$scope.alterarClientes = function(cliente){
		//angular.copy salva o objeto atual impedindo a alteração direta no DOM
		$scope.objCliente = angular.copy(cliente);
		$scope.listarClientes();
	}
	
	$scope.cancelarAlteracao = function(){
		$scope.objCliente = {};
	}
	
	$scope.listarClientes();
	
});
	
	
