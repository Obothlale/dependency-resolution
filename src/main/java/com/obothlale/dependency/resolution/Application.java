package com.obothlale.dependency.resolution;

import com.obothlale.dependency.resolution.model.DependencyGraph;
import com.obothlale.dependency.resolution.service.DependencyService;
import com.obothlale.dependency.resolution.service.impl.DependencyServiceImpl;

public class Application {

    public static void main(String[] args) {
        DependencyGraph dependencyGraph = new DependencyGraph();
        dependencyGraph.addToken("A");
        dependencyGraph.addToken("B");
        dependencyGraph.addToken("C");
        dependencyGraph.addToken("E");
        dependencyGraph.addToken("G");
        dependencyGraph.addToken("D");
        dependencyGraph.addToken("F");
        dependencyGraph.addToken("H");
        dependencyGraph.addDependence("A", "B");
        dependencyGraph.addDependence("A", "C");
        dependencyGraph.addDependence("B", "C");
        dependencyGraph.addDependence("B", "E");
        dependencyGraph.addDependence("C", "G");
        dependencyGraph.addDependence("D", "A");
        dependencyGraph.addDependence("D", "F");
        dependencyGraph.addDependence("E", "F");
        dependencyGraph.addDependence("F", "H");
        DependencyService dependencyService = new DependencyServiceImpl();

        System.out.println(dependencyService.resolve(dependencyGraph));
    }


}
