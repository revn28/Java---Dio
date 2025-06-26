
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException; // Importação para LocalDateTime
import java.util.List; // Importação para DateTimeFormatter
import java.util.Scanner; // Importação para lidar com erros de parsing

public class TelaInicial {

    private EventoDAO eventoDAO;
    private ParticipacaoEventoDAO participacaoEventoDAO;
    private Scanner scanner;
    private Cadastro usuarioLogado;
    // Formato que o usuário deve digitar e que usaremos para exibir
    private static final DateTimeFormatter USER_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    // Formato interno para o DAO, o mesmo que EventoDAO usa para consistência
    private static final DateTimeFormatter DAO_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    public TelaInicial(Cadastro usuarioLogado, Scanner scanner) {
        this.usuarioLogado = usuarioLogado;
        this.scanner = scanner;
        this.eventoDAO = new EventoDAO();
        this.participacaoEventoDAO = new ParticipacaoEventoDAO();
        this.eventoDAO.criarTabela();
        this.participacaoEventoDAO.criarTabela();
    }

    public void exibirMenuPrincipal() {
        int opcao;
        System.out.println("\n---------------------------------");
        System.out.println("Bem-vindo(a) ao seu Menu, " + usuarioLogado.getApelido() + "!");
        System.out.println("---------------------------------");

        do {
            System.out.println("\n--- Gerenciamento de Eventos ---");
            System.out.println("1 - Cadastrar Novo Evento");
            System.out.println("2 - Listar Meus Eventos Criados");
            System.out.println("3 - Confirmar Presença em Evento");
            System.out.println("4 - Cancelar Presença em Evento");
            System.out.println("5 - Listar Eventos que Confirmei Presença");
            System.out.println("6 - Excluir Evento Criado por Mim");
            System.out.println("0 - Fazer Logout");
            System.out.print("Digite sua opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um NÚMERO.");
                scanner.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    cadastrarNovoEvento();
                    break;
                case 2:
                    listarMeusEventosCriados();
                    break;
                case 3:
                    confirmarPresencaEmEvento();
                    break;
                case 4:
                    cancelarPresencaEmEvento();
                    break;
                case 5:
                    listarMeusEventosConfirmados();
                    break;
                case 6:
                    excluirEventoCriado();
                    break;
                case 0:
                    System.out.println("Fazendo logout. Até a próxima!");
                    break;
                default:
                    if (opcao != -1) {
                        System.out.println("Opção inválida. Por favor, digite 0, 1, 2, 3, 4, 5 ou 6.");
                    }
                    break;
            }
        } while (opcao != 0);
    }

    private void cadastrarNovoEvento() {
        System.out.println("\n--- Cadastrar Novo Evento ---");

        System.out.print("Nome do Evento: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("Nome do evento não pode ser vazio.");
            return;
        }

        System.out.print("Endereço do Evento: ");
        String endereco = scanner.nextLine();

        System.out.print("Categoria do Evento (ex: Show, Palestra, Reunião): ");
        String categoria = scanner.nextLine();

        LocalDateTime horarioInicio = null;
        LocalDateTime horarioFim = null;
        boolean inputValido = false;

        while (!inputValido) {
            System.out.print("Horário de INÍCIO do Evento (formato dd/mm/aaaa HH:MM): ");
            String horarioInicioStr = scanner.nextLine();
            System.out.print("Horário de TÉRMINO do Evento (formato dd/mm/aaaa HH:MM): ");
            String horarioFimStr = scanner.nextLine();

            try {
                horarioInicio = LocalDateTime.parse(horarioInicioStr, USER_FORMATTER);
                horarioFim = LocalDateTime.parse(horarioFimStr, USER_FORMATTER);

                if (horarioFim.isBefore(horarioInicio)) {
                    System.out.println("Erro: O horário de término não pode ser antes do horário de início. Tente novamente.");
                } else {
                    inputValido = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data/hora inválido. Use dd/mm/aaaa HH:MM. Tente novamente.");
            }
        }

        System.out.print("Descrição do Evento: ");
        String descricao = scanner.nextLine();

        Evento novoEvento = new Evento(
            usuarioLogado.getId(),
            nome,
            endereco,
            categoria,
            horarioInicio, // Agora é LocalDateTime
            horarioFim,    // Novo campo
            descricao
        );

        if (eventoDAO.inserirEvento(novoEvento)) {
            System.out.println("Evento cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar evento. Tente novamente.");
        }
    }

    private void listarMeusEventosCriados() {
        System.out.println("\n--- Meus Eventos Criados ---");

        List<Evento> eventos = eventoDAO.listarEventosPorUsuario(usuarioLogado.getId());

        if (eventos.isEmpty()) {
            System.out.println("Você não possui eventos criados ainda.");
        } else {
            for (Evento evento : eventos) {
                exibirDetalhesEvento(evento);
            }
        }
    }

    private void confirmarPresencaEmEvento() {
        System.out.println("\n--- Confirmar Presença em Evento ---");
        List<Evento> todosEventos = eventoDAO.listarTodosEventos(); // Já vem ordenado pelo DAO

        if (todosEventos.isEmpty()) {
            System.out.println("Não há eventos disponíveis para confirmar presença.");
            return;
        }

        System.out.println("Eventos Disponíveis (ordenados por horário):");
        for (Evento evento : todosEventos) {
            System.out.print("ID: " + evento.getId() + " | Nome: " + evento.getNome() + " | Início: " + evento.getHorarioInicio().format(USER_FORMATTER) + " | Fim: " + evento.getHorarioFim().format(USER_FORMATTER));
            if (eventoEstaOcorrendo(evento)) {
                System.out.println(" <-- OCORRENDO AGORA!");
            } else if (evento.getHorarioInicio().isBefore(LocalDateTime.now())) {
                System.out.println(" (Já ocorreu)");
            } else {
                System.out.println(); // Apenas quebra de linha
            }
        }

        System.out.print("Digite o ID do evento para confirmar presença: ");
        try {
            int eventoId = scanner.nextInt();
            scanner.nextLine();

            Evento eventoParaConfirmar = eventoDAO.buscarEventoPorId(eventoId);
            if (eventoParaConfirmar == null) {
                System.out.println("Evento com ID " + eventoId + " não encontrado.");
                return;
            }

            String statusAtual = participacaoEventoDAO.verificarStatusPresenca(usuarioLogado.getId(), eventoId);
            if (statusAtual != null && statusAtual.equals("confirmado")) {
                System.out.println("Você já confirmou presença neste evento.");
                return;
            }

            if (participacaoEventoDAO.confirmarPresenca(usuarioLogado.getId(), eventoId)) {
                System.out.println("Sua presença no evento '" + eventoParaConfirmar.getNome() + "' foi confirmada com sucesso!");
            } else {
                System.out.println("Falha ao confirmar presença. Tente novamente.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um ID de evento válido.");
            scanner.nextLine();
        }
    }

    private void cancelarPresencaEmEvento() {
        System.out.println("\n--- Cancelar Presença em Evento ---");
        List<Evento> eventosConfirmados = participacaoEventoDAO.listarEventosConfirmadosPorUsuario(usuarioLogado.getId());

        if (eventosConfirmados.isEmpty()) {
            System.out.println("Você não confirmou presença em nenhum evento ainda.");
            return;
        }

        System.out.println("Eventos que você confirmou presença (ordenados por horário):");
        for (Evento evento : eventosConfirmados) {
            System.out.print("ID: " + evento.getId() + " | Nome: " + evento.getNome() + " | Início: " + evento.getHorarioInicio().format(USER_FORMATTER) + " | Fim: " + evento.getHorarioFim().format(USER_FORMATTER));
            if (eventoEstaOcorrendo(evento)) {
                System.out.println(" <-- OCORRENDO AGORA!");
            } else if (evento.getHorarioInicio().isBefore(LocalDateTime.now())) {
                System.out.println(" (Já ocorreu)");
            } else {
                System.out.println();
            }
        }

        System.out.print("Digite o ID do evento para cancelar sua presença: ");
        try {
            int eventoId = scanner.nextInt();
            scanner.nextLine();

            boolean encontrouEventoConfirmado = false;
            for(Evento evento : eventosConfirmados) {
                if (evento.getId() == eventoId) {
                    encontrouEventoConfirmado = true;
                    break;
                }
            }
            if (!encontrouEventoConfirmado) {
                 System.out.println("Você não confirmou presença neste evento (ID: " + eventoId + ").");
                 return;
            }

            if (participacaoEventoDAO.cancelarPresenca(usuarioLogado.getId(), eventoId)) {
                System.out.println("Sua presença no evento foi cancelada com sucesso!");
            } else {
                System.out.println("Falha ao cancelar presença. Tente novamente.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um ID de evento válido.");
            scanner.nextLine();
        }
    }

    private void listarMeusEventosConfirmados() {
        System.out.println("\n--- Meus Eventos com Presença Confirmada ---");

        List<Evento> eventosConfirmados = participacaoEventoDAO.listarEventosConfirmadosPorUsuario(usuarioLogado.getId());
        // O DAO já retorna ordenado, mas podemos garantir aqui também se necessário
        // Collections.sort(eventosConfirmados, Comparator.comparing(Evento::getHorarioInicio));

        if (eventosConfirmados.isEmpty()) {
            System.out.println("Você não confirmou presença em nenhum evento ainda.");
        } else {
            for (Evento evento : eventosConfirmados) {
                exibirDetalhesEvento(evento);
            }
        }
    }

    private void excluirEventoCriado() {
        System.out.println("\n--- Excluir Evento Criado por Mim ---");
        List<Evento> meusEventos = eventoDAO.listarEventosPorUsuario(usuarioLogado.getId()); // Já vem ordenado

        if (meusEventos.isEmpty()) {
            System.out.println("Você não possui eventos criados para excluir.");
            return;
        }

        System.out.println("Seus Eventos Criados (ordenados por horário):");
        for (Evento evento : meusEventos) {
            System.out.print("ID: " + evento.getId() + " | Nome: " + evento.getNome() + " | Início: " + evento.getHorarioInicio().format(USER_FORMATTER) + " | Fim: " + evento.getHorarioFim().format(USER_FORMATTER));
            if (eventoEstaOcorrendo(evento)) {
                System.out.println(" <-- OCORRENDO AGORA!");
            } else if (evento.getHorarioInicio().isBefore(LocalDateTime.now())) {
                System.out.println(" (Já ocorreu)");
            } else {
                System.out.println();
            }
        }

        System.out.print("Digite o ID do evento que deseja EXCLUIR: ");
        try {
            int eventoId = scanner.nextInt();
            scanner.nextLine();

            boolean eventoPertenceAoUsuario = false;
            for (Evento evento : meusEventos) {
                if (evento.getId() == eventoId) {
                    eventoPertenceAoUsuario = true;
                    break;
                }
            }

            if (!eventoPertenceAoUsuario) {
                System.out.println("Você só pode excluir eventos que você mesmo criou. ID " + eventoId + " não pertence a você.");
                return;
            }

            System.out.print("Tem certeza que deseja excluir este evento? (s/n): ");
            String confirmacao = scanner.nextLine().trim().toLowerCase();

            if (confirmacao.equals("s")) {
                if (eventoDAO.excluirEvento(eventoId)) {
                    System.out.println("Evento excluído com sucesso!");
                } else {
                    System.out.println("Falha ao excluir evento. Tente novamente.");
                }
            } else {
                System.out.println("Exclusão cancelada.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um ID de evento válido.");
            scanner.nextLine();
        }
    }

    /**
     * Método auxiliar para exibir os detalhes de um evento, incluindo o status "ocorrendo agora".
     */
    private void exibirDetalhesEvento(Evento evento) {
        System.out.println("---------------------------------");
        System.out.println("ID: " + evento.getId());
        System.out.println("Criador (ID): " + evento.getUsuarioId()); // Pode ser útil para depuração
        System.out.println("Nome: " + evento.getNome());
        System.out.println("Endereço: " + evento.getEndereco());
        System.out.println("Categoria: " + evento.getCategoria());
        System.out.println("Início: " + evento.getHorarioInicio().format(USER_FORMATTER));
        System.out.println("Fim: " + evento.getHorarioFim().format(USER_FORMATTER));
        System.out.println("Descrição: " + evento.getDescricao());

        if (eventoEstaOcorrendo(evento)) {
            System.out.println("[STATUS: OCORRENDO AGORA!]");
        } else if (evento.getHorarioFim().isBefore(LocalDateTime.now())) {
            System.out.println("[STATUS: Concluído]");
        } else {
            System.out.println("[STATUS: Agendado]");
        }
        System.out.println("---------------------------------");
    }

    /**
     * Verifica se um evento está ocorrendo no momento atual.
     * Um evento está ocorrendo se o tempo atual estiver entre o horário de início e o horário de término (inclusive o início, exclusivo o término).
     * @param evento O objeto Evento a ser verificado.
     * @return true se o evento estiver ocorrendo, false caso contrário.
     */
    private boolean eventoEstaOcorrendo(Evento evento) {
        LocalDateTime now = LocalDateTime.now();
        // Um evento está ocorrendo se 'agora' é depois ou igual ao início E 'agora' é antes do fim.
        // Usamos 'isEqual' ou 'isAfter' para o início e 'isBefore' para o fim.
        return (now.isEqual(evento.getHorarioInicio()) || now.isAfter(evento.getHorarioInicio())) &&
               now.isBefore(evento.getHorarioFim());
    }
}