package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import java.util.List;

/**
 * An investment portfolio consisting of multiple positions.
 *
 * <p>The positions list is defensively copied and therefore immutable.
 *
 * @param name      the portfolio name
 * @param positions the list of portfolio positions
 */
public record PortfolioState(
        String name,
        List<PortfolioPosition> positions
) {

    /**
     * Creates a portfolio with a defensive copy of the positions list.
     *
     * @param name      the portfolio name
     * @param positions the list of portfolio positions
     */
    public PortfolioState {
        if (name == null) {
            throw new IllegalArgumentException("Portfolio name cannot be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Portfolio name cannot be blank");
        }
        if (positions == null) {
            throw new IllegalArgumentException("Positions cannot be null");
        }
        if (positions.isEmpty()) {
            throw new IllegalArgumentException("Positions cannot be empty");
        }

        positions = List.copyOf(positions);
    }

    /**
     * Calculates the total market value of this
     * portfolio given current prices.
     *
     * @param marketState the current market state
     *                    with asset prices
     * @return the total portfolio value
     * (sum of quantity &times; price)
     * @throws IllegalArgumentException if marketState
     *                                  is {@code null} or any position holds
     *                                  an asset not present in the market state
     */
    public double totalValue(final MarketState marketState) {
        if (marketState == null) {
            throw new IllegalArgumentException(
                    "Market state cannot be null");
        }

        return positions.stream()
                .mapToDouble(p -> p.quantity()
                        * marketState.getPrice(p.asset()))
                .sum();
    }
}
