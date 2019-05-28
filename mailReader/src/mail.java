import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Mail {
    private HashMap<String,Integer> palavras = new HashMap<String, Integer>();
    private boolean spam;

    public Mail(boolean spam){
        palavras.clear();
        this.spam = spam;
    }

    @Override public String toString(){
        String resposta = "";
        String[] keys = (String[]) palavras.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            resposta = resposta + keys[i] +" : " + palavras.get(keys[i]) + "\n";
        }
        return resposta;
    }

    public void addPalavra(String palavra){
        if(palavras.containsKey(palavra)){

            int atual = palavras.get(palavra);
            palavras.replace(palavra,atual+1);
            return;
        }
        palavras.put(palavra,1);
    }
    public void addMail(String email){

        String[] emailTemp = email.split(" ");
        for (int i = 0; i < emailTemp.length; i++) {
            System.out.println(emailTemp[i]);
            addPalavra(emailTemp[i]);

        }
    }
    public int tamVocabulario(){
        return palavras.size();
    }
    public long totalPalavras(){
        long total = 0;
        Collection<Integer> valores = palavras.values();
        for (int i :valores) {
            total += (long) i;
        }
        return total;
    }
    public int get(String key){
        return palavras.get(key);
    }

}
