package dadm.quixada.ufc.projeto_itech.Components;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String rua;
    private String n_rua;
    private String bairro;

    public Usuario(){}

    public Usuario(String id, String nome, String email, String senha, String telefone, String rua, String n_rua, String bairro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.rua = rua;
        this.n_rua = n_rua;
        this.bairro = bairro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getN_rua() {
        return n_rua;
    }

    public void setN_rua(String n_rua) {
        this.n_rua = n_rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
