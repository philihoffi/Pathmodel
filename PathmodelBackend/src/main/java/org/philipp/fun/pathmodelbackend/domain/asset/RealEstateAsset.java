package org.philipp.fun.pathmodelbackend.domain.asset;

/**
 * A real estate asset (e.g. REIT Index).
 *
 * @param symbol               the ticker symbol
 * @param name                 the display name
 * @param expectedRentReturn   the annualized expected rent return
 * @param expectedAppreciation the annualized expected capital appreciation
 * @param volatility           the annualized volatility
 */
public record RealEstateAsset(
        String symbol,
        String name,
        double expectedRentReturn,
        double expectedAppreciation,
        double volatility
) implements Asset {
}
