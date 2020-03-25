package DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Evento;

public class EventoRepository {

    private  ArrayList<Evento> eventos;

    public EventoRepository() {
        eventos = new ArrayList<>();
    }

    public ArrayList<Evento> getTodosEventos() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        eventos.add(new Evento("Tchê Linux", "Evento para os gaúchos que querem saber tudo sobre o Linux!", dateFormat.format(date), 200.00, 100, "Porto Alegre/RS"));
        eventos.add(new Evento("SBSeg", "Simpósio Brasileiro de Segurança da informação", dateFormat.format(date), 300.00, 200, "Florianópolis/SC"));
        eventos.add(new Evento("SulPET", "Encontro dos grupos PET da região sul.", dateFormat.format(date), 80.00, 500, "Santa Maria/RS"));
        eventos.add(new Evento("Tchê Linux", "Evento para os gaúchos que querem saber tudo sobre o Linux!", dateFormat.format(date), 200.00, 100, "Porto Alegre/RS"));
        eventos.add(new Evento("SBSeg", "Simpósio Brasileiro de Segurança da informação", dateFormat.format(date), 300.00, 200, "Florianópolis/SC"));
        eventos.add(new Evento("SulPET", "Encontro dos grupos PET da região sul.", dateFormat.format(date), 80.00, 500, "Santa Maria/RS"));
        eventos.add(new Evento("Tchê Linux", "Evento para os gaúchos que querem saber tudo sobre o Linux!", dateFormat.format(date), 200.00, 100, "Porto Alegre/RS"));
        eventos.add(new Evento("SBSeg", "Simpósio Brasileiro de Segurança da informação", dateFormat.format(date), 300.00, 200, "Florianópolis/SC"));
        eventos.add(new Evento("SulPET", "Encontro dos grupos PET da região sul.", dateFormat.format(date), 80.00, 500, "Santa Maria/RS"));
        eventos.add(new Evento("Tchê Linux", "Evento para os gaúchos que querem saber tudo sobre o Linux!", dateFormat.format(date), 200.00, 100, "Porto Alegre/RS"));
        eventos.add(new Evento("SBSeg", "Simpósio Brasileiro de Segurança da informação", dateFormat.format(date), 300.00, 200, "Florianópolis/SC"));
        eventos.add(new Evento("SulPET", "Encontro dos grupos PET da região sul.", dateFormat.format(date), 80.00, 500, "Santa Maria/RS"));
        eventos.add(new Evento("Tchê Linux", "Evento para os gaúchos que querem saber tudo sobre o Linux!", dateFormat.format(date), 200.00, 100, "Porto Alegre/RS"));
        eventos.add(new Evento("SBSeg", "Simpósio Brasileiro de Segurança da informação", dateFormat.format(date), 300.00, 200, "Florianópolis/SC"));
        eventos.add(new Evento("SulPET", "Encontro dos grupos PET da região sul.", dateFormat.format(date), 80.00, 500, "Santa Maria/RS"));
        eventos.add(new Evento("Tchê Linux", "Evento para os gaúchos que querem saber tudo sobre o Linux!", dateFormat.format(date), 200.00, 100, "Porto Alegre/RS"));
        eventos.add(new Evento("SBSeg", "Simpósio Brasileiro de Segurança da informação", dateFormat.format(date), 300.00, 200, "Florianópolis/SC"));
        eventos.add(new Evento("SulPET", "Encontro dos grupos PET da região sul.", dateFormat.format(date), 80.00, 500, "Santa Maria/RS"));
        eventos.add(new Evento("Tchê Linux", "Evento para os gaúchos que querem saber tudo sobre o Linux!", dateFormat.format(date), 200.00, 100, "Porto Alegre/RS"));
        eventos.add(new Evento("SBSeg", "Simpósio Brasileiro de Segurança da informação", dateFormat.format(date), 300.00, 200, "Florianópolis/SC"));
        eventos.add(new Evento("SulPET", "Encontro dos grupos PET da região sul.", dateFormat.format(date), 80.00, 500, "Santa Maria/RS"));
        eventos.add(new Evento("Tchê Linux", "Evento para os gaúchos que querem saber tudo sobre o Linux!", dateFormat.format(date), 200.00, 100, "Porto Alegre/RS"));
        eventos.add(new Evento("SBSeg", "Simpósio Brasileiro de Segurança da informação", dateFormat.format(date), 300.00, 200, "Florianópolis/SC"));
        eventos.add(new Evento("SulPET", "Encontro dos grupos PET da região sul.", dateFormat.format(date), 80.00, 500, "Santa Maria/RS"));
        return eventos;
    }

    public ArrayList<Evento> addEvento(Evento evento) {
        eventos.add(evento);
        return eventos;
    }


}
