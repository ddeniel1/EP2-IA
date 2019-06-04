import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Probabilidade {
    private HashMap<String, Integer> probabilidades = new HashMap<>();
    private int vocabulario;

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
    private double prob(String palavra){
        if(!probabilidades.containsKey(palavra)) return 1;


        double resposta =(double) (probabilidades.get(palavra)+10000)/(totalPalavras()+this.vocabulario);



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

    private void vocabulario(List<String> outro) {

        int resposta = 0;
        List<String> esse = getKeys();

        for (int i = 0;i< outro.size(); i++){
            if (esse.contains(outro.get(i))){
                resposta++;
                esse.remove(outro.get(i));
            }
        }
        resposta+=esse.size();

        this.vocabulario = resposta;

    }

    public double bayes(Mail email,int outro, List<String> keysOutro){
        vocabulario(keysOutro);
        double resposta = ((double) totalEmails/(totalEmails+outro));

            HashMap<String, Integer> palavras = email.getPalavras();
            List<String> keys = palavras.keySet().stream().collect(Collectors.toList());
            for (int i = 0; i<keys.size(); i++){
                String aux = keys.get(i);
                //for (int k = 0; k<palavras.get(aux);k++){
                    resposta*=prob(aux);
                //}
            }
//        System.out.println(resposta);
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

    @Override public String toString(){
        String resposta = "";
        List<String> keys = probabilidades.keySet().stream().collect(Collectors.toList());
        for (int i = 0; i < keys.size();i++) {
            String aux = keys.get(i);
            resposta = resposta + aux +" : " + prob(aux) + "\n";
        }
        return resposta;
    }
}
