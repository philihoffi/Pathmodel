package org.philipp.fun.pathmodelbackend.domain.simulation;

import org.junit.jupiter.api.Test;
import org.philipp.fun.pathmodelbackend.domain.asset.Asset;
import org.philipp.fun.pathmodelbackend.domain.asset.EtfAsset;
import org.philipp.fun.pathmodelbackend.domain.simulation.state.PortfolioPosition;
import org.philipp.fun.pathmodelbackend.domain.simulation.state.PortfolioState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationRequestTest {

    private static Asset asset() {
        return new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);
    }

    private static PortfolioState portfolioState() {
        return new PortfolioState(
                "Core",
                List.of(new PortfolioPosition(asset(), 2.0)));
    }

    @Test
    void constructorCopiesAssetParametersDefensively() {
        PortfolioState portfolioState = portfolioState();
        Asset asset = asset();
        List<Asset> assetParameters = new ArrayList<>();
        assetParameters.add(asset);

        SimulationRequest request = new SimulationRequest(
                portfolioState, 500, 20, assetParameters);
        assetParameters.clear();

        assertSame(portfolioState, request.portfolio());
        assertEquals(500, request.numberOfPaths());
        assertEquals(20, request.timeHorizonYears());
        assertEquals(1, request.assetParameters().size());
        assertSame(asset, request.assetParameters().getFirst());
        assertThrows(UnsupportedOperationException.class,
                () -> request.assetParameters().add(asset()));
    }

    @Test
    void requestsWithSameValuesAreEqual() {
        PortfolioState portfolio = portfolioState();
        List<Asset> assetParameters = List.of(asset());

        SimulationRequest first = new SimulationRequest(
                portfolio, 500, 20, assetParameters);
        SimulationRequest second = new SimulationRequest(
                portfolio, 500, 20, new ArrayList<>(assetParameters));

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotSame(first, second);
    }

    @Test
    void constructorRejectsNullPortfolioState() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimulationRequest(
                        null, 500, 20, List.of(asset())));
    }

    @Test
    void constructorRejectsNonPositivePathCounts() {
        PortfolioState portfolioState = portfolioState();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new SimulationRequest(
                                portfolioState, 0, 20, List.of(asset()))),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new SimulationRequest(
                                portfolioState, -1, 20, List.of(asset())))
        );
    }

    @Test
    void constructorRejectsNonPositiveTimeHorizons() {
        PortfolioState portfolioState = portfolioState();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new SimulationRequest(
                                portfolioState, 500, 0, List.of(asset()))),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new SimulationRequest(
                                portfolioState, 500, -1, List.of(asset())))
        );
    }

    @Test
    void constructorRejectsNullAssetParameters() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimulationRequest(
                        portfolioState(), 500, 20, null));
    }

    @Test
    void constructorRejectsEmptyAssetParameters() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimulationRequest(
                        portfolioState(), 500, 20, List.of()));
    }

    @Test
    void constructorRejectsNullAssetParameterEntries() {
        assertThrows(NullPointerException.class,
                () -> new SimulationRequest(
                        portfolioState(),
                        500,
                        20,
                        Arrays.asList(asset(), null)));
    }
}
