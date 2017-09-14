package br.com.java.triadic.contextgenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContextGenerator implements ITriadicContext {
    
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private final String name;
    private final int objectsAmount;
    private final int attributesAmount;
    private final int conditionsAmount;
    private ArrayList<String> objects;
    private ArrayList<String> attributes;
    private ArrayList<String> conditions;
    private ArrayList<ArrayList<ArrayList<String>>> relations;
    
    public ContextGenerator(String name, int objects, int attributes, int conditions) {
        this.name = name;
        this.objectsAmount = objects;
        this.attributesAmount = attributes;
        this.conditionsAmount = conditions;
    }
    
    private void generateObjetcs() {
        ArrayList<String> items = IntStream
                .range(1, objectsAmount + 1)
                .mapToObj(i -> String.valueOf(i))
                .collect(Collectors.toCollection(ArrayList::new));        
        this.objects = items;
    } 
    
    private void generateAttributes() {
        ArrayList<String> items = new ArrayList<>();
        while (items.size() < attributesAmount) {
            alphabet.chars().forEach(c -> {
                if (items.size() == attributesAmount) return;
                String str = String.valueOf((char) c);
                if (!items.contains(str)) items.add(str);
                else items.add(str + str);
            });
        }
        this.attributes = items;
    } 
    
    private void generateConditions() {
        ArrayList<String> items = new ArrayList<>();
        while (items.size() < conditionsAmount) {
            alphabet.toLowerCase().chars().forEach(c -> {
                if (items.size() == conditionsAmount) return;
                String str = String.valueOf((char) c);
                if (!items.contains(str)) items.add(str);
                else items.add(str + str);
            });
        }
        this.conditions = items;
    }
    
    private void generateRelations() {
        ArrayList<ArrayList<ArrayList<String>>> items = new ArrayList<>();
        
        if (this.conditions == null) {
            this.relations = new ArrayList<>();
            return;
        }
        
        for (int i = 0; i < objectsAmount; i++) {
            ArrayList<ArrayList<String>> a = new ArrayList<>();
            
            for (int j = 0; j < attributesAmount; j++) {
                ArrayList<String> r = new ArrayList<>();
                
                for (int k = 0; k < conditionsAmount; k++) {
                    int index = new Random().nextInt(conditionsAmount);
                    
                    if (!r.contains(conditions.get(index))) {
                        r.add(conditions.get(index));
                    }
                }
                a.add(r);
            }
            items.add(a);
        }

        this.relations = items;
    }

    @Override
    public ArrayList<String> getObjects() {
        return objects;
    }

    @Override
    public ArrayList<String> getAttributes() {
        return attributes;
    }

    @Override
    public ArrayList<String> getConditions() {
        return conditions;
    }

    @Override
    public ArrayList<ArrayList<ArrayList<String>>> getRelations() {
        generateRelations();
        return this.relations;
    }

    @Override
    public void generate(Writer writer) {
        generateObjetcs();
        generateAttributes();
        generateConditions();
        generateRelations();
        
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        TriadicContext ctx = new TriadicContext(
                this.name,
                this.objects,
                this.attributes,
                this.conditions,
                this.relations
        );
        gson.toJson(ctx, writer);
    }

    @Override
    public String generate() {
        generateObjetcs();
        generateAttributes();
        generateConditions();
        generateRelations();
        
        Gson gson = new Gson();
        TriadicContext ctx = new TriadicContext(
                this.name,
                this.objects,
                this.attributes,
                this.conditions,
                this.relations
        );
        return gson.toJson(ctx);
    }
    
}