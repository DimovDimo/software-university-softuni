function printEveryNThElementFromAnArray(input) {
    let step = Number(input.splice(input.length - 1));
    for (let i = 0; i < input.length; i+=step) {
        console.log(input[i]);
    }
}

printEveryNThElementFromAnArray([
    5,
    20,
    31,
    4,
    20,
    2
]);