package Controller;

import android.content.Context;

import DAO.EventoRepository;
import Model.Evento;
import Model.Participante;
import Utils.sharedPreferencesController;

public class EventoDetalhesController {

    private Context ctx;

    public EventoDetalhesController(Context ctx) {
        this.ctx = ctx;
    }


    public void insereParticipante(Evento ev, String nome, String email) {
        sharedPreferencesController shared = new sharedPreferencesController(this.ctx);
        shared.setInfoParticipante(nome, email);

        ev.addParticipante(new Participante(nome, email));

        EventoRepository db = new EventoRepository();
        db.updateEvento(ev);

    }
}
