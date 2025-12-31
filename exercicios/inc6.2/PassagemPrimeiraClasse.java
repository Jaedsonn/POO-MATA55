import java.time.LocalDate;
import java.time.LocalTime;

public class PassagemPrimeiraClasse extends PassagemAerea {
    private static final double PERCENTUAL_CLASSE = 2.00;
    
    public PassagemPrimeiraClasse(Trecho trechoIda, LocalDate dataIda, LocalTime horarioIda) {
        super(trechoIda, dataIda, horarioIda);
    }
    
    @Override
    public double getPercentualClasse() {
        return PERCENTUAL_CLASSE;
    }
    
    @Override
    public String getClasseVoo() {
        return "PRIMEIRA";
    }
    
    @Override
    public double calcularTaxaRemarcacao() {
        return 0.0; 
    }
    
    @Override
    public boolean podeCancelar() {
        return true;
    }
}