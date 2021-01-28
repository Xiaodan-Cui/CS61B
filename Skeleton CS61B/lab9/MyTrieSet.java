import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B{
    private class Entry{
        boolean isKey;
        HashMap<Character,Entry> Next;
        Entry (boolean blue){
            isKey=blue;
            Next=new HashMap();
        }
    }
    private Entry Root;
    public MyTrieSet() {
        Root=new Entry (false);
    }
    /** Clears all items out of Trie */
    public void clear(){
        Root=new Entry (false);
    }

    /** Returns true if the Trie contains KEY, false otherwise */

    public boolean contains(String key){
        Entry n=Root;
        for (int i=0; i<key.length();i++) {
            if (n.Next.containsKey(key.charAt(i)) == false) {
                return false;
            }
            n=n.Next.get(key.charAt(i));
        }
        return n.isKey;
    }


    /** Inserts string KEY into Trie */
    public void add(String key){
        Entry n=Root;
        for (int i=0; i<key.length();i++) {
            if (n.Next.containsKey(key.charAt(i)) == false) {
                n.Next.put(key.charAt(i),new Entry(false) );
            }
            n=n.Next.get(key.charAt(i));
        }
        n.isKey=true;
    }
    private void colHelp(String s, List<String> x, Entry n){
        if (n.isKey){
            x.add(s);}
        for (char c : n.Next.keySet()){
            colHelp(s+c, x, n.Next.get(c));
        }
    }


    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix){
        List<String> KWP = new ArrayList<>();
        Entry n=Root;
        for (int i=0; i<prefix.length();i++) {
            if (n.Next.containsKey(prefix.charAt(i))) {
                n=n.Next.get(prefix.charAt(i));
            }
            else {return null;}
        }
        colHelp(prefix, KWP, n);
        return KWP;
    }


    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();

    }
}
