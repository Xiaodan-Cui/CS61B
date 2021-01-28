import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap <K, V>  implements Map61B<K, V> {
    private class Entry{
        K Key;
        V Value;
        Entry (K key, V value){
            this.Key=key;
            this.Value=value;
        }
    }
    private ArrayList<Entry>[] buckets;
    private int numberofBuckets=16;
    private int numberofEntries=0;
    private double loadFactor=0.75;
    private HashSet<K> keyset;

    public MyHashMap(){
        buckets = new ArrayList [numberofBuckets];
        keyset = new HashSet<K>();
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Entry>();
        }
    }
    public MyHashMap(int initialSize){
        numberofBuckets = initialSize;
        buckets = new ArrayList [numberofBuckets];
        keyset = new HashSet<K>();
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Entry>();
        }
    }
    public MyHashMap(int initialSize,double loadFactor) {
        buckets = new ArrayList [numberofBuckets];
        keyset = new HashSet<K>();
        numberofBuckets = initialSize;
        loadFactor = loadFactor;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Entry>();
        }
    }
    /** Removes all of the mappings from this map. */
    public void clear (){
        numberofEntries = 0;
        buckets = new ArrayList[16];
        keyset=new HashSet<>();
        numberofBuckets=16;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Entry>();
        }
    }
    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if (keyset.contains(key)){
            return true;
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get (K key) {
        if (!containsKey(key)) {
            return null;
        }
        int i = hash(key);
        for (Entry E : buckets[i]) {
            if (E.Key.equals(key)) {
                return E.Value;
            }
        }
        return null;
    }
    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return numberofEntries;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }
    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            keyset.add(key);
            numberofEntries += 1;
            int i = hash(key);
            if (numberofEntries >= buckets.length * loadFactor) {
                resize(buckets.length * 2);
                i = hash(key);
            }
            buckets[i].add(new Entry(key,value));
        }
        else {
            int i = hash(key);
            for (Entry EE : buckets[i]) {
                if (EE.Key.equals(key)) {
                    EE.Value = value;
                }
            }
        }
    }

    private void resize(int newsize){
        numberofBuckets=newsize;
        ArrayList<Entry> newlist=new ArrayList<> ();
        for (K key: keyset) {
            newlist.add(new Entry(key,get(key)));
        }
        buckets = new ArrayList[numberofBuckets];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Entry>();
        }
        for (Entry E : newlist) {
            put(E.Key, E.Value);
        }
    }
    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        return keyset;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
        if (containsKey(key)) {
            keyset.remove(key);
            numberofEntries -= 1;
            int i = hash(key);
            if (numberofEntries <= buckets.length * loadFactor && numberofEntries>16) {
                resize(buckets.length/2);
                i = hash(key);
            }
            ArrayList<Entry> temp=buckets[i];
            for(int g=0; g<temp.size();g++) {
                if(temp.get(g).Key.equals(key)){
                V temp2=temp.get(g).Value;
                temp.remove(g);
                return temp2;}
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
        if (containsKey(key) && get(key).equals(value)) {
            keyset.remove(key);
            numberofEntries -= 1;
            int i = hash(key);
            if (numberofEntries <= buckets.length * loadFactor && numberofEntries>16) {
                resize(buckets.length/2);
                i = hash(key);
            }
            ArrayList<Entry> temp=buckets[i];
            for(int g=0; g<temp.size();g++) {
                if(temp.get(g).Key.equals(key)){
                    V temp2=temp.get(g).Value;
                    temp.remove(g);
                    return temp2;}
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keyset.iterator();
    }
}