package br.com.java.triadic.contextgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josué
 */
public class TriadicContext {

    private String name;
    private final List<String> objects;
    private final List<String> attributes;
    private final List<String> conditions;
    private final List<List<List<String>>> relations;
    
    public TriadicContext(String name) {
        this.name = name;
        this.objects = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.relations = new ArrayList<>();
    }
    
    public TriadicContext(String name, List<String> objects, 
            List<String> attributes, List<String> conditions, 
            List<List<List<String>>> relations) {
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

    public List<String> getObjects() {
        return objects;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public List<List<List<String>>> getRelations() {
        return relations;
    }
}
