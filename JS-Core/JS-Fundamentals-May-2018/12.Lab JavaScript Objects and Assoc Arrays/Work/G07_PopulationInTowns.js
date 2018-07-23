function populationInTowns(input) {
    let towns = new Map();
    for (const town of input) {
        let tokens = town.split(' <-> ');
        let townName = String(tokens[0]);
        let townPopulation = Number(tokens[1]);
        if (!towns.has(townName)){
            towns.set(townName, 0);
        }

        towns.set(townName, towns.get(townName) + townPopulation);
    }

    for (const [town, population] of towns) {
        console.log(`${town} : ${population}`);
    }
}