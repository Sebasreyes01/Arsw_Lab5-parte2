package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

import java.util.Set;

public interface BlueprintsPersistence {

    /**
     *
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    /**
     *
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException;

    /**
     * Gets all the existing blueprints.
     * @return All the existing blueprints.
     * @throws BlueprintNotFoundException
     */
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException;

    /**
     * Gets all the existing blueprints of an specific author
     * @param author The blueprint's author.
     * @return All the blueprints of a given author.
     * @throws BlueprintNotFoundException
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;

    /**
     * Updates the given blueprint.
     * @param author The blueprint's author.
     * @param bpname The blueprint's name.
     * @param points The new blueprint's points.
     * @throws BlueprintNotFoundException
     */
    public void updateBlueprint(String author, String bpname, Point[] points) throws BlueprintNotFoundException;

}
