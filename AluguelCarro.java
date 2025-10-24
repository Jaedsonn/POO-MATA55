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
    private CategoriaVeiculo categoria;
    private double quilometragemDiaria;
    private double valorLocacao;
    private Cliente cliente;

    public AluguelCarro(CategoriaVeiculo categoria, String cidadeDestino, 
                       LocalDate dataInicial, LocalDate dataFinal,
                       LocalTime horarioRetirada, LocalTime horarioDevolucao) {
        this.categoria = categoria;
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
        this(categoria, cidadeDestino, dataInicial, dataFinal, horarioRetirada, horarioRetirada);
    }

    public AluguelCarro(CategoriaVeiculo categoria, String cidadeDestino,
                       LocalDate dataInicial, LocalDate dataFinal) {
        this(categoria, cidadeDestino, dataInicial, dataFinal, LocalTime.MIDNIGHT, LocalTime.MIDNIGHT);
    }

    public long calcularQuantidadeDiarias() {
        long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
        
        if (horarioDevolucao.isAfter(horarioRetirada)) {
            dias++;
        }
        
        return dias;
    }

    public double calcularValorLocacao() {
        long quantidadeDiarias = calcularQuantidadeDiarias();
        this.valorLocacao = quantidadeDiarias * categoria.getValorDiaria();
        return this.valorLocacao;
    }

    public double calcularValorLocacao(double quilometragemAdicional) {
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

    public CategoriaVeiculo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaVeiculo categoria) {
        this.categoria = categoria;
    }

    public double getQuilometragemDiaria() {
        return quilometragemDiaria;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    }
