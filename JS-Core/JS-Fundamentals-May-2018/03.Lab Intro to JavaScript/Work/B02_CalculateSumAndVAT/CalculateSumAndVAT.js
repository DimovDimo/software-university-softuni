function sumVAT(input) {
    let sum = 0;
    for (let num in input) {
        sum += Number(input[num]);
    }

    let vat = sum * 0.2;
    let total = sum * 1.2;

    console.log(`sum = ${sum}`);
    console.log(`VAT = ${vat}`);
    console.log(`total = ${total}`);
}

sumVAT([1.20, 2.60, 3.50]);