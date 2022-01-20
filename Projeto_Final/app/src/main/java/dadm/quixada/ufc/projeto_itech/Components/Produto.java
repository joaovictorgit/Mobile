package dadm.quixada.ufc.projeto_itech.Components;

public class Produto {
    private String id;
    private String nome;
    private String tipo;
    private String preco;

    public Produto(){}

    public Produto(String id,String nome, String tipo, String preco){
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPreco() {
        return preco;
    }

    public String getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public void setId(String id) {
        this.id = id;
    }
}
