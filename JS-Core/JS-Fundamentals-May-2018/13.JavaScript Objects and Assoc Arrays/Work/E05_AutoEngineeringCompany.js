function autoEngineeringCompany(input) {
    let cars = new Map();
    input.forEach(line => {
        let [brand, model, count] = line.split(' | ');
        if (!cars.has(brand)){
            cars.set(brand, new Map());
        }

        if (!cars.get(brand).has(model)) {
            cars.get(brand).set(model, 0);
        }

        cars.get(brand).set(model, cars.get(brand).get(model) + +count);
    });

    for (const [brand, models] of cars) {
        console.log(brand);
        for (const [model, count] of models) {
            console.log(`###${model} -> ${count}`);
        }
    }
}