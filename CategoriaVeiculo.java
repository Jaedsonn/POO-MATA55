public enum CategoriaVeiculo {
    A("A", 350.00, 0.30, 70.00),
    B("B", 250.00, 0.25, 60.00),
    C("C", 150.00, 0.20, 50.00);

    private final String codigo;
    private final double valorDiaria;
    private final double percentualQuilometragemAdicional;
    private final double acrescimoQuilometragemIlimitada;

    CategoriaVeiculo(String codigo, double valorDiaria, double percentualQuilometragemAdicional, 
                     double acrescimoQuilometragemIlimitada) {
        this.codigo = codigo;
        this.valorDiaria = valorDiaria;
        this.percentualQuilometragemAdicional = percentualQuilometragemAdicional;
        this.acrescimoQuilometragemIlimitada = acrescimoQuilometragemIlimitada;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public double getPercentualQuilometragemAdicional() {
        return percentualQuilometragemAdicional;
    }

    public double getAcrescimoQuilometragemIlimitada() {
        return acrescimoQuilometragemIlimitada;
    }

    public double calcularValorDiariaComQuilometragemAdicional(double quilometragemAdicional) {
        return valorDiaria + (quilometragemAdicional * percentualQuilometragemAdicional);
    }

    public double calcularValorDiariaQuilometragemIlimitada() {
        return valorDiaria + acrescimoQuilometragemIlimitada;
    }

    public static CategoriaVeiculo fromCodigo(String codigo) {
        for (CategoriaVeiculo categoria : values()) {
            if (categoria.getCodigo().equalsIgnoreCase(codigo)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria inválida: " + codigo);
    }
}