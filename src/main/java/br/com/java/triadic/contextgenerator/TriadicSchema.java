package br.com.java.triadic.contextgenerator;

/**
 *
 * @author josue
 */
public class TriadicSchema {
    
    private String object;
    private String attribute;
    private String condition;

    public TriadicSchema(String object, String attribute, String condition) {
        this.object = object;
        this.attribute = attribute;
        this.condition = condition;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    
}
