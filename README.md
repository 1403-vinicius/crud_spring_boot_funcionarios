# crud_spring_boot_funcionarios
Executar bootRun do Gradle


Listar Funcionarios Por Departamento: http://localhost:8080/api/listaFuncionarioDepartamento/1

Listar Funcionarios: http://localhost:8080/api/listaFuncionarios

Listar Cargos: http://localhost:8080/api/listaCargos

Listar Departamentos: http://localhost:8080/api/listaDepartamentos



Criar Cargo: http://localhost:8080/api/criarCargo
{
    "carco_name": "Controladoria"
}

Criar Departamento: http://localhost:8080/api/criarDepartamento

{
    "departamento_name": "Controladoria"
}

Criar Funcionario: http://localhost:8080/api/criarFuncionario
{
    "funcionario_name": "Vinicius dos Santos",
    "funcionario_age": 35,
    "funcionario_birtday": "23/10/2020",
    "funcionario_document": "404697793",
     "cargo":{
        "cargo_id": 1,
        "cargo_name": "Gestor"
     }
}

FuncionarioDepartamento: http://localhost:8080/api/funcionarioDepartamento
      {
        "funcionario_id": 1,
        "funcionario_name": "Vinicius dos Santos",
        "funcionario_age": 35,
        "funcionario_birtday": "14/03/1985",
        "funcionario_document": "404697793",
        "deletado": false,
        "departamentos": [
            {
                "departamento_id": 1,
                "departamento_name": "Tecnologia da Informação",
                "deletado": false
            }
        ],
        "cargo": {
            "cargo_id": 1,
            "cargo_name": "Gestor",
            "deletado": false
        }
    }
    
    Adicionar Chefe ao Departamento: http://localhost:8080/api/chefeDepartamento
        {
       "departamento_id": 1,
       "departamento_name": "Tecnologia da Informação",
       "deletado": false
       "chefeDepartamento":
       {
        "funcionario_id": 1,
        "funcionario_name": "Vinicius dos Santos",
        "funcionario_age": 35,
        "funcionario_birtday": "14/03/1985",
        "funcionario_document": "404697793",
        "deletado": false,
        "cargo": {
            "cargo_id": 1,
            "cargo_name": "Gestor",
            "deletado": false
        }
   }
    
