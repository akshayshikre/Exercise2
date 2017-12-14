package com.example.dadasaheb.exercise2;

/**
 * Created by dadasaheb on 14/12/17.
 */

public class Coin {
//    "Id":"4321","Url":"/coins/42/overview",
//            "ImageUrl":"https://www.cryptocompare.com/media/12318415/42.png",
//            "Name":"42","Symbol":"42","CoinName":"42 Coin","FullName":"42 Coin (42)",
//            "Algorithm":"Scrypt","ProofType":"PoW/PoS","FullyPremined":"0",
//            "TotalCoinSupply":"42","PreMinedValue":"N/A",
//            "TotalCoinsFreeFloat":"N/A","SortOrder":"34",
// "Sponsored":false,
//            "Price_USD":38245.779,"Change_USD":-11.7,"Volume_USD":0,"Price_EUR":32180.031,
//            "Change_EUR":-11.7,"Volume_EUR":0,"Price_BTC":2.34,"Change_BTC":-11.7,
//            "Volume_BTC":0.15,"Price_ETH":52.3139,"Change_ETH":-11.7,"Volume_ETH":0},
    String Id,Url,ImageUrl,Name,Symbol,CoinName,FullName,Algorithm,ProofType,FullyPremined,
        TotalCoinSupply,PreMinedValue,TotalCoinsFreeFloat,SortOrder,Price_USD,Change_USD,Volume_USD,Price_EUR,Change_EUR,Volume_EUR,Price_BTC,Change_BTC
            ,Volume_BTC,Price_ETH,Change_ETH,Volume_ETH;
    boolean Sponsored;

    public Coin(String id, String url, String imageUrl, String name, String symbol, String coinName, String fullName, String algorithm, String proofType, String fullyPremined, String totalCoinSupply, String preMinedValue, String totalCoinsFreeFloat, String sortOrder, String price_USD, String change_USD, String volume_USD, String price_EUR, String change_EUR, String volume_EUR, String price_BTC, String change_BTC, String volume_BTC, String price_ETH, String change_ETH, String volume_ETH, boolean sponsored) {
        Id = id;
        Url = url;
        ImageUrl = imageUrl;
        Name = name;
        Symbol = symbol;
        CoinName = coinName;
        FullName = fullName;
        Algorithm = algorithm;
        ProofType = proofType;
        FullyPremined = fullyPremined;
        TotalCoinSupply = totalCoinSupply;
        PreMinedValue = preMinedValue;
        TotalCoinsFreeFloat = totalCoinsFreeFloat;
        SortOrder = sortOrder;
        Price_USD = price_USD;
        Change_USD = change_USD;
        Volume_USD = volume_USD;
        Price_EUR = price_EUR;
        Change_EUR = change_EUR;
        Volume_EUR = volume_EUR;
        Price_BTC = price_BTC;
        Change_BTC = change_BTC;
        Volume_BTC = volume_BTC;
        Price_ETH = price_ETH;
        Change_ETH = change_ETH;
        Volume_ETH = volume_ETH;
        Sponsored = sponsored;
    }
}
