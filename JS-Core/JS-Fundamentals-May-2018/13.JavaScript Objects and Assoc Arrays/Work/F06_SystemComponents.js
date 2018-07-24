function systemComponents(input) {
    let systems = new Map();
    input.forEach(line => {
        let [system, component, subcomponent] = line.split(' | ');
        if (!systems.has(system)){
            systems.set(system, new Map());
        }

        if (!systems.get(system).has(component)){
            systems.get(system).set(component, new Set());
        }

        systems.get(system).get(component).add(subcomponent);
    });

    let systemSortedKeys = Array.from(systems.keys()).sort((k1, k2) => {

        let k1Componets = Array.from(systems.get(k1).keys()).length;
        let k2Componets = Array.from(systems.get(k2).keys()).length;

        let diffCountComponents = k2Componets - k1Componets;
        if (diffCountComponents === 0){
            return k1.localeCompare(k2);
        } else {
            return diffCountComponents;
        }
    });

    for (const systemKey of systemSortedKeys) {
        console.log(systemKey);

        let componentsSorted = Array.from(systems.get(systemKey)).sort((c1, c2) => {

            return c2[1].size - c1[1].size;
            // if (diffComponents === 0){
            //
            // } else {
            //     return diffComponents;
            // }
        });

        for (const component of componentsSorted) {
            console.log(`|||${component[0]}`);
            for (const subcomponent of component[1]) {
                console.log(`||||||${subcomponent}`);
            }
        }
    }
}

systemComponents([
    'SULS | Main Site | Home Page',
    'SULS | Main Site | Login Page',
    'SULS | Main Site | Register Page',
    'SULS | Judge Site | Login Page',
    'SULS | Judge Site | Submittion Page',
    'Lambda | CoreA | A23',
    'SULS | Digital Site | Login Page',
    'Lambda | CoreB | B24',
    'Lambda | CoreA | A24',
    'Lambda | CoreA | A25',
    'Lambda | CoreC | C4',
    'Indice | Session | Default Storage',
    'Indice | Session | Default Security'
]);