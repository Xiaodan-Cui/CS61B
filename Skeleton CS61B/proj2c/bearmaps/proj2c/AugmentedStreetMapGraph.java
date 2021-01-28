package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.lab9.MyTrieSet;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    HashMap<Point,Node> map;
    List<Point> points;
    MyTrieSet cleannameset;
    HashMap<String,List<Node>> cleannameTonode;
    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();
        map=new HashMap<>();
        points=new ArrayList<>();
        cleannameset=new MyTrieSet();
        cleannameTonode=new HashMap<>();
        for(Node n:nodes){
            Point p=new Point(n.lon(), n.lat());
            map.put(p,n);
            if (n.name()!=null){
                String cleanname=cleanString(n.name());
                cleannameset.add(cleanname);
                if (!cleannameTonode.containsKey(cleanname)){
                    cleannameTonode.put(cleanname, new LinkedList<Node>());
                }
                cleannameTonode.get(cleanname).add(n);
            }
            if(neighbors(n.id())!=null){
                points.add(p);}
        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        WeirdPointSet wps=new WeirdPointSet(points);
        Point nearestpoint=wps.nearest(lon, lat);
        Long id=map.get(nearestpoint).id();
        return id;
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        String cleanprefix=cleanString(prefix);
        List<String> cleannames=cleannameset.keysWithPrefix(cleanprefix);
        HashSet<String> locations=new HashSet<>();
        for(String cleanname:cleannames){
            for (Node node:cleannameTonode.get(cleanname))
            locations.add(node.name());
        }
        return new LinkedList<String>(locations);
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String cleanlocationName=cleanString(locationName);
        List<Map<String,Object>> locations=new LinkedList<>();
        for (Node node:cleannameTonode.get(cleanlocationName)) {
            Map<String,Object> location =new HashMap<>();
            location.put("lat",node.lat());
            location.put("lon",node.lon());
            location.put("name",node.name());
            location.put("id",node.id());
            locations.add(location);
        }
        return locations;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
