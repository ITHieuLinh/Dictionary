package Repository;

import DataAccess.TranslateDAO;
import java.util.HashMap;

public class TranslateRepository implements ITranslateRepository {

    @Override
    public void addNewWord(HashMap<String, String> hm) {
        TranslateDAO.Instance().addNewWord(hm);
    }

    @Override
    public void deleteWord(HashMap<String, String> hm) {
        TranslateDAO.Instance().deleteWord(hm);
    }

    @Override
    public void translate(HashMap<String, String> hm) {
        TranslateDAO.Instance().translate(hm);
    }

    @Override
    public void readFile(HashMap<String, String> hm) {
        TranslateDAO.Instance().readFile(hm);
    }

}
