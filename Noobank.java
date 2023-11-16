import java.util.Scanner;

class Noobank {
	
    public double saldo, depositoInicial, saque, credito;
    public int nConta;

    public Noobank(int nConta, double depositoInicial) {
        this.nConta = nConta;
        this.depositoInicial = depositoInicial;
        saldo += depositoInicial;
        System.out.println("Depósito inicial na conta pix: R$" + depositoInicial);
    }

    public void depositar(double deposito) {
        saldo += deposito;
        System.out.println("Você depositou: R$" + deposito);
    }

    public void getSaldo() {
        System.out.println("Saldo atual da conta "+ nConta + ": R$" + saldo);
    }

    public void sacar(double saque) {
        saldo -= saque;
        System.out.println("Você sacou R$" + saque);
    }
 
    public void credito(double credito) {
        if (credito < saldo) {
            System.out.println("Crédito no valor de R$" + credito + " liberado.");
        } else {
            System.out.println("Crédito no valor de R$" + credito + " não foi liberado.");
            System.out.println("Você não tem acesso a esse valor em crédito");
        }
    }

    public void resetSaldo() {
        saldo = 0;
    }

}

class ContaPoupanca extends Noobank {

    private double pagamentoJuros, juros, pcntJuros;

    public ContaPoupanca(int nConta, double depositoInicial) {
        super(nConta, depositoInicial);
    }

    public void setJuros (int juros) {
        this.juros = juros;
    }

    public void pagarJuros() {
        pcntJuros = juros /100;
        pagamentoJuros = pcntJuros * saldo;
        saldo += pagamentoJuros;
        System.out.println("Seu dinheiro rendeu R$" + pagamentoJuros);
    }

    public void getTaxaJuros () {
        System.out.println("A taxa do juros é "+juros+"%");
    }

}

class ContaAplicacao extends Noobank {

    private double dinheiroAplicado, taxaMulta, valorMulta = 0, saldoFundoImobiliario, dinheiroRetirado;
    private boolean vencido = true;

    public ContaAplicacao(int nConta, double depositoInicial) {
        super(nConta, depositoInicial);
    }

    public void setVencida() {
        if (vencido)
            vencido = false;
        else
            vencido = true;
    }

    public void getVencida() {
        if (vencido)
            System.out.println("Está vencido");
        else
            System.out.println("Não está vencido");
    }

    public void setTaxaMulta(double taxaMulta) {
        this.taxaMulta = taxaMulta;
    }

    public void getTaxaMulta() {
        System.out.println("Taxa "+taxaMulta+"%");
    }

    public void aplicarDinheiro(double dinheiroAplicado) {
        saldoFundoImobiliario += dinheiroAplicado;
        System.out.println("Você aplicou R$"+dinheiroAplicado+" no fundo imobiliário");
    }

    public void sacarFundoImobiliario (double dinheiroRetirado) {
        if (vencido) {
            valorMulta = dinheiroRetirado * (taxaMulta/100);
            saldoFundoImobiliario -= dinheiroRetirado + valorMulta;           
            System.out.println("Valor da multa (você retirou antes do contrato): R$" + valorMulta);
            System.out.println("Dinheiro do fundo imobiliário retirado com multa: R$" + (dinheiroRetirado - valorMulta));
        } else {
            System.out.println("Dinheiro retirado do fundo imobilário: R$" + dinheiroAplicado);          
        }
    }

    public double getSaldoFundoImobiliario() {
        return saldoFundoImobiliario;
    }

}


class ContaSalario extends Noobank {

    private int qtdTransacoes, cotasMensais;
    private double taxa;

    public ContaSalario(int nConta, double depositoInicial) {
        super(nConta, depositoInicial);
    }

    public void calcularTaxas() {
        taxa = qtdTransacoes - cotasMensais;         
    }

    public void getTaxa() {
        System.out.println("Taxa de R$"+taxa+" aplicada");
    }

    public void getCotasMensais() {
        System.out.println("Quantidade de cotas menais: "+cotasMensais);
    }

