package org.philipp.fun.pathmodelbackend.domain.asset;

import org.junit.jupiter.api.Test;
import org.philipp.fun.pathmodelbackend.domain.asset.assetTypes.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AssetTest {

    @Test
    void etfAccessors() {
        EtfAsset asset = new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);

        assertEquals("MXWO", asset.symbol());
        assertEquals("MSCI World", asset.name());
        assertEquals(0.07, asset.expectedReturn());
        assertEquals(0.15, asset.volatility());
        assertEquals(0.02, asset.dividendYield());
        assertEquals(0.002, asset.ter());
    }

    @Test
    void cashAccessors() {
        CashAsset asset = new CashAsset(
                "CASH", "Money Market", 0.02);

        assertEquals("CASH", asset.symbol());
        assertEquals("Money Market", asset.name());
        assertEquals(0.02, asset.interestRate());
    }

    @Test
    void commodityAccessors() {
        CommodityAsset asset = new CommodityAsset(
                "GLD", "Gold",
                0.04, 0.16, 0.004);

        assertEquals("GLD", asset.symbol());
        assertEquals("Gold", asset.name());
        assertEquals(0.04, asset.expectedReturn());
        assertEquals(0.16, asset.volatility());
        assertEquals(0.004, asset.storageCost());
    }

    @Test
    void realEstateAccessors() {
        RealEstateAsset asset = new RealEstateAsset(
                "REIT", "REIT Index",
                0.03, 0.02, 0.14);

        assertEquals("REIT", asset.symbol());
        assertEquals("REIT Index", asset.name());
        assertEquals(0.03, asset.expectedRentReturn());
        assertEquals(0.02, asset.expectedAppreciation());
        assertEquals(0.14, asset.volatility());
    }

    @Test
    void equalAssets() {
        Asset asset1 = new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);
        Asset asset2 = new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);

        assertEquals(asset1, asset2);
        assertEquals(asset1.hashCode(), asset2.hashCode());
    }

    @Test
    void differentAssets() {
        Asset asset1 = new EtfAsset(
                "MXWO", "MSCI World",
                0.07, 0.15, 0.02, 0.002);
        Asset asset2 = new CommodityAsset(
                "GLD", "Gold",
                0.04, 0.16, 0.004);

        assertNotEquals(asset1, asset2);
    }

    @Test
    void differentTypeSameSymbol() {
        Asset etf = new EtfAsset(
                "X", "ETF X",
                0.07, 0.15, 0.02, 0.002);
        Asset cash = new CashAsset(
                "X", "Cash X", 0.02);

        assertNotEquals(etf, cash);
    }
}
