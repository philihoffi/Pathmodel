package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import org.junit.jupiter.api.Test;
import org.philipp.fun.pathmodelbackend.domain.asset.Asset;
import org.philipp.fun.pathmodelbackend.domain.asset.EtfAsset;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SimulationStateTest {

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

    private static MarketState marketState() {
        return new MarketState(Map.of(asset(), 100.0));
    }

    @Test
    void constructorStoresValidatedComponents() {
        PortfolioState portfolioState = portfolioState();
        MarketState marketState = marketState();
        LocalDate date = LocalDate.of(2026, 1, 1);

        SimulationState simulationState = new SimulationState(
                portfolioState, marketState, date);

        assertSame(portfolioState, simulationState.portfolioState());
        assertSame(marketState, simulationState.marketState());
        assertEquals(date, simulationState.date());
    }

    @Test
    void constructorRejectsNullDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimulationState(
                        portfolioState(), marketState(), null));
    }

    @Test
    void constructorRejectsNullPortfolioState() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimulationState(
                        null, marketState(), LocalDate.of(2026, 1, 1)));
    }

    @Test
    void constructorRejectsNullMarketState() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimulationState(
                        portfolioState(), null, LocalDate.of(2026, 1, 1)));
    }
}
