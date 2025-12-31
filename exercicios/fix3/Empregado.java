public class Empregado extends Pessoa {
    private double salario;

    public Empregado(String nome, String cpf, String setor, double salario) {
        super(nome, cpf);
        this.salario = salario;
    }

    @Override
    public double getEmprestimo(){
        return salario * 2;
    }

    public double getSalario() {
        return salario;
    }
}
