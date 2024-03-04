# BarberSchedule

API do projeto Barber Schedule - Controle de agenda para berbeiros 

# Sistema de Agendamento de Barbearia

Este documento descreve os requisitos e endpoints da API para o sistema de agendamento de barbearia.

## Requisitos

- [ ] CRUD serviço
- [ ] CRUD perfil
- [ ] Autenticação
- [ ] Dashboard

## Documentação

### Endpoints

- [Listar Serviços](#listar-serviços)
- [Adicionar Serviço](#adicionar-serviço)
- [Excluir Serviço](#excluir-serviço)
- [Editar Perfil](#editar-perfil)

## Listar Serviços

`GET` /servicos

Retorna um array com todos os serviços cadastrados.

#### Exemplo de Resposta

```js
[
    {
        "id": 1,
        "nome": "Corte de Cabelo",
        "icone":"tesoura"
        "preco": 30.0
    },
    {
        "id": 2,
        "nome": "Barba",
        "icone":"bigode"
        "preco": 20.0
    }
]

```


### Código de Status

|Código	| Descrição
|-------|--------------
|200	| Sucesso
|401	|Não autenticado


## Adicionar Serviço

`POST` /servicos

Adiciona um novo serviço com os dados enviados no corpo da requisição.

### Corpo da Requisição
Campo | Tipo   | Obrigatório | Descrição
------|--------|--------------|-----------------
`nome`| String | ✅       | Nome do serviço
`preço`|  Number | ✅      | Preço do serviço
`icone`| img | ✅| Imagem que representa o serviço 

```js
{
    "nome": "Penteado",
    "icone" : "secador",
    "preco": 40.0
}
```

### Exemplo de Resposta


```js
{
    "id": 3,
    "nome": "Penteado",
    "icone": "secador",
    "preco": 40.0
}
```


### Código de Status

|Código	| Descrição
|-------|--------------
|201	| Conteúdo criado com sucesso
|400	| Erro na validação dos parâmetros 
|401	| Não autenticado


## Excluir Serviço
`DELETE` /servicos/{id}

Exclui o serviço com o ID especificado.

### Código de Status

|Código	| Descrição
|-------|--------------
|204	| Conteúdo excluído com sucesso
|401	| Não autenticado


## Editar Perfil
`PUT` /perfil

Atualiza o perfil do usuário com os dados enviados no corpo da requisição.


### Corpo da Requisição

Campo | Tipo   | Obrigatório | Descrição
------|--------|--------------|-----------------
`nome`|String | ✅  | Novo nome para o perfil
`email`|String | ✅ | Novo email para o perfil
`senha`|String |✅ | Nova senha para o perfil 
`imagem`| img | ❌  | Imagem para o perfil

```js

{
    "nome": "Novo Nome",
    "email": "novoemail@example.com",
    "senha": "novaSenha"
    "imagem": "[arquivo de imagem]"
}
```

### Exemplo de Resposta
```js

{
    "id": 1,
    "nome": "Novo Nome",
    "email": "novoemail@example.com",
    "Senha" : "senha",
    "imagem_url": "path imagem"
}
```

### Código de Status
|Código	|Descrição
---|---
|200	|Perfil editado com Sucesso
|400	|Falha na validação  dos dados (campos vazios ou formato inválido)
|401	|Não autenticado
