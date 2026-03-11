package org.philipp.fun.pathmodelbackend.domain.asset.assetTypes;

/**
 * A cash or money market asset (e.g. Money Market Fund).
 *
 * @param symbol       the ticker symbol
 * @param name         the display name
 * @param interestRate the annualized interest rate
 */
public record CashAsset(
        String symbol,
        String name,
        double interestRate
) implements Asset {
}
