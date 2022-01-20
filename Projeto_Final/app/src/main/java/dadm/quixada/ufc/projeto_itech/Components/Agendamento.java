package dadm.quixada.ufc.projeto_itech.Components;

public class Agendamento {
    String id;
    String nome;
    String descricao;
    String data;
    String check;

    public Agendamento(){};

    public Agendamento(String id,String nome, String descricao, String data, String check) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.check = check;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public String getCheck() {
        return check;
    }

    public String getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public void setId(String id) {
        this.id = id;
    }
}
