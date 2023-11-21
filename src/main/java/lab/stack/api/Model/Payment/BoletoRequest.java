package lab.stack.api.Model.Payment;

public class BoletoRequest {
    private String boleto;
    private String user_id;

    public String getBoleto() {
        return boleto;
    }

    public BoletoRequest(String boleto, String user_id) {
        this.boleto = boleto;
        this.user_id = user_id;
    }

    public void setBoleto(String boleto) {
        this.boleto = boleto;
    }

    public BoletoRequest() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
