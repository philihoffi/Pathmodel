package org.philipp.fun.pathmodelbackend.domain.simulation;

import org.philipp.fun.pathmodelbackend.domain.asset.Asset;
import org.philipp.fun.pathmodelbackend.domain.simulation.state.PortfolioState;

import java.util.List;

/**
 * A request to run a Monte Carlo simulation.
 *
 * <p>The asset list is defensively copied via
 * {@link List#copyOf(java.util.Collection)}
 * to keep this value object immutable.
 *
 * @param portfolio        the portfolio to simulate
 * @param numberOfPaths    the number of Monte Carlo paths
 * @param timeHorizonYears the simulation horizon in years
 * @param assetParameters  the assets in the simulation
 */
public record SimulationRequest(
        PortfolioState portfolio,
        int numberOfPaths,
        int timeHorizonYears,
        List<Asset> assetParameters
) {

    /**
     * Creates a new simulation request.
     * <p>Defensively copies the asset list after validation.
     *
     * @throws IllegalArgumentException if portfolio
     *                                  is {@code null}, numberOfPaths is not
     *                                  positive, timeHorizonYears is not
     *                                  positive, or assetParameters is
     *                                  {@code null} or empty
     * @throws NullPointerException     if assetParameters
     *                                  contains {@code null} elements
     */
    public SimulationRequest {
        if (portfolio == null) {
            throw new IllegalArgumentException(
                    "Portfolio state cannot be null");
        }
        if (numberOfPaths <= 0) {
            throw new IllegalArgumentException(
                    "Number of paths must be positive");
        }
        if (timeHorizonYears <= 0) {
            throw new IllegalArgumentException(
                    "Time horizon must be positive");
        }
        if (assetParameters == null) {
            throw new IllegalArgumentException(
                    "Asset parameters cannot be null");
        }
        if (assetParameters.isEmpty()) {
            throw new IllegalArgumentException(
                    "Asset parameters cannot be empty");
        }

        assetParameters = List.copyOf(assetParameters);
    }
}
