function heroicInventory(input) {
    let heroes = [];
    for (let row of input) {
        let [heroName, heroLevel, itemElements] = row.split(' / ');
        heroLevel = Number(heroLevel);
        let items = [];
        if (row.split(' / ').length > 2){
            items = itemElements.split(', ');
        }
        let hero = {
            name: heroName,
            level: heroLevel,
            items: items
        };

        heroes.push(hero);
    }

    console.log(JSON.stringify(heroes))
}