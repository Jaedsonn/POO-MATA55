import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class PassagemAerea {
    protected Trecho trechoIda;
    protected LocalDate dataIda;
    protected LocalTime horarioIda;
    protected Trecho trechoVolta; 
    protected LocalDate dataVolta; 
    protected LocalTime horarioVolta; 
    protected String numeroCartaoCredito;
    protected Cliente cliente;
    protected ArrayList<ServicoAdicional> servicosAdicionais;
    protected double valorTotal;
    
    public PassagemAerea(Trecho trechoIda, LocalDate dataIda, LocalTime horarioIda) {
        this.trechoIda = trechoIda;
        this.dataIda = dataIda;
        this.horarioIda = horarioIda;
        this.servicosAdicionais = new ArrayList<>();
        this.valorTotal = 0.0;
    }
    
    public void setTrechoVolta(Trecho trechoVolta, LocalDate dataVolta, LocalTime horarioVolta) {
        this.trechoVolta = trechoVolta;
        this.dataVolta = dataVolta;
        this.horarioVolta = horarioVolta;
    }
    
    public void vincularCliente(Cliente cliente) {
        if(cliente.getCartaoCredito() == null) {
            throw new IllegalArgumentException("O cliente não possui um cartão de crédito válido.");
        }
        this.cliente = cliente;
        this.numeroCartaoCredito = cliente.getCartaoCredito().getNumeroCartao();
    }
    
    public void adicionarServico(ServicoAdicional servico) {
        if(servico.aplicavelPara(getClasseVoo())) {
            servicosAdicionais.add(servico);
        } else {
            throw new IllegalArgumentException("Serviço não aplicável para esta classe.");
        }
    }
    
    public abstract double getPercentualClasse();
    
    public abstract String getClasseVoo();
    
    public abstract double calcularTaxaRemarcacao();
    
    public abstract boolean podeCancelar();
    
    public double calcularValorTotal() {
        double valorBase = 0.0;
        
        valorBase += trechoIda.getValorPadrao() * getPercentualClasse();
        
        if(trechoVolta != null) {
            valorBase += trechoVolta.getValorPadrao() * getPercentualClasse();
        }
        
        for(ServicoAdicional servico : servicosAdicionais) {
            valorBase += servico.getValor();
        }
        
        this.valorTotal = valorBase;
        return this.valorTotal;
    }
    
    public void remarcarVoo(LocalDate novaData) {
        double taxa = calcularTaxaRemarcacao();
        if(taxa > 0) {
            System.out.println("Taxa de remarcação: R$ " + taxa);
        }
        this.dataIda = novaData;
    }
    
    public void cancelarPassagem() {
        if(!podeCancelar()) {
            throw new UnsupportedOperationException("Esta classe não permite cancelamento.");
        }
        System.out.println("Passagem cancelada com sucesso!");
    }
    
    public Trecho getTrechoIda() {
        return trechoIda;
    }
    
    public LocalDate getDataIda() {
        return dataIda;
    }
    
    public LocalTime getHorarioIda() {
        return horarioIda;
    }
    
    public Trecho getTrechoVolta() {
        return trechoVolta;
    }
    
    public LocalDate getDataVolta() {
        return dataVolta;
    }
    
    public LocalTime getHorarioVolta() {
        return horarioVolta;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public String getNumeroCartaoCredito() {
        return numeroCartaoCredito;
    }
    
    public ArrayList<ServicoAdicional> getServicosAdicionais() {
        return servicosAdicionais;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
} 
    