    public void setCotasMensais(int cotasMensais) {
        this.cotasMensais = cotasMensais;
    }

    public void getQtdTransacoes() {
        System.out.println("Quantidade de transações: "+qtdTransacoes);
    }

    public void sacar(double saque) {
        this.saque = saque;
        qtdTransacoes++;   
        taxa = qtdTransacoes - cotasMensais;     

        if (cotasMensais > qtdTransacoes) {
            saldo -= saque;        
            System.out.println("Você sacou R$"+saque);
        }    
        else {
            saldo -= taxa + saque;
            System.out.println("Você sacou R$"+saque);
        }
    }
}

class ContaEspecial extends Noobank {   

    private double taxaCredito, fatura, creditoSolicitado, pcntTaxaJuros, taxaAplicada;

    public ContaEspecial(int nConta, double depositoInicial) {
        super(nConta, depositoInicial);
    }

    public void setTaxaCredito(double taxaCredito) {
        this.taxaCredito = taxaCredito;
    }

    public void getTaxaCredito() {
        System.out.println("A taxa de crédito é "+taxaCredito+"%");
    }

    public void solicitarCredito (double creditoSolicitado) {
        this.creditoSolicitado = creditoSolicitado;
        fatura += creditoSolicitado;
        System.out.println("Crédito recebido R$"+creditoSolicitado);        
    }

    public void aplicarJuros() {
        pcntTaxaJuros = taxaCredito / 100;
        taxaAplicada = fatura * pcntTaxaJuros;
        fatura += taxaAplicada;
        System.out.println("Juros aplicado na fatura, valor de R$"+taxaAplicada);
    }

    public void getFatura() {
        System.out.println("Valor atual da fatura R$"+fatura);
    }
}

public class Main {
    public static void main(String[] args) {
        
       // Conta genérica
        System.out.println("CONTA GENÉRICA: ");
        Noobank noobank = new Noobank(111, 300);
        noobank.getSaldo();
        noobank.depositar(130);
        noobank.getSaldo();
        noobank.sacar(300);
        noobank.getSaldo();
        noobank.credito(100);
        noobank.resetSaldo();

        // Conta Poupança
        System.out.println("\nCONTA POUPANÇA: ");
        ContaPoupanca poupanca = new ContaPoupanca(222, 100);
        poupanca.getSaldo();
        poupanca.setJuros(3);
        poupanca.getTaxaJuros();
        poupanca.pagarJuros();
        poupanca.getSaldo();
        poupanca.resetSaldo();
    
        // Conta Aplicação
        System.out.println("\nCONTA APLICAÇÃO: ");
        ContaAplicacao contaAplicacao = new ContaAplicacao(333, 400);
        contaAplicacao.setTaxaMulta(5);
        contaAplicacao.aplicarDinheiro(200);
        contaAplicacao.getVencida();
        contaAplicacao.getTaxaMulta();
        contaAplicacao.sacarFundoImobiliario(100);
        contaAplicacao.resetSaldo();

        // Conta Salário
        System.out.println("\nCONTA SALARIO: ");
        ContaSalario contaSalario = new ContaSalario (444, 2500);
        contaSalario.setCotasMensais(2);
        contaSalario.getCotasMensais();
        contaSalario.getQtdTransacoes();
        contaSalario.sacar(10);
        contaSalario.sacar(10);
        contaSalario.getSaldo();
        contaSalario.getQtdTransacoes();
        contaSalario.calcularTaxas();
        contaSalario.sacar(20);
        contaSalario.resetSaldo();
        contaSalario.getTaxa();

        // Conta Especial
        System.out.println("\nCONTA ESPECIAL: ");
        ContaEspecial contaEspecial = new ContaEspecial(555, 7000);
        contaEspecial.setTaxaCredito(20);
        contaEspecial.getTaxaCredito();
        contaEspecial.solicitarCredito(20000);
        contaEspecial.aplicarJuros();
        contaEspecial.getFatura();
        contaEspecial.resetSaldo();
    }   
}