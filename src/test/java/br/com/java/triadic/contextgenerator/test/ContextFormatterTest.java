package br.com.java.triadic.contextgenerator.test;

import br.com.java.triadic.contextgenerator.ContextFormatter;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author josue
 */
public class ContextFormatterTest {
    
    ContextFormatter cf;
    
    @Before
    public void setUp() {
        File f = new File("/home/josue/Downloads/dados.txt");
        cf = new ContextFormatter(f);
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void readFile() {
         assertTrue(cf.readFile());
     }
     
     @Test
     public void getObjects() {
         cf.readFile();
         assertEquals(cf.getObjects().size(), 6);
     }
     
     @Test
     public void getAttrs() {
         cf.readFile();
         assertEquals(cf.getAttributes().size(), 22);
     }
     
     @Test
     public void getConds() {
         cf.readFile();
         assertEquals(cf.getConditions().size(), 16);
     }
     
     @Test
     public void buildRelations() {
         cf.readFile();
         assertTrue(cf.buildRelations());
     }
}
