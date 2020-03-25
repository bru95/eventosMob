package DAO;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Auth {

    private FirebaseAuth firebaseAuth;
    private Activity act;

    public Auth(Activity act) {
        firebaseAuth = FirebaseAuth.getInstance();
        this.act = act;
    }

    public Task<AuthResult> login(String email, String senha) {
        return firebaseAuth.signInWithEmailAndPassword(email, senha);
    }

    public boolean logout() {
        firebaseAuth.signOut();
        if (firebaseAuth.getCurrentUser() != null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificaUsrLogado() {
        if (firebaseAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }

}
