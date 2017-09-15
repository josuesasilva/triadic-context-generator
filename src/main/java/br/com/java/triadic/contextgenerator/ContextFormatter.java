package br.com.java.triadic.contextgenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Josué
 */
public class ContextFormatter implements IOutput {

    public static String SEPARATOR = " ";
    
    private final ArrayList<String> objects;
    private final ArrayList<String> attributes;
    private final ArrayList<String> conditions;
    private final ArrayList<ArrayList<ArrayList<String>>> relations;
    private final File file;

    public ContextFormatter(File file) {
        this.file = file;
        this.objects = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.relations = new ArrayList<>();
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
        return relations;
    }
    
    @Override
    public String generateJSON() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        TriadicContext ctx = new TriadicContext(this);
        return gson.toJson(ctx);
    }
    
    @Override
    public void generateJSON(Writer writer) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        TriadicContext ctx = new TriadicContext(this);
        gson.toJson(ctx, writer);
    }
    
    public boolean readFile() {
        try (Scanner scan = new Scanner(file)) {
            scan.nextLine();
            
            while(scan.hasNextLine()) {
                TriadicSchema line = parseLine(scan.nextLine());
                
                if (!objects.contains(line.getObject()))
                    objects.add(line.getObject());
                
                if (!attributes.contains(line.getAttribute())) 
                    attributes.add(line.getAttribute());
                
                if (!conditions.contains(line.getCondition()))
                    conditions.add(line.getCondition());
            }
            scan.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean buildRelations() {
        objects.forEach((__) -> {
            ArrayList<ArrayList<String>> object = new ArrayList<>();
            attributes.forEach((___) -> object.add(new ArrayList<>()));
            relations.add(object);
        });
        
        try (Scanner scan = new Scanner(file)) {
            scan.nextLine();
            
            while(scan.hasNextLine()) {
                TriadicSchema line = parseLine(scan.nextLine());
                relations.get(objects.indexOf(line.getObject()))
                        .get(attributes.indexOf(line.getAttribute()))
                        .add(line.getCondition());
            }
            System.out.println(relations);
            scan.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    private TriadicSchema parseLine(String line) {
        String[] l = line.split(SEPARATOR);
        String object = l[0];
        String attr = l[1];
        String cond = l[2];
        return new TriadicSchema(object, attr, cond);
    }
    
}
