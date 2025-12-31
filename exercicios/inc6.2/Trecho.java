public class Trecho {
    private String origem;
    private String destino;
    private double valorPadrao;
    
    public Trecho(String origem, String destino, double valorPadrao) {
        this.origem = origem;
        this.destino = destino;
        this.valorPadrao = valorPadrao;
    }
    
    public String getOrigem() {
        return origem;
    }
    
    public String getDestino() {
        return destino;
    }
    
    public double getValorPadrao() {
        return valorPadrao;
    }
    
    public void setValorPadrao(double valorPadrao) {
        this.valorPadrao = valorPadrao;
    }
}