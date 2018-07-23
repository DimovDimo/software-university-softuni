function lowestPricesInCities(input) {
    let products = new Map();
    for (const row of input) {
        let [townName, productName, productPrice] = row.split(' | ');
        if (!products.has(productName)){
            products.set(productName, new Map());
        }

        products.get(productName).set(townName, Number(productPrice));
    }

    for (const [key, value] of products) {
        let sortedTowns = Array.from(value.keys()).sort((town1, town2) => {
            return value[town1] - value[town2];
        });
        console.log(`${key} -> ${value.get(sortedTowns[0])} (${sortedTowns[0]})`);
    }
}