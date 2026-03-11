package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import org.junit.jupiter.api.Test;
import org.philipp.fun.pathmodelbackend.domain.asset.Asset;
import org.philipp.fun.pathmodelbackend.domain.asset.EtfAsset;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioPositionTest {

    private static Asset asset() {
        return new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);
    }

    @Test
    void constructorStoresValidatedValues() {
        Asset asset = asset();

        PortfolioPosition position = new PortfolioPosition(asset, 2.5);

        assertSame(asset, position.asset());
        assertEquals(2.5, position.quantity());
    }

    @Test
    void constructorRejectsNullAsset() {
        assertThrows(IllegalArgumentException.class,
                () -> new PortfolioPosition(null, 2.5));
    }

    @Test
    void constructorRejectsNonPositiveQuantities() {
        Asset asset = asset();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new PortfolioPosition(asset, 0.0)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new PortfolioPosition(asset, -0.1))
        );
    }
}
