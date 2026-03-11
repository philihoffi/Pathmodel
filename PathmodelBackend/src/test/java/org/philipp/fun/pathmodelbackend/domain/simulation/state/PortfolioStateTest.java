package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import org.junit.jupiter.api.Test;
import org.philipp.fun.pathmodelbackend.domain.asset.Asset;
import org.philipp.fun.pathmodelbackend.domain.asset.CashAsset;
import org.philipp.fun.pathmodelbackend.domain.asset.EtfAsset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioStateTest {

    private static Asset asset() {
        return new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);
    }

    private static PortfolioPosition position() {
        return new PortfolioPosition(asset(), 2.0);
    }

    @Test
    void constructorCopiesPositionsDefensively() {
        PortfolioPosition position = new PortfolioPosition(asset(), 2.0);
        List<PortfolioPosition> positions = new ArrayList<>();
        positions.add(position);

        PortfolioState portfolioState = new PortfolioState("Core", positions);
        positions.clear();

        assertEquals(1, portfolioState.positions().size());
        assertSame(position, portfolioState.positions().getFirst());
        assertThrows(UnsupportedOperationException.class,
                () -> portfolioState.positions().add(position));
    }

    @Test
    void constructorRejectsNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new PortfolioState(null, List.of(position())));
    }

    @Test
    void constructorRejectsBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new PortfolioState("   ", List.of(position())));
    }

    @Test
    void constructorRejectsNullPositions() {
        assertThrows(IllegalArgumentException.class,
                () -> new PortfolioState("Core", null));
    }

    @Test
    void constructorRejectsEmptyPositions() {
        assertThrows(IllegalArgumentException.class,
                () -> new PortfolioState("Core", List.of()));
    }

    @Test
    void constructorRejectsNullPositionEntries() {
        assertThrows(NullPointerException.class,
                () -> new PortfolioState(
                        "Core",
                        Arrays.asList(position(), null)));
    }

    @Test
    void totalValueSumsPositionValues() {
        Asset equity = asset();
        Asset cash = new CashAsset("CASH", "Cash", 0.02);
        PortfolioState portfolioState = new PortfolioState(
                "Core",
                List.of(
                        new PortfolioPosition(equity, 2.0),
                        new PortfolioPosition(cash, 10.0)
                ));
        MarketState marketState = new MarketState(Map.of(
                equity, 100.0,
                cash, 1.0));

        assertEquals(210.0, portfolioState.totalValue(marketState));
    }

    @Test
    void totalValueRejectsNullMarketState() {
        assertThrows(IllegalArgumentException.class,
                () -> new PortfolioState("Core", List.of(position()))
                        .totalValue(null));
    }

    @Test
    void totalValueRejectsMissingAssetPrices() {
        Asset equity = asset();
        PortfolioState portfolioState = new PortfolioState(
                "Core",
                List.of(new PortfolioPosition(equity, 2.0)));
        MarketState marketState = new MarketState(Map.of(
                new CashAsset("CASH", "Cash", 0.02), 1.0));

        assertThrows(IllegalArgumentException.class,
                () -> portfolioState.totalValue(marketState));
    }
}
