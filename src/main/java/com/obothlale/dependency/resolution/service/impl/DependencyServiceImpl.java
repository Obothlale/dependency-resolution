package com.obothlale.dependency.resolution.service.impl;

import com.obothlale.dependency.resolution.model.DependencyGraph;
import com.obothlale.dependency.resolution.service.DependencyService;
import com.obothlale.dependency.resolution.utils.OutputUtil;

import java.util.*;

public class DependencyServiceImpl implements DependencyService {

    @Override
    public String resolve(String input) {
        DependencyGraph graph = createGraph(input);
        return resolve(graph);
    }

    @Override
    public String resolve(DependencyGraph dependencyGraph) {
        List<Set<String>> results = new ArrayList<Set<String>>();
        for (Map.Entry<String, List<String>> tokenRoot : dependencyGraph.getTokens().entrySet()) {
            if (hasDependents(tokenRoot)) {
                results.add(depthFirstTraversal(tokenRoot.getKey(), dependencyGraph));
            }
        }
        return printedResults(results);
    }

    private DependencyGraph createGraph(String input) {
        DependencyGraph graph = new DependencyGraph();
        addTokensFromInput(graph, input);
        addDependenciesFromInput(graph, input);
        return graph;
    }

    private void addTokensFromInput(DependencyGraph graph, String input) {
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            List<String> lineTokens = (Arrays.asList(scanner.nextLine().split(" ")));
            for (String token : lineTokens) {
                graph.addToken(token);
            }
        }
    }

    private void addDependenciesFromInput(DependencyGraph graph, String input) {
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().replace(" ", "");
            String token = Character.toString(line.charAt(0));
            line = line.replace(token, "");
            for (int index = 0; index < line.length(); index++) {
                graph.addDependence(token, Character.toString(line.charAt(index)));
            }
        }
    }

    private boolean hasDependents(Map.Entry<String, List<String>> root) {
        return root.getValue().size() > 0;
    }

    private Set<String> depthFirstTraversal(String root, DependencyGraph dependencyGraph) {
        Set<String> visited = new LinkedHashSet<String>();
        Stack<String> stack = new Stack<String>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String token = stack.pop();
            if (!visited.contains(token)) {
                visited.add(token);
                for (String dependency : dependencyGraph.getAdjacentDependency(token)) {
                    stack.push(dependency);
                }
            }
        }
        return visited;
    }

    private String printedResults(List<Set<String>> results) {
        StringBuilder output = new StringBuilder();
        for (Set<String> result : results) {
            output.append(result).append("\n");
        }
        removeLastEndlLine(output);
        return OutputUtil.removeCharacters(output.toString());
    }

    private void removeLastEndlLine(StringBuilder output) {
        if (output.length() > 0) {
            output.setLength(output.length() - 1);
        }
    }
}
