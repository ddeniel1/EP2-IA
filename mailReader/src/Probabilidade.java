import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Probabilidade {
    private HashMap<String, Integer> probabilidades = new HashMap<>();

    public List<String> getKeys(){
        return probabilidades.keySet().stream().collect(Collectors.toList());
    }
    public int getTotalEmails() {
        return totalEmails;
    }

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
    private double prob(String palavra, List<String> outro){
        if(!probabilidades.containsKey(palavra)) return 1;


        double resposta =(double) (probabilidades.get(palavra)+1)/(totalPalavras()+vocabulario(outro));



        return resposta;
    }
    private int totalPalavras(){
        int resposta=0;
        List<String> esse = getKeys();
        for (int i=0; i<esse.size();i++){
            resposta+=probabilidades.get(esse.get(i));
        }
        return resposta;
    }

    private int vocabulario(List<String> outro) {

        int resposta = 0;
        List<String> esse = getKeys();

        for (int i = 0;i< outro.size(); i++){
            if (esse.contains(outro.get(i))){
                resposta++;
                esse.remove(outro.get(i));
            }
        }
        resposta+=esse.size();

        return resposta;

    }

    public double bayes(Mail email,int outro, List<String> keysOutro){
        double resposta = ((double) totalEmails/(totalEmails+outro));

            HashMap<String, Integer> palavras = email.getPalavras();
            List<String> keys = palavras.keySet().stream().collect(Collectors.toList());
            for (int i = 0; i<keys.size(); i++){
                String aux = keys.get(i);
                for (int k = 0; k<palavras.get(aux);k++){
                    resposta*=prob(aux,keysOutro);
                }
            }
        System.out.println(resposta);
        return resposta;
    }
    public void addMail(HashMap<String, Integer> palavras){
        List<String> keys = palavras.keySet().stream().collect(Collectors.toList());
        for (int i = 0; i < keys.size();i++) {
           String aux = keys.get(i);
           for (int k = 0; k<palavras.get(aux); k++)
            addPalavra(aux);
        }
    }

    public String toString(List<String> outro){
        String resposta = "";
        List<String> keys = probabilidades.keySet().stream().collect(Collectors.toList());
        for (int i = 0; i < keys.size();i++) {
            String aux = keys.get(i);
            resposta = resposta + aux +" : " + prob(aux,outro) + "\n";
        }
        return resposta;
    }
}
