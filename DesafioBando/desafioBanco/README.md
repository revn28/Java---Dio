# Sistema Bancário Simples em Java

Este projeto é uma implementação básica de um sistema bancário em Java, focado em demonstrar conceitos de Programação Orientada a Objetos (POO), como herança, polimorfismo, interfaces e encapsulamento. Ele simula operações bancárias essenciais, login de usuários e a criação de contas por gerentes.

## 🚀 Funcionalidades

As principais funcionalidades do sistema incluem:

* **Gestão de Contas**:
    * Criação de contas Corrente e Poupança.
    * Operações de saque, depósito e transferência entre contas.
    * Funcionalidade de `investir` (transferência para conta poupança).
    * Impressão de dados da conta (titular, agência, número da conta, saldo).
* **Gestão de Usuários**:
    * Cadastro de `Clientes` e `Gerentes`.
    * Sistema de login básico com CPF e senha para ambos os tipos de usuário.
    * Definição de `Nível de Acesso` para diferentes usuários.
* **Controle de Acesso**:
    * Listagem de todas as contas do banco, acessível apenas por `Gerentes` com o nível de acesso adequado.
    * Criação de novas contas para clientes, restrita a `Gerentes`.

## 📦 Estrutura do Projeto

O projeto é organizado nos seguintes pacotes e classes:

* **`banco`**:
    * `Banco.java`: Representa o banco em si. Gerencia listas de clientes, gerentes e contas. Contém a lógica de login e criação/listagem de contas.
* **`contas`**:
    * `Conta.java` (Classe Abstrata): Base para todos os tipos de conta, com atributos e métodos comuns (saldo, sacar, depositar, transferir, imprimirDados, investir).
    * `contaCorrente.java`: Estende `Conta`, representando uma conta corrente.
    * `contaPoupanca.java`: Estende `Conta`, representando uma conta poupança.
    * `Iconta.java` (Interface): Define o contrato para as operações que uma conta deve possuir.
* **`usuario`**:
    * `Cliente.java`: Representa um cliente do banco, com dados pessoais e capacidade de autenticação.
    * `Gerente.java`: Representa um gerente do banco, com dados pessoais, nível de acesso e capacidade de autenticação.
    * `Iusuario.java` (Interface): Define o contrato básico para um usuário no sistema (ID, nível de acesso).
* **`main.java`**: A classe principal que demonstra a utilização do sistema, criando usuários, realizando logins, criando contas e efetuando operações.

## 🛠️ Como Compilar e Executar

Para compilar e executar este projeto, você precisará ter o Java Development Kit (JDK) instalado em sua máquina.

1.  **Clone ou Baixe o Projeto**:
    ```bash
    # Se estiver usando Git
    git clone <url_do_seu_repositorio>
    cd <nome_da_pasta_do_projeto>
    ```
    Ou baixe os arquivos diretamente e coloque-os em uma pasta.

2.  **Organize os Arquivos**:
    Certifique-se de que os arquivos `.java` estejam organizados em suas respectivas pastas de pacotes:
    ```
    .
    ├── src/
    │   ├── main.java
    │   ├── banco/
    │   │   └── Banco.java
    │   ├── contas/
    │   │   ├── Conta.java
    │   │   ├── contaCorrente.java
    │   │   ├── contaPoupanca.java
    │   │   └── Iconta.java
    │   └── usuario/
    │       ├── Cliente.java
    │       ├── Gerente.java
    │       └── Iusuario.java
    ```
    *(**Nota**: Se os seus arquivos estão todos na raiz e não em pastas de pacotes, você precisará remover a declaração `package ...;` do início de cada arquivo ou organizá-los em pastas correspondentes aos pacotes.)*

3.  **Compilar o Código**:
    Abra o terminal ou prompt de comando na raiz do projeto (onde a pasta `src` estaria) e compile todos os arquivos Java:
    ```bash
    javac src/main.java src/banco/*.java src/contas/*.java src/usuario/*.java
    ```
    *(Dependendo da sua estrutura de diretórios e como você está compilando, pode ser necessário ajustar o caminho. Se você usa um IDE como VS Code, IntelliJ ou Eclipse, a compilação é automática.)*

4.  **Executar o Programa**:
    Após a compilação bem-sucedida, execute a classe `main`:
    ```bash
    java -cp src main
    ```

## 💡 Exemplo de Uso

Ao executar o programa, você verá no console uma simulação das seguintes operações:

* Registro de clientes e um gerente no banco.
* Tentativas de login (sucesso e falha).
* Criação de contas (corrente e poupança) para clientes pelo gerente.
* Operações de depósito, saque e transferência.
* Demonstração do método `investir`.
* Listagem de todas as contas do banco, acessível apenas pelo gerente logado.

## 💻 Tecnologias Utilizadas

* **Java 8+** (ou versão compatível)

## 🔮 Próximos Passos e Possíveis Melhorias

Este é um ponto de partida. Aqui estão algumas ideias para expandir e aprimorar o projeto:

* **Interface de Usuário**: Implementar uma interface mais amigável (gráfica, com Swing/JavaFX, ou até mesmo um menu interativo no console).
* **Persistência de Dados**: Salvar os dados do banco (clientes, contas, transações) em arquivos (CSV, JSON) ou em um banco de dados (SQLite, MySQL) para que as informações não se percam ao fechar o programa.
* **Transações Detalhadas**: Registrar um histórico de todas as transações (depósitos, saques, transferências) para cada conta.
* **Validações Robustas**: Adicionar validações mais completas para entradas de usuário (ex: CPF, e-mail, formato de data).
* **Tratamento de Exceções**: Implementar um tratamento de exceções mais granular para cenários como saldo insuficiente, conta não encontrada, etc.
* **Segurança**: Implementar técnicas de hash para senhas (em vez de armazenar em texto puro).
* **Melhorias na Interface `Iusuario`**: Revisar se métodos como `setId` e `setNivelAcesso` deveriam estar na interface, ou se são mais específicos das classes concretas.
* **Transferência para `BigDecimal`**: Para maior precisão em cálculos financeiros, migrar o `saldo` e valores de transação de `double` para `java.math.BigDecimal`.

## 🤝 Autor

Seu Nome / Nome do Time

---
