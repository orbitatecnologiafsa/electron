# Arquitetura do Projeto

Utilizaremos a seguinte arquitetura:

REPOSITORY -> SERVICE -> CONTROLLER -> MODULES

## Estrutura do Projeto

### schema.prisma
Neste arquivo, temos a definição das tabelas. É necessário criar endpoints para todas as funcionalidades relevantes.

### main.ts
Este arquivo contém a configuração do projeto. Nele, iniciamos o NestJS e passamos a configuração do aplicativo.

## Camadas da Arquitetura

### Repository
- Responsável pela comunicação com o banco de dados através do Prisma ORM.

### Service
- Contém a lógica de negócios da aplicação.

### Controller
- Recebe as requisições.
- Chama o serviço apropriado.
- Retorna a resposta ao cliente.

### Modules
- Agrupam os controllers, services e repositories relacionados.

## Fluxo de Trabalho com Git

### Criando e Usando Branches

1. Crie uma nova branch para sua feature:
   ```bash
   git checkout -b nome-da-sua-feature
   ```

2. Faça suas alterações e commits na nova branch:
   ```bash
   git add .
   git commit -m "Descrição das alterações"
   ```

3. Envie a branch para o repositório remoto:
   ```bash
   git push origin nome-da-sua-feature
   ```

### Fazendo Merge para a Branch Principal

1. Volte para a branch principal:
   ```bash
   git checkout master
   ```

2. Atualize a branch principal com as últimas alterações do repositório remoto:
   ```bash
   git pull origin master
   ```

3. Faça o merge da sua feature branch:
   ```bash
   git merge nome-da-sua-feature
   ```

4. Resolva quaisquer conflitos, se houver.

5. Faça o commit do merge:
   ```bash
   git commit -m "Merge da feature nome-da-sua-feature"
   ```

6. Envie as alterações para o repositório remoto:
   ```bash
   git push origin main
   ```

7. Opcional: Exclua a branch local após o merge:
   ```bash
   git branch -d nome-da-sua-feature
   ```

Lembre-se de sempre manter sua branch principal atualizada e fazer pull antes de criar novas branches ou fazer merge.


