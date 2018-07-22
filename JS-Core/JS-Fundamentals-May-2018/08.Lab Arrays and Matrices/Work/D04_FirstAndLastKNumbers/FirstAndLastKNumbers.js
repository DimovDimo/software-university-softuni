function firstAndLastKNumbers(input) {
    let count = input.splice(0, 1);
    let firstElements = input.slice(0, count);
    let lastElements = input.slice(input.length - count, count + 1);
    console.log(firstElements.join(' '));
    console.log(lastElements.join(' '));
}