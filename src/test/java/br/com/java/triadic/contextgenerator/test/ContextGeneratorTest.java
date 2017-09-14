package br.com.java.triadic.contextgenerator.test;

import br.com.java.triadic.contextgenerator.ContextGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author josue
 */
public class ContextGeneratorTest {
    
    ContextGenerator generator;
    
    @Before
    public void setUp() {
        generator = new ContextGenerator("teste", 100, 100, 100);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void generateObjects() {
        generator.generate();
        assertEquals(generator.getObjects().size(), 100);
    }
    
    @Test
    public void generateAttrs() {
        generator.generate();
        assertEquals(generator.getAttributes().size(), 100);
    }
    
    @Test
    public void generateConds() {
        generator.generate();
        assertEquals(generator.getConditions().size(), 100);
    }
}
