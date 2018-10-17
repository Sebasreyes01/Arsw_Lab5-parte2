/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    private BlueprintServices bs;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> handlerGetResourceBlueprints() {
        try {
            return new ResponseEntity<>(bs.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, blueprints not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{author}")
    public ResponseEntity<?> handlerGetResourceBlueprintsByAuthor(@PathVariable String author) {
        try {
            return new ResponseEntity<>(bs.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, blueprints of that author not found (remember the API is case sensitive)", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{author}/{bpname}")
    public ResponseEntity<?> handlerGetResourceBlueprintsByAuthor(@PathVariable String author, @PathVariable String bpname) {
        try {
            return new ResponseEntity<>(bs.getBlueprint(author, bpname), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, blueprint not found (remember the API is case sensitive)", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> handlerPostResourceBlueprint(@RequestBody Blueprint bp) {
        try {
            bs.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, the blueprint already exists", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{author}/{bpname}")
    public ResponseEntity<?> handlerPutResourceBlueprint(@PathVariable String author, @PathVariable String bpname, @RequestBody Point[] points) {
        try {
            bs.updateBlueprint(author, bpname, points);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, blueprint not found (remember the API is case sensitive)", HttpStatus.FORBIDDEN);
        }
    }

}

