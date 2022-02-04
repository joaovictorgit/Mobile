package dadm.quixada.ufc.projeto_itech.Components;

public class Produto {
    private String id;
    private String nome;
    private String tipo;
    private String preco;
    private String quantidade;
    private String descricao;

    public Produto(){}

    public Produto(String id, String nome, String tipo, String preco, String quantidade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
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

    public String getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
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

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
