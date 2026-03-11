package org.philipp.fun.pathmodelbackend.domain.simulation;

import org.philipp.fun.pathmodelbackend.domain.simulation.state.SimulationState;

import java.util.List;

/**
 * The result of a single simulated path through time.
 *
 * <p>Contains the chronological list of simulation states,
 * each representing the portfolio and market state at a
 * specific point in time. The list is defensively copied.
 *
 * @param simulationStates the chronological list of
 *                         simulation states
 */
public record PathResult(
        List<SimulationState> simulationStates) {

    /**
     * Creates a path result with a defensive copy of
     * the simulation states list.
     *
     * @param simulationStates the chronological list of
     *                         simulation states
     * @throws IllegalArgumentException if simulationStates
     *                                  is {@code null} or empty
     */
    public PathResult {
        if (simulationStates == null) {
            throw new IllegalArgumentException(
                    "Simulation states cannot be null");
        }
        if (simulationStates.isEmpty()) {
            throw new IllegalArgumentException(
                    "Simulation states cannot be empty");
        }
        simulationStates = List.copyOf(simulationStates);
    }
}

