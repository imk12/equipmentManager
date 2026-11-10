# EquipmentManager
Um sistema que permite o cadastramento de equipamentos, empregados e empréstimos entre os dois.

# Funcionalidades
- Registro de equipamentos
- Gerenciamento de equipamentos
- Registro de trabalhadores
- Gerenciamento dos trabalhadores
- Registro de empréstimos
- Gerenciamento de empréstimos

## Equipamentos

Permite o registro dos equipamentos, cada equipamento contém suas propriedades, sendo elas:

- Tag
- Name
- Status

Se o equipamento estiver cadastrado em algum empréstimo não é possível remove-lo mais.

### Tag

Marcador único do equipamento

### Name

Apelido ou nome da maquina, ex.: Intel-I5 / Computador da Intel-I5

### Status

Estado atual do equipamento, eles são:

1. AVAILABLE
2. BORROWED
3. IN_MAINTENANCE
4. DISCARDED

## Trabalhadores

Permite o registo dos funcionários, cada funcionário tem suas propriedades, sendo elas:

- Name
- Email
- Sector

### Name

Nome do trabalhador

### Email

Email do trabalhador

> Não é possível ter mais de um funcionário com o mesmo email

### Sector

Setor do trabalhador, sendo eles:

1. RH
2. TI
3. COMMERCIAL
4. FINANCIAL
5. MARKETING
6. OPERATION
7. OTHERS

## Empréstimos

São a conexão do equipamento com o funcionário, ele liga os dois para informar qual funcionário está usando o equipamento.

Ele registra a data do inicio e término do empréstimo, caso não tenha terminado não terá sido registrado, somente o inicio

os empréstimos não podem ser deletados, uma vez que são criados não podem ser alterados ou deletados.

# Comandos

## Equipamentos

Para registrar uma máquina é necessário acessar o url/equipments e mandar uma json com corpo assim:

``{
  "name": "nome da maquina",
  "assetTag": "tag"
``}

Na criação retorna se o Id.

Dar get retorna todos os equipamentos e get com id devolve mais informações do equipamento

## Trabalhador

``{
  "name": "nome do trabalhador",
  "email": "email do trabalhador",
  "sector": "setor"
``}


Na criação retorna se o Id.

Dar get retorna todos os trabalhadores e get com id devolve mais informações do trabalhador

## Empréstimo

``{
  "employeeId": "id do trabalhador"
  "equipmentId": "id do equipamento"
``}


Na criação retorna se o Id.

Dar get retorna todos os empréstimos e get com id devolve mais informações do empréstimo

# Salvamento

O sistema todo roda em um H2 ou seja, precisa-se de uma maquina rodando para atualizar e obter, eles ficam salvos em um arquivo no computador, para alterar a o login e senha altera a configuração do ``application.properties``:

``spring.datasource.username=sa`` define o nome do admin

``spring.datasource.password=`` define a senha do admin
