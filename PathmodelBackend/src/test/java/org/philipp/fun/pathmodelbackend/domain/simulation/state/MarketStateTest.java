package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import org.junit.jupiter.api.Test;
import org.philipp.fun.pathmodelbackend.domain.asset.Asset;
import org.philipp.fun.pathmodelbackend.domain.asset.CashAsset;
import org.philipp.fun.pathmodelbackend.domain.asset.EtfAsset;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MarketStateTest {

    private static Asset asset() {
        return new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);
    }

    @Test
    void constructorCopiesPricesDefensivelyAndReturnsKnownPrices() {
        Asset asset = asset();
        Map<Asset, Double> prices = new HashMap<>();
        prices.put(asset, 100.0);

        MarketState marketState = new MarketState(prices);
        prices.put(asset, 200.0);

        assertEquals(100.0, marketState.getPrice(asset));
        assertEquals(1, marketState.prices().size());
        assertThrows(UnsupportedOperationException.class,
                () -> marketState.prices().put(asset(), 50.0));
    }

    @Test
    void constructorRejectsNullPrices() {
        assertThrows(IllegalArgumentException.class,
                () -> new MarketState(null));
    }

    @Test
    void constructorRejectsEmptyPrices() {
        assertThrows(IllegalArgumentException.class,
                () -> new MarketState(Map.of()));
    }

    @Test
    void constructorRejectsNonPositivePrices() {
        Asset asset = asset();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new MarketState(Map.of(asset, 0.0))),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new MarketState(Map.of(asset, -1.0)))
        );
    }

    @Test
    void getPriceRejectsNullAsset() {
        MarketState marketState = new MarketState(Map.of(asset(), 100.0));

        assertThrows(IllegalArgumentException.class,
                () -> marketState.getPrice(null));
    }

    @Test
    void getPriceRejectsUnknownAsset() {
        MarketState marketState = new MarketState(Map.of(asset(), 100.0));

        assertThrows(IllegalArgumentException.class,
                () -> marketState.getPrice(
                        new CashAsset("CASH", "Cash", 0.02)));
    }
}
