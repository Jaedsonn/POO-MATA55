public class ServicoAdicional {
    private String nome;
    private double valor;
    private String classeAplicavel; 
    
    public ServicoAdicional(String nome, double valor, String classeAplicavel) {
        this.nome = nome;
        this.valor = valor;
        this.classeAplicavel = classeAplicavel;
    }
    
    public String getNome() {
        return nome;
    }
    
    public double getValor() {
        return valor;
    }
    
    public String getClasseAplicavel() {
        return classeAplicavel;
    }
    
    public boolean aplicavelPara(String classe) {
        return classeAplicavel.equals("TODAS") || classeAplicavel.equals(classe);
    }
}