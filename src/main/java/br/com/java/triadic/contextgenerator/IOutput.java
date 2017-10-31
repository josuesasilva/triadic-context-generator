/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.java.triadic.contextgenerator;

import java.io.Writer;
import java.util.List;

/**
 *
 * @author Josué
 */
public interface IOutput {
    List<String> getObjects();
    List<String> getAttributes();
    List<String> getConditions();
    List<List<List<String>>> getRelations();
    String generateJSON();
    void generateJSON(Writer writer);
}
