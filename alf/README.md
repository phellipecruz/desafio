# Getting Started

### Reference Documentation

* Antes de avaliarem meu desafio, tenho uma observação a fazer.

Existe um erro na especificação do desafio, mais precisamente em relação ao cálculo da nota do aluno.
Do jeito que está especificado, o resultado máximo que um aluno conseguirá obter se acertar todas as questões da prova será 1.
Vou dar um exemplo para vocês entenderem melhor:

Supondo que uma prova tenha 10 questões e os pesos de cada questão inicia em 1 e termina em 10.
Cálculo da nota do aluno, onde . significa uma multiplicação:


1.1 + 1.2 + 1.3 + 1.4 + 1.5 + 1.6 + 1.7 + 1.8 + 1.9 + 1.10          
___________________________________________________________ = 
         1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10


1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10
_______________________________________ = 
                  55

55
____ = 1
55                                                                



O código do desafio, foi feito conforme o que estava especificado no documento, então, como existe esse erro de especificação, o método listar alunos aprovados sempre irá trazer vazio, porque nunca terá aluno com média superior a 7.
O método pode ser testado alterando uma condição do método listarAlunosAprovados da classe NotaAlunoRepositoryImpl.

Onde tem:

builder.append("HAVING AVG(na.NOTA) > 7 ");

Colocar, por exemplo:

builder.append("HAVING AVG(na.NOTA) >= 1 ");

Ou qualquer outra média entre 0 e 1.

Espero ter conseguindo mostrar a vocês que não é um erro de implementação, mas sim um erro de especificação, e com isso não perder pontos quanto a essa parte do desafio.


### Guides

Para executar o projeto, faça o clone do repositório do GitHub e baixe as dependências do mesmo.
De preferência, utilize o Ecplise ou o STS para rodar o projeto.
Foi utilizado para persistência dos dados o banco de dados embarcado H2 e para desenvolvimento da API Java 8, Lombok e Spring Boot.
Será necessário incluir o lombok a IDE já que todos o getters, setters, equals e hashcode são gerados por ele. O site para download e instruções de como instalar se encontra em https://projectlombok.org
Todas as configurações para acesso ao banco estão no arquivo application.properties, não sendo necessário fazer nenhuma adicional.
Segue a abaixo a descrição de cada método, URL, método HTTP correspondente e exemplo de objeto a ser enviado para requisição.
Para acesso a console de gerenciamento do banco, pode ser acessado pelo browser através da URL localhost:8080/h2-console.
Quando aparece a janela de conexão, basta apenas apontar para o arquivo físico que contém os dados confome o exemplo abaixo:

jdbc:h2:file:C://sts-4.4.0.RELEASE//workspace-spring//alf//ALF

* Cadastrar Gabarito - Método utilizado para cadastro do gabarito da prova.
URL -> localhost:8080/alf/prova/cadastrar_gabarito
Método HTTP -> POST
Objeto a ser enviado -> 
{
   "questoes": [
       {
        "enunciado": "Quem descobriu o Brasil?",
        "peso": 1,
        "gabarito": {
            "resposta": "a"
            }            
        },
        {
        "enunciado": "Quem descobriu a América?",
        "peso": 1,
        "gabarito": {
            "resposta": "b"
            }            
        },
        {
        "enunciado": "Qual a capital do Brasil?",
        "peso": 1,
        "gabarito": {
            "resposta": "c"
            }            
        },
        {
        "enunciado": "Qual a capital de Portugal?",
        "peso": 1,
        "gabarito": {
            "resposta": "d"
            }            
        },
        {
        "enunciado": "Qual a capital do Acre?",
        "peso": 1,
        "gabarito": {
            "resposta": "e"
            }            
        }
    ]
}

* Cadastrar Aluno - Método utilizado para cadastro do aluno.
URL -> localhost:8080/alf/aluno/cadastrar_aluno
Método HTTP -> POST
Objeto a ser enviado -> 
{
   "nome": "Bruno Cruz"
}

* Cadastrar Nota do Aluno - Método utilizado para cadastro da nota do aluno para uma determinada prova.
URL -> localhost:8080/alf/aluno/cadastrar_resposta_aluno
Método HTTP -> PUT
* Observação - Para mais de uma prova por aluno, deve ser alterado o id do objeto prova no JSON, e se tiver mais de um aluno, mudar o campo id e também no campo id do objeto aluno no JSON.
Objeto a ser enviado -> 
{
    "id": 1,
    "provas": [
        {
            "aluno": {"id": 1},
            "prova": {"id": 1},
            "questao": {"id": 1},
            "resposta": "b"
        },
        {
            "aluno": {"id": 1},
            "prova": {"id": 1},
            "questao": {"id": 2},
            "resposta": "e"
        },
        {
            "aluno": {"id": 1},
            "prova": {"id": 1},
            "questao": {"id": 3},
            "resposta": "c"
        },
        {
            "aluno": {"id": 1},
            "prova": {"id": 1},
            "questao": {"id": 4},
            "resposta": "d"
        },
        {
            "aluno": {"id": 1},
            "prova": {"id": 1},
            "questao": {"id": 5},
            "resposta": "a"
        }
    ]
}

* Listar nota final do aluno - Método consultar as notas do aluno por cada prova.
URL -> localhost:8080/alf/aluno/nota_final/{aluno}
Método HTTP -> GET
* Observação - {aluno} é o id do aluno cadastrado

* Listar alunos aprovados - Método consultar os alunos aprovados.
URL -> localhost:8080/alf/aluno/lista_aprovados
Método HTTP -> GET
