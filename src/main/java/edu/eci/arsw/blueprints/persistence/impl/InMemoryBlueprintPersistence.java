package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String,String>, Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pointsTriangle = new Point[]{new Point(0,0), new Point(5,5), new Point(0,10)};
        Point[] pointsSquare = new Point[]{new Point(0,0), new Point(0,5), new Point(5,5), new Point(5,0)};
        Point[] pointsRectangle = new Point[]{new Point(0,0), new Point(0,5), new Point(10,5), new Point(10,0)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        Blueprint blueprintTriangle = new Blueprint("Sebastian","Triangle",pointsTriangle);
        Blueprint blueprintSquare = new Blueprint("Sebastian","Square",pointsSquare);
        Blueprint blueprintRectangle = new Blueprint("Santiago","Rectangle",pointsRectangle);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(blueprintTriangle.getAuthor(),blueprintTriangle.getName()), blueprintTriangle);
        blueprints.put(new Tuple<>(blueprintSquare.getAuthor(),blueprintSquare.getName()), blueprintSquare);
        blueprints.put(new Tuple<>(blueprintRectangle.getAuthor(),blueprintRectangle.getName()), blueprintRectangle);

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        try {
            return blueprints.get(new Tuple<>(author, bprintname));
        } catch (Exception e) {
            throw new BlueprintNotFoundException("Blueprint not found");
        }
    }

    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        Set<Blueprint> blueprintSet = new LinkedHashSet<>();
        for (Tuple<String, String> t : blueprints.keySet()) {
            blueprintSet.add(blueprints.get(t));
        }
        if(blueprintSet.isEmpty()) {
            throw new BlueprintNotFoundException("There are no blueprints");
        } else {
            return blueprintSet;
        }
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> blueprintSet = new LinkedHashSet<>();
        for (Tuple<String, String> t : blueprints.keySet()) {
            if (t.getElem1().equals(author)) {
                blueprintSet.add(blueprints.get(t));
            }
        }
        if(blueprintSet.isEmpty()) {
            throw new BlueprintNotFoundException("There are no blueprints of that author");
        } else {
            return blueprintSet;
        }
    }

    @Override
    public void updateBlueprint(String author, String bpname, Point[] points) throws BlueprintNotFoundException {
        Blueprint bp = getBlueprint(author, bpname);
        bp.setPoints(points);
        blueprints.replace(new Tuple<>(bp.getAuthor(), bp.getName()),bp);
    }

}
