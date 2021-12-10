package dadm.quixada.ufc.trabalho03;

public class Carro {
    private static int contadorId = 0;

    private int id;
    private String nome;
    private String marca;
    private String cor;

    public Carro(){

    }

    public Carro(String nome, String marca, String cor) {
        //this.id = contadorId++;
        this.nome = nome;
        this.marca = marca;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getCor() {
        return cor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return marca + '\'' + nome + " - " + cor;
    }
}
