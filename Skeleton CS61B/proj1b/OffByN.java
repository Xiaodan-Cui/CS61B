public class OffByN implements CharacterComparator{
    private int offset;
    public void OffByN(int N){
        offset=N;}
        @Override
        public boolean equalChars(char a, char b){
            int diff=a-b;
            return diff==offset || diff ==-offset;}


}
