function processOddNumbers(input) {
    let oddIndexNumbers = input.filter(((x, i) => i % 2 === 1));
    let doubledNumbers = oddIndexNumbers.map(x => x * 2);
    let reversedNumbers = doubledNumbers.reverse();
    console.log(reversedNumbers.join(' '));
}

processOddNumbers([10, 15, 20, 25]);