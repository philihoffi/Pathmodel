package org.philipp.fun.pathmodelbackend.domain.simulation;

import org.junit.jupiter.api.Test;
import org.philipp.fun.pathmodelbackend.domain.asset.Asset;
import org.philipp.fun.pathmodelbackend.domain.asset.EtfAsset;
import org.philipp.fun.pathmodelbackend.domain.simulation.state.MarketState;
import org.philipp.fun.pathmodelbackend.domain.simulation.state.PortfolioPosition;
import org.philipp.fun.pathmodelbackend.domain.simulation.state.PortfolioState;
import org.philipp.fun.pathmodelbackend.domain.simulation.state.SimulationState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PathResultTest {

    private static SimulationState simulationState() {
        Asset asset = new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);
        PortfolioState portfolioState = new PortfolioState(
                "Core",
                List.of(new PortfolioPosition(asset, 3.0)));
        MarketState marketState = new MarketState(Map.of(asset, 100.0));

        return new SimulationState(
                portfolioState,
                marketState,
                LocalDate.of(2026, 1, 1));
    }

    @Test
    void constructorCopiesSimulationStatesDefensively() {
        SimulationState simulationState = simulationState();
        List<SimulationState> simulationStates = new ArrayList<>();
        simulationStates.add(simulationState);

        PathResult result = new PathResult(simulationStates);
        simulationStates.clear();

        assertEquals(1, result.simulationStates().size());
        assertSame(simulationState, result.simulationStates().getFirst());
        assertThrows(UnsupportedOperationException.class,
                () -> result.simulationStates().add(simulationState));
    }

    @Test
    void constructorRejectsNullSimulationStates() {
        assertThrows(IllegalArgumentException.class,
                () -> new PathResult(null));
    }

    @Test
    void constructorRejectsEmptySimulationStates() {
        assertThrows(IllegalArgumentException.class,
                () -> new PathResult(List.of()));
    }

    @Test
    void constructorRejectsNullStateEntries() {
        assertThrows(NullPointerException.class,
                () -> new PathResult(Arrays.asList(simulationState(), null)));
    }
}
