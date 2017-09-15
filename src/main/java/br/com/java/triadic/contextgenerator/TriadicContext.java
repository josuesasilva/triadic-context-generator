package br.com.java.triadic.contextgenerator;

import java.util.ArrayList;

/**
 *
 * @author Josué
 */
public class TriadicContext {

    private String name;
    private final ArrayList<String> objects;
    private final ArrayList<String> attributes;
    private final ArrayList<String> conditions;
    private final ArrayList<ArrayList<ArrayList<String>>> relations;
    
    public TriadicContext(String name) {
        this.name = name;
        this.objects = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.relations = new ArrayList<>();
    }
    
    public TriadicContext(String name, ArrayList<String> objects, 
            ArrayList<String> attributes, ArrayList<String> conditions, 
            ArrayList<ArrayList<ArrayList<String>>> relations) {
        this.name = name;
        this.objects = objects;
        this.attributes = attributes;
        this.conditions = conditions;
        this.relations = relations;
    }
    
    public TriadicContext(IOutput ctx) {
        this.objects = ctx.getObjects();
        this.attributes = ctx.getAttributes();
        this.conditions = ctx.getConditions();
        this.relations = ctx.getRelations();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getObjects() {
        return objects;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public ArrayList<String> getConditions() {
        return conditions;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getRelations() {
        return relations;
    }
}
