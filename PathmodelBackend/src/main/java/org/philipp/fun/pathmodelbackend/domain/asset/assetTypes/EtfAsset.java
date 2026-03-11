package org.philipp.fun.pathmodelbackend.domain.asset.assetTypes;

/**
 * An ETF asset (e.g. MSCI World, S&amp;P 500, MSCI EM).
 *
 * @param symbol         the ticker symbol
 * @param name           the display name
 * @param expectedReturn the annualized expected return
 * @param volatility     the annualized volatility
 * @param dividendYield  the annualized dividend yield
 * @param ter            the total expense ratio
 */
public record EtfAsset(
        String symbol,
        String name,
        double expectedReturn,
        double volatility,
        double dividendYield,
        double ter
) implements Asset {
}
