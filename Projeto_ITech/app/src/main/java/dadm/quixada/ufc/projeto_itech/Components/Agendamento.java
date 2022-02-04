package dadm.quixada.ufc.projeto_itech.Components;

public class Agendamento {
    String id;
    String nome;
    String modelo;
    String descricao;
    String data;
    String horario;
    String check;

    public Agendamento(){};

    public Agendamento(String id,String nome, String modelo, String descricao, String data, String horario , String check) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
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

    public String getModelo() {
        return modelo;
    }

    public String getHorario() {
        return horario;
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

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
