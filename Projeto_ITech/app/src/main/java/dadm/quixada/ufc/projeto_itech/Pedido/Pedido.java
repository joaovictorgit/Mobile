package dadm.quixada.ufc.projeto_itech.Pedido;

public class Pedido {

    private String id;
    private String idProduto;
    private String idUsuario;

    public Pedido(){}

    public Pedido(String id, String idProduto, String idUsuario) {
        this.id = id;
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
    }

    public String getId() {
        return id;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

}
