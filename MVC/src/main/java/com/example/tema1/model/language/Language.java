package com.example.tema1.model.language;

import com.example.tema1.model.database.Repository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Language {

    public static Language INSTANCE;

    private Languages language = Languages.ROMANA;
    private Map<Languages, Map<String, String>> words;

    private Language() {
        Map<String, String> wordsR = new HashMap<>();
        Map<String, String> wordsS = new HashMap<>();
        Map<String, String> wordsE = new HashMap<>();
        words = new HashMap<>();

        wordsR.put("sign_in", "Conectare");
        wordsS.put("sign_in", "Inciar Sesion");
        wordsE.put("sign_in", "SignIn");

        wordsR.put("register", "Inregistrare");
        wordsS.put("register", "Registro");
        wordsE.put("register", "Register");

        wordsR.put("log_out", "Deconectare");
        wordsS.put("log_out", "Cerrar Sesion");
        wordsE.put("log_out", "LogOut");

        wordsR.put("username", "Nume utilizator: ");
        wordsS.put("username", "Nombre de usuario: ");
        wordsE.put("username", "Username: ");

        wordsR.put("password", "Parola: ");
        wordsS.put("password", "Clave: ");
        wordsE.put("password", "Password: ");

        wordsR.put("last_games", "Ultimele jocuri");
        wordsS.put("last_games", "Los Ultimos Juegos");
        wordsE.put("last_games", "The last games");

        wordsR.put("select_level", "Selecteaza Nivelul");
        wordsS.put("select_level", "Selecciona el Nivel");
        wordsE.put("select_level", "Select Level");

        wordsR.put("level1", "Nivel 1");
        wordsS.put("level1", "Nivel 1");
        wordsE.put("level1", "Level 1");

        wordsR.put("level2", "Nivel 2");
        wordsS.put("level2", "Nivel 2");
        wordsE.put("level2", "Level 2");

        wordsR.put("start", "Start");
        wordsS.put("start", "Comienzo");
        wordsE.put("start", "Start");

        wordsR.put("restart", "Repornire");
        wordsS.put("restart", "Reiniciar");
        wordsE.put("restart", "Restart");

        wordsR.put("back_menu", "Inapoi la Meniu");
        wordsS.put("back_menu", "Volver al Menu");
        wordsE.put("back_menu", "Back to Menu");

        wordsR.put("select_arrow", "Selectare Sageata ");
        wordsS.put("select_arrow", "Seleccionar Flecha ");
        wordsE.put("select_arrow", "Select Arrow ");

        words.put(Languages.ROMANA, wordsR);
        words.put(Languages.ENGLISH, wordsE);
        words.put(Languages.SPANISH, wordsS);

    }

    public static Language getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Language();
        }
        return INSTANCE;
    }

}
