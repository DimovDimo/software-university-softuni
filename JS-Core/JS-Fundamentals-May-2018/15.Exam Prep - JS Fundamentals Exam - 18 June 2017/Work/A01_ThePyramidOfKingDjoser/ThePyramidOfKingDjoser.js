function thePyramidOfKingDjoser(base, increment) {
    [base, increment] = [base, increment].map(Number);
    let height = 0;
    let step = 0;
    let stone = 0;
    let marble = 0;
    let lapis = 0;
    let gold = 0;
    do {
        base = base - step;
        let currentStone = (base - 2)**2;
        if (base === 1 || base === 2){
            gold += base**2;
        } else {
            stone += currentStone;
            let decoration = (base**2) - currentStone;
            if ((height + 1) % 5 === 0) {
                lapis += decoration;
            } else {
                marble += decoration;
            }
        }

        height++;
        step = 2;
    } while (base - 2 > 0)

    stone = Math.ceil(stone*increment);
    marble = Math.ceil(marble*increment);
    lapis = Math.ceil(lapis*increment);
    gold = Math.ceil(gold*increment);
    height = Math.floor(height*increment);

    console.log(`Stone required: ${stone}`);
    console.log(`Marble required: ${marble}`);
    console.log(`Lapis Lazuli required: ${lapis}`);
    console.log(`Gold required: ${gold}`);
    console.log(`Final pyramid height: ${height}`);
}

thePyramidOfKingDjoser(11, 0.75);