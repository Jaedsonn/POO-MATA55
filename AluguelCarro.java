import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class AluguelCarro {
    private static final double QUILOMETRAGEM_DIARIA_PADRAO = 200.0;
    
    private String cidadeDestino;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private LocalTime horarioRetirada;
    private LocalTime horarioDevolucao;
    private double quilometragemDiaria;
    private double valorLocacao;
    private String numeroCartaoCredito;
    private Cliente cliente;
    private Carro carro;
    
    public AluguelCarro(String cidadeDestino, 
                       LocalDate dataInicial, LocalDate dataFinal,
                       LocalTime horarioRetirada, LocalTime horarioDevolucao) {
        this.cidadeDestino = cidadeDestino;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.horarioRetirada = horarioRetirada;
        this.horarioDevolucao = horarioDevolucao;
        this.quilometragemDiaria = QUILOMETRAGEM_DIARIA_PADRAO;
        this.valorLocacao = 0.0;
    }

    
    public AluguelCarro(CategoriaVeiculo categoria, String cidadeDestino,
    LocalDate dataInicial, LocalDate dataFinal,
    LocalTime horarioRetirada) {
        this( cidadeDestino, dataInicial, dataFinal, horarioRetirada, horarioRetirada);
    }
    
    public AluguelCarro(CategoriaVeiculo categoria, String cidadeDestino,
    LocalDate dataInicial, LocalDate dataFinal) {
        this( cidadeDestino, dataInicial, dataFinal, LocalTime.MIDNIGHT, LocalTime.MIDNIGHT);
    }
    public void vincularCarro(Carro carro){
        if(carro.getStatusCarro() != StatusCarro.DISPONIVEL){
            throw new IllegalArgumentException("O carro não está disponível para aluguel.");
        }
        this.carro = carro;
        this.carro.setStatusCarro(StatusCarro.ALUGADO);
    }
    public void vincularCliente(Cliente cliente){
        if(cliente.getCartaoCredito() == null){
            throw new IllegalArgumentException("O cliente não possui um cartão de crédito válido.");
        }
        this.cliente = cliente;
        this.numeroCartaoCredito = cliente.getCartaoCredito().getNumeroCartao();
    }

    public void devolverCarro(){
        if(this.carro != null){
            this.carro.setStatusCarro(StatusCarro.DISPONIVEL);
            this.carro = null;
            this. cliente = null;
            this.numeroCartaoCredito = null;
            this.horarioDevolucao = null;
            this.horarioRetirada = null;
            this.valorLocacao = 0.0;
        }
    }


    public long calcularQuantidadeDiarias() {
        long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
        
        if (horarioDevolucao.isAfter(horarioRetirada)) {
            dias++;
        }
        
        return dias;
    }

    public double calcularValorLocacao() {
        CategoriaVeiculo categoria = carro.getCategoria();
        long quantidadeDiarias = calcularQuantidadeDiarias();
        this.valorLocacao = quantidadeDiarias * categoria.getValorDiaria();
        return this.valorLocacao;
    }

    public double calcularValorLocacao(double quilometragemAdicional) {
        CategoriaVeiculo categoria = carro.getCategoria();
        long quantidadeDiarias = calcularQuantidadeDiarias();
        double valorDiariaComAdicional = categoria.calcularValorDiariaComQuilometragemAdicional(quilometragemAdicional);
        this.valorLocacao = quantidadeDiarias * valorDiariaComAdicional;
        
        this.quilometragemDiaria = QUILOMETRAGEM_DIARIA_PADRAO + quilometragemAdicional;
        
        return this.valorLocacao;
    }

    public double calcularValorLocacao(String quilometragemIlimitada) {
        if(quilometragemIlimitada != null && !quilometragemIlimitada.equalsIgnoreCase("ILIMITADA")) {
            throw new IllegalArgumentException("Parâmetro inválido para quilometragem ilimitada.");
        }
        CategoriaVeiculo categoria = carro.getCategoria();
        long quantidadeDiarias = calcularQuantidadeDiarias();
        double valorDiariaIlimitada = categoria.calcularValorDiariaQuilometragemIlimitada();
        this.valorLocacao = quantidadeDiarias * valorDiariaIlimitada;
        this.quilometragemDiaria = 0.0;
        
        return this.valorLocacao;
    }

    public double calcularQuilometragemTotal() {
        if (quilometragemDiaria == 0.0) {
            return Double.POSITIVE_INFINITY;
        }
        return calcularQuantidadeDiarias() * quilometragemDiaria;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public LocalTime getHorarioRetirada() {
        return horarioRetirada;
    }

    public void setHorarioRetirada(LocalTime horarioRetirada) {
        this.horarioRetirada = horarioRetirada;
    }

    public LocalTime getHorarioDevolucao() {
        return horarioDevolucao;
    }

    public void setHorarioDevolucao(LocalTime horarioDevolucao) {
        this.horarioDevolucao = horarioDevolucao;
    }


    public double getQuilometragemDiaria() {
        return quilometragemDiaria;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    }
