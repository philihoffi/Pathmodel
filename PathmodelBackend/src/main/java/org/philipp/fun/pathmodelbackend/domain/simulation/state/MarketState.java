package org.philipp.fun.pathmodelbackend.domain.simulation.state;

import org.philipp.fun.pathmodelbackend.domain.asset.Asset;

import java.util.Map;

/**
 * A snapshot of market prices for a set of assets at a point in time.
 *
 * <p>The prices map is defensively copied and therefore immutable.
 *
 * @param prices a map from asset to its current price
 */
public record MarketState(Map<Asset, Double> prices) {

    /**
     * Creates a market state with a defensive copy
     * of the prices map.
     *
     * @param prices a map from asset to its current
     *               price
     * @throws IllegalArgumentException if prices is
     *                                  {@code null}, empty, or contains
     *                                  non-positive values
     */
    public MarketState {
        if (prices == null) {
            throw new IllegalArgumentException("Prices cannot be null");
        }
        if (prices.isEmpty()) {
            throw new IllegalArgumentException("Prices cannot be empty");
        }
        if (prices.values().stream().anyMatch(price -> price <= 0)) {
            throw new IllegalArgumentException("Prices must be positive");
        }

        prices = Map.copyOf(prices);
    }

    /**
     * Returns the price of the given asset.
     *
     * @param asset the asset to look up
     * @return the price of the asset
     * @throws IllegalArgumentException if the asset is
     *                                  {@code null} or not present in this market
     *                                  state
     */
    public double getPrice(final Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("Asset cannot be null");
        }

        Double price = prices.get(asset);
        if (price == null) {
            throw new IllegalArgumentException(
                    "Unknown asset: " + asset.symbol());
        }
        return price;
    }
}

