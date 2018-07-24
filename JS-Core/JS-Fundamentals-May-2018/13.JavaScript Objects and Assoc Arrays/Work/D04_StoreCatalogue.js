function storeCatalogue(input) {
    let productsSet = new Set();
    input.forEach(line => productsSet.add(line));
    let products = {};
    productsSet.forEach(line => {
        let [name, price] = line.split(' : ');
        products[name] = +price;
    });

    let group;
    let keys = Object.keys(products).sort();
    for (const key of keys) {
        if (group !== key[0]){
            group = key[0];
            console.log(group);
        }

        console.log(`  ${key}: ${products[key]}`);
    }
}

storeCatalogue([
    'Appricot : 20.4',
    'Fridge : 1500',
    'TV : 1499',
    'Deodorant : 10',
    'Boiler : 300',
    'Apple : 1.25',
    'Anti-Bug Spray : 15',
    'T-Shirt : 10'
]);