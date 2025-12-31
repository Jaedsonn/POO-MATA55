import java.time.LocalDate;
import java.time.LocalTime;

public class PassagemExecutiva extends PassagemAerea {
    private static final double PERCENTUAL_CLASSE = 1.40;
    private static final double TAXA_REMARCACAO = 0.30;
    
    public PassagemExecutiva(Trecho trechoIda, LocalDate dataIda, LocalTime horarioIda) {
        super(trechoIda, dataIda, horarioIda);
    }
    
    @Override
    public double getPercentualClasse() {
        return PERCENTUAL_CLASSE;
    }
    
    @Override
    public String getClasseVoo() {
        return "EXECUTIVA";
    }
    
    @Override
    public double calcularTaxaRemarcacao() {
        return valorTotal * TAXA_REMARCACAO;
    }
    
    @Override
    public boolean podeCancelar() {
        return false;
    }
}