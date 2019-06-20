package com.obothlale.dependency.resolution.service;

import com.obothlale.dependency.resolution.model.DependencyGraph;

public interface DependencyService {
    String resolve(DependencyGraph dependencyGraph);

    String resolve(String input);
}
