package DAO;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Evento;

public class EventoRepository {

    private DatabaseReference eventoReferencia;

    public EventoRepository() {
        eventoReferencia = FirebaseDatabase.getInstance().getReference("evento");
    }

    public void getTodosEventos(final callbackEvento callBack) {
        eventoReferencia.orderByChild("nome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Evento> eventos = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Evento e = postSnapshot.getValue(Evento.class);
                    eventos.add(e);
                }
                callBack.onAllEventscallback(eventos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addEvento(Evento evento) {
        eventoReferencia.child(evento.getId()).setValue(evento);
    }


    public void updateEvento(Evento evento) {
        eventoReferencia.child(evento.getId()).setValue(evento);
    }

    public void deleteEvento(Evento evento) {
        eventoReferencia.child(evento.getId()).removeValue();
    }


}
