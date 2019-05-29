import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Mail {
    private HashMap<String,Integer> palavras = new HashMap<String, Integer>();
    private boolean spam;

    public Mail(boolean spam){
        palavras.clear();
        this.spam = spam;
    }
    public HashMap<String,Integer> getPalavras(){
        return palavras;
    }

    @Override public String toString(){
        String resposta = "";
        List<String> keys = palavras.keySet().stream().collect(Collectors.toList());
        for (int i = 0; i < keys.size();i++) {
            String aux = keys.get(i);
            resposta = resposta + aux +" : " + palavras.get(aux) + "\n";
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
          //  System.out.println(emailTemp[i]);
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
