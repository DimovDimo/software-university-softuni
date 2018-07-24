function arenaTier(input) {
    let gladiators = new Map();
    for (let i = 0; i < input.length - 1; i++) {
        if (input[i].indexOf('vs') > -1) {
            let [firstGladiator, secondGladiator] = input[i].split(' vs ');
            if (gladiators.has(firstGladiator) && gladiators.has(secondGladiator)) {
                if (haveTheyCommonTechniques([firstGladiator, secondGladiator])) {
                    let skillsFirstGladiator = sumTotalSkills(gladiators.get(firstGladiator));
                    let skillsSecondGladiator = sumTotalSkills(gladiators.get(secondGladiator));
                    if (skillsFirstGladiator > skillsSecondGladiator){
                        gladiators.delete(secondGladiator);
                    } else if (skillsFirstGladiator < secondGladiator){
                        gladiators.delete(firstGladiator);
                    }
                }
            }

        } else {
            let [gladiator, technique, skill] = input[i].split(' -> ');
            if (!gladiators.has(gladiator)) {
                gladiators.set(gladiator, new Map());
            }
            
            if (!gladiators.get(gladiator).has(technique)) {
                gladiators.get(gladiator).set(technique, 0);
            }

            if (gladiators.get(gladiator).get(technique) < skill){
                gladiators.get(gladiator).set(technique, Number(skill));
            }
        }
    }
    
    function haveTheyCommonTechniques([firstGladiator, secondGladiator]) {
        let techniquesFirstGladiator = Array.from(gladiators.get(firstGladiator).keys());
        let techniquesSecondGladiator = Array.from(gladiators.get(secondGladiator).keys());
        for (const technique of techniquesSecondGladiator) {
            if (techniquesFirstGladiator.includes(technique)){
                return true;
            }
        }

        return false;
    }

    function sumTotalSkills(map) {
        return Array.from(map.values()).reduce((a, b) => a + b);
    }

    let keysSorted = Array.from(gladiators.keys()).sort((g1, g2) => {
        let diff = sumTotalSkills(gladiators.get(g2)) - sumTotalSkills(gladiators.get(g1));
        if (diff === 0){
            return g1.localeCompare(g2);
        } else {
            return diff;
        }
    });

    for (const key of keysSorted) {
        console.log(`${key}: ${sumTotalSkills(gladiators.get(key))} skill`);

        let skillsKeysOrdered = Array.from(gladiators.get(key).keys()).sort((t1, t2) => {
            let diff = gladiators.get(key).get(t2) - gladiators.get(key).get(t1);
            if (diff === 0){
                return t1.localeCompare(t2);
            } else {
                return diff;
            }
            }
        );

        for (const skillKey of skillsKeysOrdered) {
            console.log(`- ${skillKey} <!> ${gladiators.get(key).get(skillKey)}`);
        }
    }
}

arenaTier([
    'Pesho -> Duck -> 400',
    'Julius -> Shield -> 150',
    'Gladius -> Heal -> 200',
    'Gladius -> Support -> 250',
    'Gladius -> Shield -> 250',
    'Pesho vs Gladius',
    'Gladius vs Julius',
    'Gladius vs Gosho',
    'Ave Cesar'
]);

arenaTier([
    'Pesho -> BattleCry -> 400',
    'Gosho -> PowerPunch -> 300',
    'Stamat -> Duck -> 200',
    'Stamat -> Tiger -> 250',
    'Ave Cesar'
])