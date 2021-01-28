import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    List<Bear> finalbears;
    List<Bed> finalbeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        quicksortbed(beds,bears);
    }
    private void partitionbed (List<Bed> beds,Bear bear,List<Bed> less,List<Bed> equal, List<Bed> greater){
        for(Bed bed:beds){
            if (bed.compareTo(bear)<0){
                less.add(bed);
            }
            else if (bed.compareTo(bear)==0){
                equal.add(bed);
            }
            else {
                greater.add(bed);
            }
        }
    }
    private void partitionbear (List<Bear> bears, Bed bed,List<Bear> less,List<Bear> equal, List<Bear> greater){
        for(Bear bear:bears){
            if (bear.compareTo(bed)<0){
                less.add(bear);
            }
            else if (bear.compareTo(bed)==0){
                equal.add(bear);
            }
            else {
                greater.add(bear);
            }
        }
    }
    private List<Bed> catenatebed(List<Bed> b1, List<Bed> b2){
        List<Bed> nlist=new ArrayList<>();
        for(Bed b :b1){
            nlist.add(b);
        }
        for(Bed b :b2){
            nlist.add(b);
        }
        return nlist;
    }
    private List<Bear> catenatebear(List<Bear> b1, List<Bear> b2){
        List<Bear> nlist=new ArrayList<>();
        for(Bear b :b1){
            nlist.add(b);
        }
        for(Bear b :b2){
            nlist.add(b);
        }
        return nlist;
    }
    private void quicksort(List<Bed> beds, List<Bear> bears) {
        List<Bed> lessbed = new ArrayList<>();
        List<Bed> equalbed = new ArrayList<>();
        List<Bed> greaterbed = new ArrayList<>();
        List<Bear> lessbear = new ArrayList<>();
        List<Bear> equalbear = new ArrayList<>();
        List<Bear> greaterbear = new ArrayList<>();
        Bear pivotbear = bears.get(0);
        partitionbed(beds, pivotbear, lessbed, equalbed, greaterbed);
        Bed pivotbed = equalbed.get(0);
        partitionbear(bears, pivotbed, lessbear, equalbear, greaterbear);
        Pair<List<Bed>, List<Bear>> less = quicksort(lessbed, lessbear);
        lessbear = less.second();
        lessbed = less.first();
        Pair<List<Bed>, List<Bear>> greater = quicksort(greaterbed, greaterbear);
        greaterbed = greater.first();
        greaterbear = greater.second();
        finalbears = catenatebear(catenatebear(lessbear, equalbear), greaterbear);
        finalbeds = catenatebed(catenatebed(lessbed, equalbed), greaterbed);
        return new Pair<>(finalbeds,finalbears);
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        return finalbears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return finalbeds;
    }
}
