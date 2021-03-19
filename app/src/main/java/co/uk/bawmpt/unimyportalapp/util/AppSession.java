package co.uk.bawmpt.unimyportalapp.util;

import com.google.firebase.auth.FirebaseUser;

import co.uk.bawmpt.unimyportalapp.model.User;

public class AppSession {
    public static class Session {
        public static User student;
        public static FirebaseUser user;
    }
}
