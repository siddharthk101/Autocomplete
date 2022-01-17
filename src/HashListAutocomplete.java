import java.util.*;

public class HashListAutocomplete implements Autocompletor{
    private static final int MAX_PREFIX = 10;
    private Map<String, List<Term>> myMap;
    private int mySize;

    public HashListAutocomplete(String[] terms, double[] weights){
        if (terms == null || weights == null) {
            throw new NullPointerException("One or more arguments null");
        }

        if (terms.length != weights.length) {
            throw new IllegalArgumentException("terms and weights are not the same length");
        }
        initialize(terms,weights);
    }
    @Override
    public List<Term> topMatches(String prefix, int k) {
        //if(prefix.length() > MAX_PREFIX) prefix = prefix.substring(0,MAX_PREFIX);
        if(!myMap.containsKey((prefix))) return new ArrayList<>();
        List<Term> all = myMap.get(prefix);
        List<Term> list = all.subList(0, Math.min(k,all.size()));
        return list;
    }

    @Override
    public void initialize(String[] terms, double[] weights) {
        myMap = new HashMap<String, List<Term>>();
        for(int i = 0; i < terms.length; i++){
            String current = terms[i];
            for(int j = 0; j < MAX_PREFIX; j++){
                if(current.length() >= j){
                    String pre = current.substring(0,j);
                    myMap.putIfAbsent(pre, new ArrayList<Term>());
                    myMap.get(pre).add(new Term(terms[i],weights[i]));
                }
            }
        }
        for(String key: myMap.keySet()){
            Collections.sort(myMap.get(key), Comparator.comparing(Term::getWeight).reversed());
        }
    }

    @Override
    public int sizeInBytes() {
        if (mySize == 0) {

            for(String key: myMap.keySet()) {
                mySize += BYTES_PER_CHAR*key.length();
                List<Term> list = myMap.get(key);
                for(int i = 0; i < list.size();i++){
                    mySize += (BYTES_PER_DOUBLE + BYTES_PER_CHAR*list.get(i).getWord().length());
                }
            }
        }
        return mySize;
    }
}
