import java.util.ArrayList;
import java.time.LocalDate;

public class Cliente{
    private String nome;
    private String cpf;
    private String numeroTelefone;
    private CartaoCredito cartaoCredito;
    private ArrayList<AluguelCarro> alugueis;
    private ArrayList<PassagemAerea> passagens;
    
    public Cliente(String nome, String cpf, String numeroTelefone){
        this.nome = nome;
        this.cpf = cpf;
        this.numeroTelefone = numeroTelefone;
        this.alugueis = new ArrayList<>();
    }
    
    public void setCartaoCredito(String numeroCartao,String codigoSeguranca, int mesValidade, int anoValidade){
        if(numeroCartao.length() < 16){
            throw new IllegalArgumentException("Numero do cartao invalido");
        } else if(mesValidade > 12){
            throw new IllegalArgumentException("Mes de validade invalido");
        } else if(anoValidade < LocalDate.now().getYear()){
            throw new IllegalArgumentException("Ano de validade invalido");
        }
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

    public ArrayList<AluguelCarro> getAlugueis(){
        return this.alugueis;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public ArrayList<PassagemAerea> getPassagens(){
        return this.passagens;
    }

    public void adicionarPassagem(PassagemAerea passagem){
        if(!this.passagens.contains(passagem)){
            this.passagens.add(passagem);
        }
    }

    public void removerPassagem(PassagemAerea passagem){
        if(this.passagens.contains(passagem)){
            this.passagens.remove(passagem);
        }
    }
}