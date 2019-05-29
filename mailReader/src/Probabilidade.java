import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Probabilidade {
    private HashMap<String, Integer> probabilidades = new HashMap<>();
    private int totalEmails;
    public Probabilidade(int emails){
        this.totalEmails = emails;
    }
    public void addPalavra(String palavra){
        if(!probabilidades.containsKey(palavra)){
            probabilidades.put(palavra, 1);
        }else{
            int aux = probabilidades.get(palavra);
            probabilidades.replace(palavra, aux+1);
        }
    }
    public double prob(String palavra){
        double resposta =(double) probabilidades.get(palavra)/totalEmails;
        return resposta;
    }
    public void addMail(HashMap<String, Integer> palavras){
        List<String> keys = palavras.keySet().stream().collect(Collectors.toList());
        for (int i = 0; i < keys.size();i++) {
           String aux = keys.get(i);
           for (int k=0;k<palavras.get(aux);k++){
               addPalavra(aux);
           }
        }
    }
    @Override
    public String toString(){
        String resposta = "";
        List<String> keys = probabilidades.keySet().stream().collect(Collectors.toList());
        for (int i = 0; i < keys.size();i++) {
            String aux = keys.get(i);
            resposta = resposta + aux +" : " + prob(aux) + "\n";
        }
        return resposta;
    }
}
