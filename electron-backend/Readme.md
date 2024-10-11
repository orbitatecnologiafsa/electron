# Electron Back-end

## Dependências e Configurações

O arquivo `pom.xml` carrega todas as dependências do projeto. A maioria das IDEs modernas já baixam as dependências
automaticamente. Porém, caso contrário, você precisará:

1. Baixar o [Maven](https://maven.apache.org/download.cgi)
2. Acessar o diretório do projeto no terminal
3. Rodar o comando:
   ```
   mvn clean install
   ```

Você também precisará do JDK 8 ou superior para executar o projeto.

### Configurações

O arquivo application.properties carrega as configurações do projeto, lá você poderá configurar as credenciais do MySQL

## Arquitetura do Projeto

### Camada de Repository

A camada de Repository interage com o banco de dados através do JPA. Para criar um novo repository, basta estender a
interface `JpaRepository`:
public interface NomeDoRepository extends JpaRepository<EntidadeRelacionada, TipoDaChavePrimaria> {
// Métodos personalizados, se necessário
}

### Camada de Serviço

A camada de serviço contém a lógica de negócios da aplicação. Para criar um novo serviço:
@Service
public class NomeDoServico {
private final NomeDoRepository repository;
public NomeDoServico(NomeDoRepository repository) {
this.repository = repository;
}
// Métodos do serviço
}

### Camada de Controller

Os controllers são responsáveis por receber as requisições HTTP e direcioná-las para os serviços apropriados:
@RestController
@RequestMapping("/api/nome-do-recurso")
public class NomeDoController {
private final NomeDoServico servico;
public NomeDoController(NomeDoServico servico) {
this.servico = servico;
}
// Endpoints
}

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