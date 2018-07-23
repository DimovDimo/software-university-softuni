function cappyJuice(input) {
    let juices = {};
    input.forEach(juice => {
        let [juiceName, juiceQuantity] = juice.split(' => ');
        if (!juices.hasOwnProperty(juiceName)){
            juices[juiceName] = +juiceQuantity;
        } else {
            let value = juices[juiceName];
            delete juices[juiceName];
            juices[juiceName] = +value + +juiceQuantity;
        }
    });

    for (const key of Object.keys(juices)) {
        let bottles = Math.floor(juices[key] / 1000);
        if (bottles > 0){
            console.log(`${key} => ${bottles}`);
        }
    }
}

cappyJuice([
    'Orange => 2000',
    'Peach => 1432',
    'Banana => 450',
    'Peach => 600',
    'Strawberry => 549'
]);