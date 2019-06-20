package com.obothlale.dependency.resolution.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependencyGraph {
    private Map<String, List<String>> tokens;

    public DependencyGraph(Map<String, List<String>> tokens) {
        this.tokens = tokens;
    }

    public DependencyGraph() {
        tokens = new HashMap<String, List<String>>();
    }

    public void addToken(String token) {
        ArrayList<String> initializeEmptyDependencies = new ArrayList<>();
        tokens.putIfAbsent(token, initializeEmptyDependencies);
    }

    public void addDependence(String token, String dependent) {
        tokens.get(token).add(dependent);
    }

    public List<String> getAdjacentDependency(String token) {
        return tokens.get(token);
    }

    public Map<String, List<String>> getTokens() {
        return tokens;
    }

    public String toString(){
        String output = "";
        for(Map.Entry<String,List<String>> entry: tokens.entrySet()){
            output += entry +" "+entry.getValue();
        }
        return output;
    }
}
