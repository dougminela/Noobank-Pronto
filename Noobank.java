import java.util.Scanner;

class Noobank {
	
    public double saldo, depositoInicial, saque, credito;
    int nConta;

    public Noobank(int nConta, double depositoInicial) {
        this.nConta = nConta;
        this.depositoInicial = depositoInicial;
        saldo += depositoInicial;
        System.out.println("Depósito inicial: " + depositoInicial + " reais.");
    }

    public void depositar(double deposito) {
        saldo += deposito;
        System.out.println("Você depositou: " + deposito + " reais.");
    }

    public void getSaldo() {
        System.out.println("Saldo atual da conta: " + saldo);
    }

    public void sacar(double saque) {
        saldo -= saque;
        System.out.println("Você sacou: " + saque + " reais.");
    }
 
    public void credito(double credito) {
        if (credito < saldo) {
            System.out.println("Crédito no valor de R$ " + credito + " liberado.");
        } else {
            System.out.println("Crédito no valor de R$ " + credito + " não foi liberado.");
            System.out.println("Você não tem acesso a esse valor em crédito");
        }
    }

}

class ContaPoupanca extends Noobank {

    public double pagamentoJuros, juros, saldoPoupanca;

    public ContaPoupanca(int nConta, double depositoInicial) {
        super(nConta, depositoInicial);
        saldoPoupanca += depositoInicial;
    }

    public void setJuros (int juros) {
        this.juros = juros;
    }

    public void pagarJuros() {
        pagamentoJuros = saldoPoupanca * (juros / 100);
        saldoPoupanca += pagamentoJuros;
        System.out.println("Valor do juros: " + pagamentoJuros);
        System.out.println("Seu saldo atual é: " + (saldoPoupanca + pagamentoJuros));
    }

    public double getTaxaJuros () {
        return pagamentoJuros;
    }

}

class ContaAplicacao extends Noobank {

    private double pagamentoJuros, juros, dinheiroAplicado, taxaMulta, valorMulta = 0, saldoContaAplicacao;
    private boolean vencido = true;

    public ContaAplicacao(int nConta, double depositoInicial, double taxaMulta) {
        super(nConta, depositoInicial);
        this.taxaMulta = taxaMulta;
        saldoContaAplicacao += depositoInicial;
    }

    public void vencida() {
        if (vencido)
            vencido = false;
        else
            vencido = true;
    }

    public void estaVencida() {
        if (vencido)
            System.out.println("Está vencido");
        else
            System.out.println("Não está vencido");
    }

    public void setTaxaJuros(double juros) {
        this.juros = juros;
    }

    public double getTaxaJuros() {
        return juros;
    }

    public void AplicarDinheiro(double dinheiroAplicado) {
        dinheiroAplicado += dinheiroAplicado;
    }

    public void sacar() {
        if (vencido) {
            valorMulta = dinheiroAplicado * (taxaMulta / 100);
            dinheiroAplicado = dinheiroAplicado - valorMulta;
            System.out.println("Dinheiro aplicado: " + dinheiroAplicado);
            System.out.println("Valor da multa: " + valorMulta);
            System.out.println("Dinheiro retirado com multa: " + (dinheiroAplicado - valorMulta));
        } else {
            System.out.println("Dinheiro retirado: " + dinheiroAplicado);          
        }
    }



}


class ContaSalario extends Noobank {

    private int limiteTransacoes, qtdTransacoes, cotasMensais;
    private double taxa, saldoContaSalario;

    public ContaSalario(int nConta, double depositoInicial) {
        super(nConta, depositoInicial);
        saldoContaSalario += depositoInicial;
    }

    public void calcularTaxas() {
        taxa = qtdTransacoes - cotasMensais;         
    }

    public double getTaxa() {
        return taxa;
    }

    public int getCotasMensais() {
        return cotasMensais;
    }

    public void setCotasMensais(int cotasMensais) {
        this.cotasMensais = cotasMensais;
    }

    public int getQtdTransacoes() {
        return qtdTransacoes;
    }

    public void sacar(double saque) {
        this.saque = saque;

        qtdTransacoes++;

        if (cotasMensais > qtdTransacoes) {
            saldoContaSalario -= saque;        
            System.out.println("Você sacou: "+saque);
            System.out.println("Saldo atual: "+saldoContaSalario);
        }    
        else {
            saldoContaSalario -= taxa;
            saldoContaSalario -= saque;
            System.out.println("Você sacou: "+saque);
            System.out.println("Saldo atual: "+saldoContaSalario);
            System.out.println("Valor da taxa: "+taxa);
        }
    }
}

class ContaEspecial extends Noobank {   

    double taxaCredito, fatura, saldoContaEspecial;

    public ContaEspecial(int nConta, double depositoInicial) {
        super(nConta, depositoInicial);
        saldoContaEspecial += depositoInicial;
    }

    public void setTaxaCredito(double taxaCredito) {
        this.taxaCredito = taxaCredito;
    }

    public double getTaxaCredito() {
        return taxaCredito;
    }

    public void solicitarCredito (double fatura) {
        this.fatura = fatura;
        System.out.println("Crédito recebido: "+fatura);        
    }

    public void aplicarJuros() {
        fatura += (fatura * (taxaCredito/100));
        System.out.println("Juros aplicado no crédito");
    }

    public void sacar() {
        saldoContaEspecial -= saque;
        System.out.println("Valor da fatura atrasada com acrescimo de "+taxaCredito+"%: "+fatura);
        System.out.println("Você sacou: "+saque);
        System.out.println("Saldo atual da conta: "+saldoContaEspecial);
    }
}

public class Main {
    public static void main(String[] args) {
        
       // Conta genérica
        System.out.println("\nConta genérica: ");
        Noobank noobank = new Noobank(122, 200);
        noobank.getSaldo();
        noobank.depositar(370);
        noobank.getSaldo();
        noobank.sacar(300);
        noobank.getSaldo();
        noobank.credito(1000);

        // Conta Poupança
        System.out.println("\nConta poupança: ");
        ContaPoupanca poupanca = new ContaPoupanca(123, 300);
        poupanca.setJuros(3);
        poupanca.getSaldo();
        poupanca.pagarJuros();
    
        // Conta Aplicação
        System.out.println("\nConta aplicação: ");
        ContaAplicacao contaAplicacao = new ContaAplicacao(124, 400, 5);
        contaAplicacao.setTaxaJuros(5);
        contaAplicacao.getTaxaJuros();
        contaAplicacao.AplicarDinheiro(50.000);
        contaAplicacao.estaVencida();
        contaAplicacao.sacar();

        // Conta Salário
        System.out.println("\nConta salário: ");
        ContaSalario contaSalario = new ContaSalario (125, 2500);
        contaSalario.setCotasMensais(2);
        contaSalario.getCotasMensais();
        contaSalario.getQtdTransacoes();
        contaSalario.sacar(1);
        contaSalario.sacar(1);
        contaSalario.getQtdTransacoes();
        contaSalario.calcularTaxas();
        contaSalario.sacar(2);

        // Conta Especial
        System.out.println("\nConta especial: ");
        ContaEspecial contaEspecial = new ContaEspecial(126, 7000);
        contaEspecial.setTaxaCredito(20);
        contaEspecial.getTaxaCredito();
        contaEspecial.solicitarCredito(20000);
        contaEspecial.aplicarJuros();
        contaEspecial.sacar(500);
    }   
}