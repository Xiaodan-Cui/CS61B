public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> LLD= new LinkedListDeque();
        for (int i=0;i<word.length();i++){
            LLD.addLast(word.charAt(i));}
        return LLD;
    }
    public boolean isPalindrome(String word){
        Deque<Character> W = wordToDeque(word);
        while (W.size()>1){
            if (W.removeFirst()!=W.removeLast()){
                return false;}
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        return true;
    }
}