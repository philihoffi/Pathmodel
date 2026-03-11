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

class SimulationResultTest {

    private static PathResult pathResult() {
        Asset asset = new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);
        PortfolioState portfolioState = new PortfolioState(
                "Core",
                List.of(new PortfolioPosition(asset, 3.0)));
        MarketState marketState = new MarketState(Map.of(asset, 100.0));
        SimulationState simulationState = new SimulationState(
                portfolioState,
                marketState,
                LocalDate.of(2026, 1, 1));

        return new PathResult(List.of(simulationState));
    }

    @Test
    void constructorCopiesPathsDefensively() {
        PathResult path = pathResult();
        List<PathResult> paths = new ArrayList<>();
        paths.add(path);

        SimulationResult result = new SimulationResult(paths);
        paths.clear();

        assertEquals(1, result.paths().size());
        assertSame(path, result.paths().getFirst());
        assertThrows(UnsupportedOperationException.class,
                () -> result.paths().add(path));
    }

    @Test
    void constructorRejectsNullPaths() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimulationResult(null));
    }

    @Test
    void constructorRejectsEmptyPaths() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimulationResult(List.of()));
    }

    @Test
    void constructorRejectsNullPathEntries() {
        assertThrows(NullPointerException.class,
                () -> new SimulationResult(Arrays.asList(pathResult(), null)));
    }
}
