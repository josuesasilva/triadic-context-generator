package br.com.java.triadic.contextgenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Josué
 */
public class ContextFormatter implements IOutput {

    public static String SEPARATOR = "\t";
    
    private final List<String> objects;
    private final List<String> attributes;
    private final List<String> conditions;
    private final List<List<List<String>>> relations;
    private final File file;

    public ContextFormatter(File file) {
        this.file = file;
        this.objects = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.relations = new ArrayList<>();
    }
    
    public ContextFormatter(File file, List<String> objects, 
            List<String> attributes, List<String> conditions,
            List<List<List<String>>> relations) {
        this.file = file;
        this.objects = objects;
        this.attributes = attributes;
        this.conditions = conditions;
        this.relations = relations;
    }
    
    public ContextFormatter filter(List<String> objects, 
            List<String> attrs, List<String> conds) {
        return new ContextFormatter(this.file, objects, attrs, conds, 
                buildRelations(this.file, objects, attrs, conds));
    }

    @Override
    public List<String> getObjects() {
        return this.objects;
    }

    @Override
    public List<String> getAttributes() {
        return this.attributes;
    }

    @Override
    public List<String> getConditions() {
        return this.conditions;
    }

    @Override
    public List<List<List<String>>> getRelations() {
        return this.relations;
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
                
                if (line == null) continue;
                
                if (!objects.contains(line.getObject()))
                    objects.add(line.getObject());
                
                if (!attributes.contains(line.getAttribute())) 
                    attributes.add(line.getAttribute());
                
                if (!conditions.contains(line.getCondition()))
                    conditions.add(line.getCondition());
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean buildRelations() {
        objects.forEach((__) -> {
            List<List<String>> object = new ArrayList<>();
            attributes.forEach((___) -> object.add(new ArrayList<>()));
            relations.add(object);
        });
        
        try (Scanner scan = new Scanner(file)) {
            scan.nextLine();
            
            while(scan.hasNextLine()) {
                TriadicSchema line = parseLine(scan.nextLine());
                
                if (line == null) continue;
                
                relations.get(objects.indexOf(line.getObject()))
                        .get(attributes.indexOf(line.getAttribute()))
                        .add(line.getCondition());
            }
            System.out.println(relations);
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    private List<List<List<String>>> buildRelations(File file,
            List<String> fobjects, List<String> fattrs, List<String> fconds) {
       
        List<List<List<String>>> rels = new ArrayList<>();
        
        fobjects.forEach((__) -> {
            List<List<String>> o = new ArrayList<>();
            fattrs.forEach((___) -> o.add(new ArrayList<>()));
            rels.add(o);
        });
        
        try (Scanner scan = new Scanner(file)) {
            scan.nextLine();
            
            while(scan.hasNextLine()) {
                TriadicSchema line = parseLine(scan.nextLine());
                
                if (line == null) continue;
                
                if (fobjects.contains(line.getObject()) &&
                        fattrs.contains(line.getAttribute()) &&
                        fconds.contains(line.getCondition())) {
                    rels.get(fobjects.indexOf(line.getObject()))
                        .get(fattrs.indexOf(line.getAttribute()))
                        .add(line.getCondition());   
                }
            }
            
            System.out.println(rels);
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
            return rels;
        }
        return rels;
    }
    
    private TriadicSchema parseLine(String line) {
        String[] l = line.split(SEPARATOR);
        if (l.length > 2) {
            String object = l[0];
            String attr = l[1];
            String cond = l[2];
            return new TriadicSchema(object, attr, cond);
        }
        return null;
    }
    
}
