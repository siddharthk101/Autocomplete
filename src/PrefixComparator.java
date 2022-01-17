import java.util.Comparator;

/**
 * Factor pattern for obtaining PrefixComparator objects
 * without calling new. Users simply use
 *
 *     Comparator<Term> comp = PrefixComparator.getComparator(size)
 *
 * @author owen astrachan
 * @date October 8, 2020
 */
public class PrefixComparator implements Comparator<Term> {

    private int myPrefixSize; // size of prefix

    /**
     * private constructor, called by getComparator
     * @param prefix is prefix used in compare method
     */
    private PrefixComparator(int prefix) {
        myPrefixSize = prefix;
    }


    /**
     * Factory method to return a PrefixComparator object
     * @param prefix is the size of the prefix to compare with
     * @return PrefixComparator that uses prefix
     */
    public static PrefixComparator getComparator(int prefix) {
       return new PrefixComparator(prefix);
    }


    @Override
    public int compare(Term v, Term w) {
        String vword = v.getWord();
        String wword = w.getWord();

        int maxloop = myPrefixSize;
        boolean check = false;
        if (vword.length() < maxloop) {
            maxloop = vword.length();
            check = true;
        }
        if (wword.length() < maxloop) {
            maxloop = wword.length();
            check = true;
        }


        for(int i = 0; i < maxloop; i++){

            if(vword.charAt(i)!=wword.charAt(i)){
                if(vword.charAt(i) > wword.charAt(i)){
                    return 1;
                    }
                    return -1;
                }
            }
        if(check){
            if(vword.equals(wword)){
                return 0;
            }
            if(vword.length()<wword.length()){
                return -1;
            } else
            return 1;
        }
        return 0;
    }
}
