import java.util.ArrayList;

public class SistemaAluguel {
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
}
