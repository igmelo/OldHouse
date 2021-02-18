package fetin_oldhouse;

import com.mysql.cj.conf.StringProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CheckManager {

    private static CheckManager instance;

    public StringProperty lastCheck;
    
    public static CheckManager getInstance() {
        if (instance == null) {
            instance = new CheckManager();
        }
        return instance;
    }

    private DAOCheck daoCheck;

    private CheckManager() {
        daoCheck = DAOCheck.getInstance();
    }

    public void saveCheck(String tag) {
        Check check = new Check(tag, LocalDateTime.now());
        daoCheck.insere(check);
    }

    public static boolean validarTag(String tag) {
        return (tag.length() == 8);
    }

    public Boolean verificaAutorizacao(String tag) {

        DAOIdoso idoaux = new DAOIdoso();
        ArrayList<Idoso> ido = idoaux.selecionaTodos();
        for (Idoso user : ido) {

            if (user.getTag_idoso().equals(tag)) {
                return true;

            }
        }
        return false;
    }

}
