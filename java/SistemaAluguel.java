import java.util.ArrayList;
import java.util.Scanner;

public class SistemaAluguel implements ISistema {
    private ArrayList<AluguelCarro> reservas;
    private static SistemaAluguel instance;

    // Aqui foi usado um construtor privado pois como o objetivo da classe
    // é guardar todas as reservas do sistema, logo caso criassemos um 
    // construtor público poderiamos ter duas classes SistemaAluguel
    // diferentes, o que não faz sentido.
    // Dessa forma, garantimos que só exista uma instância do objeto
    // e todas as reservas forem concentradas nelas
    private SistemaAluguel(){
        this.reservas = new ArrayList<>();
    }

    public static SistemaAluguel getInstance(){
        if(instance == null){
           instance = new SistemaAluguel();
        }
        return instance;
    }

    public void adicionarReserva(AluguelCarro aluguel){
        this.reservas.add(aluguel);
    }

    public void buscaPorCpf(String cpf){
        Cliente cliente = null;
        String cartaoUsadoNaReserva = "";
        for(AluguelCarro aluguel: reservas){
            Cliente c = aluguel.getCliente();

            if(c.getCpf().equals(cpf)){
                cliente = c;
                cartaoUsadoNaReserva = aluguel.getNumeroCartao();
                break;
            }
        }

        if(cliente == null){
            showMessage("Cliente com CPF " + cpf + " não encontrado.");
            return;
        }
        clienteInfo(cliente);
        showMessage("Numero do Cartão: " + cartaoUsadoNaReserva);
        showMessage("-".repeat(100));

        for(AluguelCarro aluguel: reservas){
            Cliente c = aluguel.getCliente();

            if(c.getCpf().equals(cpf)){
                cliente = c;
                veiculoEReservaInfo(aluguel, aluguel.getCarro());
            }
        }
    }

    public void buscaPorPlaca(String placa){


        for(AluguelCarro reserva: reservas){
            if(reserva.getCarro() != null && reserva.getCarro().getPlaca().equals(placa)){
                clienteInfo(reserva.getCliente());
                showMessage("-".repeat(100));
                veiculoEReservaInfo(reserva, reserva.getCarro());
            }
        }
        
    }

    private void clienteInfo(Cliente cliente){
        showMessage("Dados do cliente:");
        showMessage("Nome " + cliente.getNome());
        showMessage("CPF " + cliente.getCpf());
        showMessage("Telefone " + cliente.getTelefone());
    }

    private void veiculoEReservaInfo(AluguelCarro reserva, Carro veiculo){
        showMessage("Dados do veículo:");
        showMessage("Modelo: " + veiculo.getModelo());
        showMessage("Cor: " + veiculo.getCor());
        showMessage("Placa: " + veiculo.getPlaca());
        showMessage("Categoria: " + veiculo.getCategoria());
        showMessage("-".repeat(100));
        showMessage("Dados da reserva: ");
        showMessage("Data Inicial: " + reserva.getDataInicial());
        showMessage("Data Final: " + reserva.getDataFinal());
        showMessage("Valor da reserva: " + reserva.getValorLocacao());
        showMessage("Horário inicial: " + reserva.getHorarioRetirada());
        showMessage("Horário final: " + reserva.getHorarioDevolucao());
    }

    private void showMessage(String message){
        System.out.println(message);
    }

    public void interfaceDeCadastro(){
        Scanner sc = new Scanner(System.in);

        System.out.println(" Cadastro de Reserva de Carro ");
        System.out.println("Escolha uma opção de cadastro: ");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Carro");
        System.out.println("3 - Finalizar Sistema de Cadastro");

        int opcao = sc.nextInt();
        sc.nextLine();

        while(opcao != 3){
            switch(opcao){
                case 1:
                    cadastroCliente();
                    break;
                case 2:
                    cadastroCarro();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println("Escolha uma opção de cadastro: ");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Carro");
            System.out.println("3 - Finalizar Sistema de Cadastro");

            opcao = sc.nextInt();
            sc.nextLine();
        }
    }

    public void cadastroCarro(){
        Scanner sc = new Scanner(System.in);

        System.out.println(" Cadastro de Carro ");
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Cor: ");
        String cor = sc.nextLine();

        System.out.print("Placa: ");
        String placa = sc.nextLine();

        System.out.print("Categoria (1 - Econômica, 2 - Intermediária, 3 - Luxo): ");
        int categoriaOpcao = sc.nextInt();
        sc.nextLine();

        CategoriaVeiculo categoria = null;
        
        while(categoria == null) {
            switch (categoriaOpcao) {
                case 1:
                    categoria = CategoriaVeiculo.B;
                    break;
                case 2:
                    categoria = CategoriaVeiculo.C;
                    break;
                case 3:
                    categoria = CategoriaVeiculo.A;
                    break;
                default:
                    categoriaOpcao = sc.nextInt();
                    sc.nextLine();
            }

        Carro carro = new Carro(modelo, placa, categoria, cor);
        System.out.println("Carro cadastrado com sucesso!");
    }

    return;
  }

  public void cadastroCliente(){
    Scanner sc = new Scanner(System.in);

    System.out.println(" Cadastro de Cliente ");
    System.out.print("Nome: ");
    String nome = sc.nextLine();

    System.out.print("CPF: ");
    String cpf = sc.nextLine();

    System.out.print("Telefone: ");
    String telefone = sc.nextLine();

    Cliente cliente = new Cliente(nome, cpf, telefone);
    System.out.println("Cliente cadastrado com sucesso!");

    System.out.println("Deseja cadastrar o cartão de crédito agora? (s/n)");
    String resposta = sc.nextLine();
    if(resposta.equalsIgnoreCase("s")){
        try {
            System.out.print("Número do Cartão (16 dígitos): ");
            String numeroCartao = sc.nextLine();

            System.out.print("Código de Segurança (3 dígitos): ");
            String codigoSeguranca = sc.nextLine();

            System.out.print("Mês de Validade (MM): ");
            int mesValidade = sc.nextInt();

            System.out.print("Ano de Validade (AAAA): ");
            int anoValidade = sc.nextInt();
            sc.nextLine(); 

            cliente.setCartaoCredito(numeroCartao, codigoSeguranca, mesValidade, anoValidade);
            System.out.println("Cartão de crédito cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar o cartão de crédito: " + e.getMessage());
        }
    } else {
        System.out.println("Cadastro de cartão de crédito ignorado.");
    }
    System.out.println("Cadastro de cliente finalizado.");
 }

 @Override
 public void emitirNotaFiscal(Cliente cliente) {
    ArrayList<AluguelCarro> alugueis = cliente.getAlugueis();
    System.out.println("\n=== NOTA FISCAL DE ALUGUEL DE CARRO ===");
    System.out.println("Cliente: " + cliente.getNome());
    System.out.println("CPF: " + cliente.getCpf());
    System.out.println("Telefone: " + cliente.getTelefone());
    System.out.println("----------------------------------------");
    
    for(AluguelCarro aluguel: alugueis){
        System.out.println("Carro Alugado: " + aluguel.getCarro().getModelo() + " - Placa: " + aluguel.getCarro().getPlaca());
        System.out.println("Data Inicial: " + aluguel.getDataInicial());
        System.out.println("Data Final: " + aluguel.getDataFinal());
        System.out.println("Horário Retirada: " + aluguel.getHorarioRetirada());
        System.out.println("Horário Devolução: " + aluguel.getHorarioDevolucao());
        System.out.println("Valor Total do Aluguel: R$ " + aluguel.getValorLocacao());
        System.out.println("========================================\n");
    }

 }

}