package ag;


public class Item {
    private String nome;
    private int peso;
    private int valor;
    private int qtd;

    public Item(String nome, int peso, int valor, int qtd) {
        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
        this.qtd = qtd;
    }

    public Item() {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    
    
}
