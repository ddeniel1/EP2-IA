import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
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
    public void addMail(String email) throws java.io.FileNotFoundException, java.io.IOException{
        String emailTratado = trataEmail(email);

        String[] emailTemp = emailTratado.split(" ");
        for (int i = 0; i < emailTemp.length; i++) {
//            System.out.println(emailTemp[i]);
//            emailTemp[i] = emailTemp[i].trim();
            if(!emailTemp[i].trim().isBlank() || !emailTemp[i].trim().equalsIgnoreCase(""))addPalavra(emailTemp[i].trim());

        }
    }

    private String trataEmail(String email) throws java.io.FileNotFoundException, java.io.IOException{
        String resposta=email.toLowerCase();
        FileReader indesejaveisPalavras = new FileReader("./mailReader/noInPalavras.txt");
        FileReader indesejaveisSimbolos = new FileReader("./mailReader/noInSimbolos.txt");
        ArrayList<String> noIn = new ArrayList<>();
        BufferedReader buff;

        //Simbolos
        buff = new BufferedReader(indesejaveisSimbolos);
        while(buff.ready()){
            noIn.add(buff.readLine().trim());
        }
        while(!noIn.isEmpty()){
           // System.out.println(resposta);
            resposta = resposta.replaceAll(noIn.remove(0),"");
        }
        //Palavras
        buff = new BufferedReader(indesejaveisPalavras);
        while(buff.ready()){
            noIn.add(buff.readLine().trim());
        }
        while(!noIn.isEmpty()){
           // System.out.println(resposta);
            resposta = resposta.replaceAll(" "+noIn.remove(0)+" "," ");
        }


        return resposta;
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
