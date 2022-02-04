package dadm.quixada.ufc.projeto_itech.Agendamento;

public class AgendamentoModel {
    private String id;
    private String idUsuario;
    private String tipoDispositivo;
    private String modeloDispositivo;
    private String problema;
    private String opcaoEntrega;
    private String dia;
    private String horario;
    private String status;

    AgendamentoModel(){}

    public AgendamentoModel(String id, String idUsuario, String tipoDispositivo, String modeloDispositivo, String problema, String opcaoEntrega, String dia, String horario, String status) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.tipoDispositivo = tipoDispositivo;
        this.modeloDispositivo = modeloDispositivo;
        this.problema = problema;
        this.opcaoEntrega = opcaoEntrega;
        this.dia = dia;
        this.horario = horario;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public String getModeloDispositivo() {
        return modeloDispositivo;
    }

    public void setModeloDispositivo(String modeloDispositivo) {
        this.modeloDispositivo = modeloDispositivo;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getOpcaoEntrega() {
        return opcaoEntrega;
    }

    public void setOpcaoEntrega(String opcaoEntrega) {
        this.opcaoEntrega = opcaoEntrega;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
