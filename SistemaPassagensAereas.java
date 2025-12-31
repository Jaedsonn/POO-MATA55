import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class SistemaPassagensAereas implements ISistema {
    private ArrayList<PassagemAerea> passagens;
    private ArrayList<Trecho> trechosDisponiveis;
    private ArrayList<ServicoAdicional> servicosDisponiveis;
    private static SistemaPassagensAereas instance;

    private SistemaPassagensAereas() {
        this.passagens = new ArrayList<>();
        this.trechosDisponiveis = new ArrayList<>();
        this.servicosDisponiveis = new ArrayList<>();
        inicializarServicos();
    }

    public static SistemaPassagensAereas getInstance() {
        if (instance == null) {
            instance = new SistemaPassagensAereas();
        }
        return instance;
    }

    private void inicializarServicos() {
        servicosDisponiveis.add(new ServicoAdicional("Pet na cabine", 250.0, "TODAS"));
        servicosDisponiveis.add(new ServicoAdicional("Bagagem extra", 80.0, "TODAS"));

        servicosDisponiveis.add(new ServicoAdicional("Escolha de assento", 80.0, "ECONOMICA"));
        servicosDisponiveis.add(new ServicoAdicional("Escolha de assento", 120.0, "EXECUTIVA"));
        servicosDisponiveis.add(new ServicoAdicional("Escolha de assento", 160.0, "PRIMEIRA"));
    }

    public void cadastrarTrecho(String origem, String destino, double valorPadrao) {
        Trecho trecho = new Trecho(origem, destino, valorPadrao);
        trechosDisponiveis.add(trecho);
        System.out.println("Trecho cadastrado com sucesso!");
    }

    public void adicionarPassagem(PassagemAerea passagem) {
        this.passagens.add(passagem);
    }

    public void listarTrechos() {
        System.out.println("\n=== Trechos Disponíveis ===");
        for (int i = 0; i < trechosDisponiveis.size(); i++) {
            Trecho t = trechosDisponiveis.get(i);
            System.out.println((i + 1) + " - " + t.getOrigem() + " → " + t.getDestino() +
                    " | Valor base: R$ " + t.getValorPadrao());
        }
    }

    public void listarServicos(String classe) {
        System.out.println("\n=== Serviços Adicionais Disponíveis ===");
        for (int i = 0; i < servicosDisponiveis.size(); i++) {
            ServicoAdicional s = servicosDisponiveis.get(i);
            if (s.aplicavelPara(classe)) {
                System.out.println((i + 1) + " - " + s.getNome() + " | R$ " + s.getValor());
            }
        }
    }

    public Trecho getTrecho(int index) {
        if (index >= 0 && index < trechosDisponiveis.size()) {
            return trechosDisponiveis.get(index);
        }
        return null;
    }

    public ServicoAdicional getServico(int index, String classe) {
        if (index >= 0 && index < servicosDisponiveis.size()) {
            ServicoAdicional servico = servicosDisponiveis.get(index);
            if (servico.aplicavelPara(classe)) {
                return servico;
            }
        }
        return null;
    }

    public void buscarPassagemPorCpf(String cpf) {
        boolean encontrou = false;
        for (PassagemAerea passagem : passagens) {
            if (passagem.getCliente() != null &&
                    passagem.getCliente().getCpf().equals(cpf)) {
                exibirDetalhesPassagem(passagem);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma passagem encontrada para o CPF: " + cpf);
        }
    }

    public void buscarPassagemPorCodigo(UUID codigo) {
        boolean encontrou = false;
        for (PassagemAerea passagem : passagens) {
            if (passagem.getCodigo().equals(codigo)) {
                exibirDetalhesPassagem(passagem);
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma passagem encontrada para o código: " + codigo);
        }
    }

    private void exibirDetalhesPassagem(PassagemAerea passagem) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PASSAGEM AÉREA - CLASSE " + passagem.getClasseVoo());
        System.out.println("=".repeat(80));

        Cliente c = passagem.getCliente();
        System.out.println("Cliente: " + c.getNome());
        System.out.println("CPF: " + c.getCpf());
        System.out.println("Telefone: " + c.getTelefone());
        System.out.println("Cartão: " + passagem.getNumeroCartaoCredito());

        System.out.println("\n--- VOO DE IDA ---");
        Trecho ida = passagem.getTrechoIda();
        System.out.println("Origem: " + ida.getOrigem());
        System.out.println("Destino: " + ida.getDestino());
        System.out.println("Data: " + passagem.getDataIda());
        System.out.println("Horário: " + passagem.getHorarioIda());

        if (passagem.getTrechoVolta() != null) {
            System.out.println("\n--- VOO DE VOLTA ---");
            Trecho volta = passagem.getTrechoVolta();
            System.out.println("Origem: " + volta.getOrigem());
            System.out.println("Destino: " + volta.getDestino());
            System.out.println("Data: " + passagem.getDataVolta());
            System.out.println("Horário: " + passagem.getHorarioVolta());
        }

        if (!passagem.getServicosAdicionais().isEmpty()) {
            System.out.println("\n--- SERVIÇOS ADICIONAIS ---");
            for (ServicoAdicional servico : passagem.getServicosAdicionais()) {
                System.out.println("• " + servico.getNome() + " - R$ " + servico.getValor());
            }
        }

        System.out.println("\n--- INFORMAÇÕES FINANCEIRAS ---");
        System.out.println("Valor Total: R$ " + String.format("%.2f", passagem.getValorTotal()));
        System.out.println("Taxa de Remarcação: R$ " +
                String.format("%.2f", passagem.calcularTaxaRemarcacao()));
        System.out.println("Permite Cancelamento: " +
                (passagem.podeCancelar() ? "Sim" : "Não"));
        System.out.println("=".repeat(80));
    }

    public void interfaceCompraPassagem(Cliente cliente) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== COMPRA DE PASSAGEM AÉREA ===");

        listarTrechos();
        System.out.print("Escolha o trecho de ida: ");
        int trechoIdaIdx = sc.nextInt() - 1;
        sc.nextLine();
        Trecho trechoIda = getTrecho(trechoIdaIdx);

        if (trechoIda == null) {
            System.out.println("Trecho inválido!");
            return;
        }

        System.out.print("Data da ida (AAAA-MM-DD): ");
        LocalDate dataIda = LocalDate.parse(sc.nextLine());
        System.out.print("Horário da ida (HH:MM): ");
        LocalTime horarioIda = LocalTime.parse(sc.nextLine());

        System.out.println("\nEscolha a classe:");
        System.out.println("1 - Econômica (70% do valor)");
        System.out.println("2 - Executiva (140% do valor)");
        System.out.println("3 - Primeira Classe (200% do valor)");
        System.out.print("Opção: ");
        int classeOpcao = sc.nextInt();
        sc.nextLine();

        PassagemAerea passagem = null;
        switch (classeOpcao) {
            case 1:
                passagem = new PassagemEconomica(trechoIda, dataIda, horarioIda);
                break;
            case 2:
                passagem = new PassagemExecutiva(trechoIda, dataIda, horarioIda);
                break;
            case 3:
                passagem = new PassagemPrimeiraClasse(trechoIda, dataIda, horarioIda);
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        System.out.print("\nDeseja adicionar passagem de volta? (s/n): ");
        String temVolta = sc.nextLine();

        if (temVolta.equalsIgnoreCase("s")) {
            listarTrechos();
            System.out.print("Escolha o trecho de volta: ");
            int trechoVoltaIdx = sc.nextInt() - 1;
            sc.nextLine();
            Trecho trechoVolta = getTrecho(trechoVoltaIdx);

            if (trechoVolta != null) {
                System.out.print("Data da volta (AAAA-MM-DD): ");
                LocalDate dataVolta = LocalDate.parse(sc.nextLine());
                System.out.print("Horário da volta (HH:MM): ");
                LocalTime horarioVolta = LocalTime.parse(sc.nextLine());

                passagem.setTrechoVolta(trechoVolta, dataVolta, horarioVolta);
            }
        }

        passagem.vincularCliente(cliente);

        System.out.print("\nDeseja adicionar serviços? (s/n): ");
        String addServicos = sc.nextLine();

        while (addServicos.equalsIgnoreCase("s")) {
            listarServicos(passagem.getClasseVoo());
            System.out.print("Escolha o serviço (0 para finalizar): ");
            int servicoIdx = sc.nextInt() - 1;
            sc.nextLine();

            if (servicoIdx < 0)
                break;

            ServicoAdicional servico = getServico(servicoIdx, passagem.getClasseVoo());
            if (servico != null) {
                passagem.adicionarServico(servico);
                System.out.println("Serviço adicionado!");
            }

            System.out.print("Adicionar outro serviço? (s/n): ");
            addServicos = sc.nextLine();
        }

        double valorTotal = passagem.calcularValorTotal();
        System.out.println("\n=== RESUMO DA COMPRA ===");
        System.out.println("Valor Total: R$ " + String.format("%.2f", valorTotal));

        System.out.print("\nConfirmar compra? (s/n): ");
        String confirma = sc.nextLine();

        if (confirma.equalsIgnoreCase("s")) {
            adicionarPassagem(passagem);
            System.out.println("Passagem comprada com sucesso!");
        } else {
            System.out.println("Compra cancelada.");
        }
    }

    public void emitirNotaFiscal(Cliente cliente){
        ArrayList<PassagemAerea> compras = cliente.getPassagens();
        System.out.println("\n=== NOTA FISCAL DE PASSAGENS AÉREAS ===");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("----------------------------------------");
        
        for(PassagemAerea passagem: compras){
            System.out.println("Código da Passagem: " + passagem.getCodigo());
            System.out.println("Classe: " + passagem.getClasseVoo());
            System.out.println("Trecho Ida: " + passagem.getTrechoIda().getOrigem() + " → " + passagem.getTrechoIda().getDestino());
            System.out.println("Data Ida: " + passagem.getDataIda());
            if(passagem.getTrechoVolta() != null){
                System.out.println("Trecho Volta: " + passagem.getTrechoVolta().getOrigem() + " → " + passagem.getTrechoVolta().getDestino());
                System.out.println("Data Volta: " + passagem.getDataVolta());
            }
            System.out.println("Valor Total da Passagem: R$ " + passagem.getValorTotal());
            System.out.println("========================================\n");
        }
    }
}