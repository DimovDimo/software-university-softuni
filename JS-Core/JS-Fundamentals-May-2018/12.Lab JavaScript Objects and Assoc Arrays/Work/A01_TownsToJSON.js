function townsToJSON(table) {
    let townOblect = {};
    let towns = [];
    for (let i = 0; i < table.length; i++) {
        let townElements = table[i]
            .replace(/(\s*\|\s*)/g, '|')
            .split('|')
            .filter(e => e !== '');
        if (i === 0){
            townElements.forEach(townElement => townOblect[townElement] = '')
        } else {
            let currentTown = {};
            currentTown['Town'] = townElements[0];
            currentTown['Latitude'] = +townElements[1];
            currentTown['Longitude'] = +townElements[2];
            towns.push(currentTown);
        }
    }

    console.log(JSON.stringify(towns));
}

townsToJSON(['| Town | Latitude | Longitude |',
    '| Sofia | 42.696552 | 23.32601 |',
    '| Beijing | 39.913818 | 116.363625 |']
)