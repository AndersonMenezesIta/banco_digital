// Classe abstrata para representar uma conta genérica
public abstract class Conta {
    protected int numero;
    protected double saldo;
    protected Cliente titular;

    public Conta(int numero, Cliente titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public abstract void sacar(double valor);

    public void transferir(double valor, Conta destino) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            System.out.println("Transferência de R$ " + valor + " realizada com sucesso.");
        } else {
            System.out.println("Transferência não realizada. Saldo insuficiente.");
        }
    }

    public double getSaldo() {
        return this.saldo;
    }
}

// Classe para representar uma conta corrente
public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(int numero, Cliente titular, double limite) {
        super(numero, titular);
        this.limite = limite;
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= (this.saldo + this.limite)) {
            this.saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }
}

// Classe para representar uma conta poupança
public class ContaPoupanca extends Conta {
    public ContaPoupanca(int numero, Cliente titular) {
        super(numero, titular);
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }
}

// Classe para representar um cliente
public class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}

// Classe principal para demonstração
public class Banco {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("João", "123.456.789-00");
        Cliente cliente2 = new Cliente("Maria", "987.654.321-00");

        ContaCorrente cc = new ContaCorrente(1001, cliente1, 1000.0);
        ContaPoupanca cp = new ContaPoupanca(2001, cliente2);

        cc.depositar(1500.0);
        cp.depositar(1000.0);

        cc.sacar(2000.0);
        cp.sacar(500.0);

        cc.transferir(300.0, cp);

        System.out.println("Saldo CC: R$ " + cc.getSaldo());
        System.out.println("Saldo CP: R$ " + cp.getSaldo());
    }
}
