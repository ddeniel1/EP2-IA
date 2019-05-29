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
    private double prob(String palavra){
        if(!probabilidades.containsKey(palavra)) return 1;
        double resposta =(double) probabilidades.get(palavra)/totalEmails;
        return resposta;
    }
    public double bayes(Mail email){
        double resposta = 0.5;

            HashMap<String, Integer> palavras = email.getPalavras();
            List<String> keys = palavras.keySet().stream().collect(Collectors.toList());
            for (int i = 0; i<keys.size(); i++){
                String aux = keys.get(i);
                for (int k = 0; k<palavras.get(aux);k++){
                    resposta*=prob(aux);
                }
            }

        return resposta;
    }
    public void addMail(HashMap<String, Integer> palavras){
        List<String> keys = palavras.keySet().stream().collect(Collectors.toList());
        for (int i = 0; i < keys.size();i++) {
           String aux = keys.get(i);
            addPalavra(aux);
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
