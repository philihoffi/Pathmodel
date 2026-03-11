package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import java.time.LocalDate;

/**
 * Represents the complete state of a simulation at a point in time.
 *
 * <p>Combines the current portfolio holdings with the current market prices.
 *
 * @param portfolioState the current state of the portfolio
 * @param marketState    the current state of the market
 * @param date           the date of this simulation state
 */
public record SimulationState(
        PortfolioState portfolioState,
        MarketState marketState,
        LocalDate date
) {

    /**
     * Validates that no argument is {@code null}.
     *
     * @param portfolioState the current state of the
     *                       portfolio
     * @param marketState    the current state of the
     *                       market
     * @param date           the date of this simulation
     *                       state
     * @throws IllegalArgumentException if any argument
     *                                  is {@code null}
     */
    public SimulationState {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (portfolioState == null) {
            throw new IllegalArgumentException("Portfolio cannot be null");
        }
        if (marketState == null) {
            throw new IllegalArgumentException("Market cannot be null");
        }
    }
}
