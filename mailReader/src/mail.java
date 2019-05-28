import java.util.Collection;
import java.util.HashMap;

public class mail {
    private HashMap<String,Integer> palavras;

    public mail(){
        palavras.clear();
        palavras.
    }
    public void addPalavra(String palavra){
        if(palavras.containsKey(palavra)){
            int atual = palavras.get(palavra);
            palavras.replace(palavra,atual+1);
            return;
        }
        palavras.put(palavra,1);
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
