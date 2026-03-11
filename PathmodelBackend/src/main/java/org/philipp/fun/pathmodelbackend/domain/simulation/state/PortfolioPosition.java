package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import org.philipp.fun.pathmodelbackend.domain.asset.Asset;

/**
 * A single position within a portfolio.
 *
 * <p>Represents a holding of a specific asset with a given quantity.
 *
 * @param asset    the financial asset held
 * @param quantity the number of units held
 */
public record PortfolioPosition(
        Asset asset,
        double quantity
) {

    /**
     * Creates a portfolio position after validating
     * inputs.
     *
     * @param asset    the financial asset held
     * @param quantity the number of units held
     * @throws IllegalArgumentException if asset is
     *                                  {@code null} or quantity is not positive
     */
    public PortfolioPosition {
        if (asset == null) {
            throw new IllegalArgumentException("Asset cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }
}
