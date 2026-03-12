package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import org.junit.jupiter.api.Test;
import org.philipp.fun.pathmodelbackend.domain.asset.Asset;
import org.philipp.fun.pathmodelbackend.domain.asset.EtfAsset;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioPositionTest {

    private static final LocalDate PURCHASE_DATE = LocalDate.of(2026, 1, 1);

    private static Asset asset() {
        return new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);
    }

    @Test
    void constructorStoresValidatedValues() {
        Asset asset = asset();

        PortfolioPosition position = new PortfolioPosition(asset, PURCHASE_DATE, 2.5);

        assertSame(asset, position.asset());
        assertEquals(PURCHASE_DATE, position.purchaseDate());
        assertEquals(2.5, position.quantity());
    }

    @Test
    void constructorRejectsNullAsset() {
        assertThrows(IllegalArgumentException.class,
                () -> new PortfolioPosition(null, PURCHASE_DATE, 2.5));
    }

    @Test
    void constructorRejectsNullPurchaseDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new PortfolioPosition(asset(), null, 2.5));
    }

    @Test
    void constructorRejectsNonPositiveQuantities() {
        Asset asset = asset();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new PortfolioPosition(asset, PURCHASE_DATE, 0.0)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new PortfolioPosition(asset, PURCHASE_DATE, -0.1))
        );
    }
}
