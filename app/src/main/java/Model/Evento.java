package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Evento implements Serializable {

    private String id;
    private String nome;
    private String descricao;
    private String data;
    private Double valor;
    private Integer vagas;
    private String local;

    private ArrayList<Participante> participantes = new ArrayList<>();;

    public Evento() {

    }

    public Evento(String id, String nome, String descricao, String data, Double valor, Integer vagas, String local) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.vagas = vagas;
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

    public void addParticipante(Participante p) {
        this.participantes.add(p);
    }

    public boolean procuraParticipante(Participante p) {
        for(int i = 0; i < participantes.size(); i++) {
            if(p.getEmail().equals(participantes.get(i).getEmail())){
                return true;
            }
        }
        return false;
    }
}
