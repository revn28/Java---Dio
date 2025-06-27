# Gerenciador de Eventos Pessoal
Este é um aplicativo de console simples desenvolvido em Java para ajudar usuários a gerenciar eventos e suas participações. Ele permite cadastrar, listar e excluir eventos, além de confirmar e cancelar a presença em eventos criados por outros usuários. O aplicativo utiliza SQLite como banco de dados para persistência dos dados.

## Funcionalidades Principais

### Autenticação de Usuários:
Cadastro: Crie uma nova conta de usuário com email, senha, nome, apelido e outros dados pessoais. O email é único e verificável.
Login: Acesse o sistema com suas credenciais de email e senha.

### Gerenciamento de Eventos:
Cadastro de Eventos: Crie novos eventos informando nome, endereço, categoria, descrição, e o mais importante: horário de início e término (com validação de formato e lógica de tempo).

### Listar Meus Eventos Criados: 
Visualize todos os eventos que você cadastrou, ordenados cronologicamente.

### Excluir Eventos: 
Remova eventos que você criou, com uma etapa de confirmação. A exclusão de um evento também cancela automaticamente todas as participações associadas a ele.

### Gerenciamento de Participações:

Confirmar Presença em Evento: Confirme sua participação em qualquer evento disponível no sistema, mesmo aqueles criados por outros usuários.

Cancelar Presença em Evento: Cancele sua participação em eventos que você havia confirmado anteriormente.

Listar Eventos Confirmados: Veja uma lista de todos os eventos nos quais você confirmou presença, também ordenados cronologicamente.

### Controle de Horários:
O programa exibe os eventos de forma ordenada pelos seus horários de início.
Indica claramente se um evento está OCORRENDO AGORA! ou se já foi Concluído, com base no horário atual do sistema.

### Tecnologias Utilizadas
Java 11+ (ou versão compatível)
SQLite: Banco de dados leve e embarcado para persistência de dados.
JDBC: API Java para conexão com o banco de dados.
java.time API (JSR 310): Para manipulação moderna e eficiente de datas e horas.

### Como Compilar e Executar
Para rodar este aplicativo, você precisará ter o Java Development Kit (JDK) instalado e o driver JDBC para SQLite.
Baixe o Driver SQLite JDBC:
Faça o download do arquivo .jar do driver SQLite JDBC mais recente (ex: sqlite-jdbc-X.Y.Z.jar) em https://github.com/xerial/sqlite-jdbc/releases.
Crie uma pasta chamada lib na raiz do seu projeto e coloque o arquivo .jar do driver lá.
Estrutura do Projeto:
Certifique-se de que seus arquivos .java estejam organizados da seguinte forma:

## Estrutura
.
├── App.java
├── Cadastro.java
├── Evento.java
├── EventoDAO.java
├── ParticipacaoEventoDAO.java
├── TelaInicial.java
├── UsuarioDAO.java