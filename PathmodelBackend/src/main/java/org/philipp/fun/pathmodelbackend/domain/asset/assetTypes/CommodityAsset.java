package org.philipp.fun.pathmodelbackend.domain.asset.assetTypes;

/**
 * A commodity asset (e.g. Gold, Commodity Index).
 *
 * @param symbol         the ticker symbol
 * @param name           the display name
 * @param expectedReturn the annualized expected return
 * @param volatility     the annualized volatility
 * @param storageCost    the annualized storage cost ratio (0 for paper assets)
 */
public record CommodityAsset(
        String symbol,
        String name,
        double expectedReturn,
        double volatility,
        double storageCost
) implements Asset {
}

