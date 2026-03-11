package org.philipp.fun.pathmodelbackend.domain.simulation;

import java.util.List;

/**
 * The result of a Monte Carlo simulation.
 *
 * <p>Contains all simulated paths. The list is defensively copied
 * and therefore immutable.
 *
 * @param paths the list of simulated path results
 */
public record SimulationResult(List<PathResult> paths) {

    /**
     * Creates a simulation result with a defensive
     * copy of the paths list.
     *
     * @param paths the list of simulated path results
     * @throws IllegalArgumentException if paths is
     *                                  {@code null} or empty
     */
    public SimulationResult {
        if (paths == null) {
            throw new IllegalArgumentException("Paths cannot be null");
        }
        if (paths.isEmpty()) {
            throw new IllegalArgumentException("Paths cannot be empty");
        }
        paths = List.copyOf(paths);
    }
}

