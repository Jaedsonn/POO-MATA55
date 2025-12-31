import java.time.LocalDate;
import java.time.LocalTime;

public class PassagemEconomica extends PassagemAerea {
    private static final double PERCENTUAL_CLASSE = 0.70;
    private static final double TAXA_REMARCACAO = 0.50;
    
    public PassagemEconomica(Trecho trechoIda, LocalDate dataIda, LocalTime horarioIda) {
        super(trechoIda, dataIda, horarioIda);
    }
    
    @Override
    public double getPercentualClasse() {
        return PERCENTUAL_CLASSE;
    }
    
    @Override
    public String getClasseVoo() {
        return "ECONOMICA";
    }
    
    @Override
    public double calcularTaxaRemarcacao() {
        return super.getValorTotal() * TAXA_REMARCACAO;
    }
    
    @Override
    public boolean podeCancelar() {
        return false;
    }
}