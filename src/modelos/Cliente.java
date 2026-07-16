package modelos;

public class Cliente {
    private int id;
    private String cpf;
    private String nome;
    private String email;
    private String rua;
    private int numeroRua;
    private String bairro;
    private int cep;
    private String cidade;
    private String estado;

    public Cliente(int id, String cpf, String nome, String email, String rua, int numeroRua, String bairro, int cep,
            String cidade, String estado) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.numeroRua = numeroRua;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Cliente(String cpf, String nome, String email, String rua, int numeroRua, String bairro, int cep,
            String cidade, String estado) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.rua = rua;
        this.numeroRua = numeroRua;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroRua() {
        return numeroRua;
    }

    public void setNumeroRua(int numeroRua) {
        this.numeroRua = numeroRua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
