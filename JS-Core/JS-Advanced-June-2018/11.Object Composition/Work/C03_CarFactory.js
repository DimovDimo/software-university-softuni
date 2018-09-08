function carFactory(input) {
    let car = { model: '',
        engine: {},
        carriage: {},
        wheels: [] };

    car.model = input.model;
    if (input.power < 105){
        car.engine = { power: 90, volume: 1800 };
    } else if (input.power < 160){
        car.engine = { power: 120, volume: 2400 };
    } else {
        car.engine = { power: 200, volume: 3500 };
    }
    
    if (input.carriage === 'hatchback'){
        car.carriage = { type: 'hatchback', color: input.color}
    } else {
        car.carriage = { type: 'coupe', color: input.color}
    }

    let diameter = input.wheelsize % 2 === 0 ?
        input.wheelsize - 1 :
        input.wheelsize;
    for (let i = 0; i < 4; i++) {
        car.wheels.push(diameter);
    }

    return car;
}