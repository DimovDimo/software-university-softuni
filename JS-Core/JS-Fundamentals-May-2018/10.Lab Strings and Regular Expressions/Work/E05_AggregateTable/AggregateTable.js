function aggregateTable(table) {
    let townsIncome = table.map(e => e.split('|').filter(x => x !== ''))
        .map(t => t.map(e => e.trim()));
    let towns = townsIncome.map(t => t[0]);
    let sum = 0;
    townsIncome.forEach(t => sum += Number(t[1]));
    console.log(towns.join(', '));
    console.log(sum);
}

aggregateTable([
    '| Sofia           | 300',
    '| Veliko Tarnovo  | 500',
    '| Yambol          | 275']
);