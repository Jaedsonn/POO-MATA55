import java.util.ArrayList;

public class Corretora {
    private ArrayList<Pessoa> clientes;

    public Corretora(){
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Pessoa cliente){
        clientes.add(cliente);
    }

    public void listarClientes(){
        for(Pessoa cliente: clientes){
            System.out.println("Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf() + ", Empréstimo: " + cliente.getEmprestimo());
        }
    }

    public void consultarEmprestimo(Pessoa cliente){
        System.out.println("Empréstimo disponível para " + cliente.getNome() + ": " + cliente.getEmprestimo());
        this.adicionarCliente(cliente);
    }

    public static void main(String[] args) {
        Corretora corretora = new Corretora();

        Pessoa pessoa = new Pessoa("Ana Silva", "123.456.789-00");
        Empregado empregado = new Empregado("Carlos Souza", "987.654.321-00", "Vendas", 3000.0);
        Chefia chefia = new Chefia("Mariana Lima", "456.789.123-00", "Marketing", 5000.0, 1500.0);

        corretora.adicionarCliente(pessoa);
        corretora.adicionarCliente(empregado);
        corretora.adicionarCliente(chefia);

        corretora.listarClientes();
    }
}
