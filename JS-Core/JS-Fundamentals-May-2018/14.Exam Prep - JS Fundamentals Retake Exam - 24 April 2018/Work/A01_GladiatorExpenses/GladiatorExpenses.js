function gladiatorExpenses(lostFightsCount, helmetPrice, swordPrice, shieldPrice, armorPrice) {
    [lostFightsCount, helmetPrice, swordPrice, shieldPrice, armorPrice] = [lostFightsCount, helmetPrice, swordPrice, shieldPrice, armorPrice].map(Number);

    let helmentTrashed = 0;
    let swordTrashed = 0;
    let shieldTrashed = 0;
    let armorTrashed = 0;

    for (let i = 1; i <= lostFightsCount; i++) {
        if (i % 2 === 0){
            helmentTrashed++;
        }

        if (i % 3 === 0){
            swordTrashed++;
        }

        if (i % 6 === 0){
            shieldTrashed++;
            if (shieldTrashed % 2 === 0){
                armorTrashed++;
            }
        }
    }

    let price = helmentTrashed * helmetPrice +
        swordTrashed * swordPrice +
        shieldTrashed * shieldPrice +
        armorTrashed * armorPrice;
    console.log(`Gladiator expenses: ${price.toFixed(2)} aureus`);
}

gladiatorExpenses(
    7,
    2,
    3,
    4,
    5
);

gladiatorExpenses(
    23,
    12.50,
    21.50,
    40,
    200
);