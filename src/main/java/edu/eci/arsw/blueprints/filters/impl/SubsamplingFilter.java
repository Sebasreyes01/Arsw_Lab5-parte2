package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class SubsamplingFilter implements BlueprintFilter {

    private List<Point> points;

    public SubsamplingFilter() {
        points = new ArrayList<>();
    }

    @Override
    public Blueprint filter(Blueprint blueprint) {
        List<Point> blueprintPoints = blueprint.getPoints();
        for(int i = 0; i < blueprintPoints.size();i++) {
            if(i % 2 == 0) {
                points.add(blueprintPoints.get(i));
            }
        }
        Point[] ps = points.toArray(new Point[points.size()]);
        points.clear();
        Blueprint bp = new Blueprint(blueprint.getAuthor(),blueprint.getName(),ps);
        return bp;
    }

}
