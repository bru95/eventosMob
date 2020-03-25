package DAO;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Auth {

    private FirebaseAuth firebaseAuth;

    public Auth() {
        firebaseAuth = FirebaseAuth.getInstance();
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
