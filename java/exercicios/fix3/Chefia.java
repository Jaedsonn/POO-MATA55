public class Chefia extends Empregado {
    private double bonus;

    public Chefia(String nome, String cpf, String setor, double salario, double bonus){
        super(nome, cpf, setor, salario);
        this.bonus = bonus;
    }

    @Override
    public double getEmprestimo(){
        return super.getSalario() + bonus;
    }
}
