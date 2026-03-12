package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import org.philipp.fun.pathmodelbackend.domain.asset.Asset;

import java.time.LocalDate;

/**
 * A single position within a portfolio.
 *
 * <p>Represents a holding of a specific asset with a given quantity.
 *
 * @param asset    the financial asset held
 * @param purchaseDate the date of purchase
 * @param quantity the number of units held
 */
public record PortfolioPosition(
        Asset asset,
        LocalDate purchaseDate,
        double quantity
) {

    /**
     * Creates a portfolio position after validating
     * inputs.
     *
     * @param asset    the financial asset held
     * @param quantity the number of units held
     * @param purchaseDate the date of purchase
     * @throws IllegalArgumentException if asset is
     *                                  {@code null} or quantity is not positive
     */
    public PortfolioPosition {
        if (purchaseDate == null) {
            throw new IllegalArgumentException("Purchase date cannot be null");
        }
        if (asset == null) {
            throw new IllegalArgumentException("Asset cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }
}
