function cityMarkets(input) {
    let towns = new Map();
    for (const row of input) {
        let [town, product, amountOfSales, priceForOneUnit] = row.split(/ -> | : /g);
        if (!towns.has(town)){
            towns.set(town, new Map());
        }

        // if (!towns.get(town).has(product)){
        //     towns.get(town).set(product, 0);
        // }

        let totalIncome = Number(amountOfSales) * Number(priceForOneUnit);
        towns.get(town).set(product, totalIncome);
    }

    for (const [town, productProperty] of towns) {
        console.log(`Town - ${town}`);
        for (const [product, income] of productProperty) {
            console.log(`$$$${product} : ${income}`);
        }
    }
}