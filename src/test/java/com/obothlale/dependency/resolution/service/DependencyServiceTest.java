package com.obothlale.dependency.resolution.service;

import com.obothlale.dependency.resolution.model.DependencyGraph;
import com.obothlale.dependency.resolution.service.impl.DependencyServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class DependencyServiceTest {

    @Test
    public void testResolveDependencyFromStringInput() {
        String input = "A B C\n" +
                "B C E\n" +
                "C G\n" +
                "D A F\n" +
                "E F\n" +
                "F H";

        DependencyService dependencyService = new DependencyServiceImpl();
        String actual = dependencyService.resolve(input);
        String expected = "A C G B E F H\n" +
                "B E F H C G\n" +
                "C G\n" +
                "D F H A C G B E\n" +
                "E F H\n" +
                "F H";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testResolveSingleToken() {
        String input = "A";
        DependencyService dependencyService = new DependencyServiceImpl();
        String actual = dependencyService.resolve(input);
        String expected = "";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testResolveSingleLineDependency() {
        String input = "A B";
        DependencyService dependencyService = new DependencyServiceImpl();
        String actual = dependencyService.resolve(input);
        String expected = "A B";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testResolveDependency() {
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
        String actual = dependencyService.resolve(dependencyGraph);
        String expected = "A C G B E F H\n" +
                "B E F H C G\n" +
                "C G\n" +
                "D F H A C G B E\n" +
                "E F H\n" +
                "F H";
        Assert.assertEquals(expected, actual);
    }
}
