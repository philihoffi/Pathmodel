package org.philipp.fun.pathmodelbackend.domain.asset.assetTypes;

/**
 * Sealed interface for all financial assets.
 *
 * <p>Each asset is identified by its {@code symbol} and {@code name}.
 * Permitted implementations define the specific market properties
 * for each asset class.
 */
public sealed interface Asset permits
        EtfAsset,
        CashAsset,
        CommodityAsset,
        RealEstateAsset {

    /**
     * Returns the ticker symbol of this asset.
     *
     * @return the ticker symbol
     */
    String symbol();

    /**
     * Returns the display name of this asset.
     *
     * @return the asset name
     */
    String name();
}
