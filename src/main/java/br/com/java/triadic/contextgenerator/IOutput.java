/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.java.triadic.contextgenerator;

import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author Josué
 */
public interface IOutput {
    ArrayList<String> getObjects();
    ArrayList<String> getAttributes();
    ArrayList<String> getConditions();
    ArrayList<ArrayList<ArrayList<String>>> getRelations();
    String generateJSON();
    void generateJSON(Writer writer);
}
