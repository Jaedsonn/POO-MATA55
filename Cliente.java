import java.util.ArrayList;

public class Cliente{
    private String nome;
    private String cpf;
    private String numeroTelefone;
    private CartaoCredito cartaoCredito;
    private ArrayList<AluguelCarro> alugueis;
    
    
    public Cliente(String nome, String cpf, String numeroTelefone){
        this.nome = nome;
        this.cpf = cpf;
        this.numeroTelefone = numeroTelefone;
        this.alugueis = new ArrayList<>();
    }
    
    public void setCartaoCredito(String numeroCartao,String codigoSeguranca, int mesValidade, int anoValidade){
        this.cartaoCredito = new CartaoCredito(codigoSeguranca, mesValidade, anoValidade, numeroCartao);
    }

    public String getNome(){
        return this.nome;
    }

    public String getTelefone(){
        return this.numeroTelefone;
    }

    public CartaoCredito getCartaoCredito(){
        return this.cartaoCredito;
    }

    public void adicionarAluguel(AluguelCarro aluguel){
        if(!(alugueis.contains(aluguel))){
            this.alugueis.add(aluguel);
        }
    }

    public void removerAluguel(AluguelCarro aluguel){
        if(alugueis.contains(aluguel)){
            alugueis.remove(aluguel);
        }
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }
}