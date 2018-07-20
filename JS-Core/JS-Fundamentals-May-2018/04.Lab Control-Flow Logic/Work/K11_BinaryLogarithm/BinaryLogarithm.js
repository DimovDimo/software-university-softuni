function binaryLogarithm(args) {
    let output = [];
    for (let index in args) {
        output[index] = Math.log2(args[index]);
    }

    for (let number of output) {
        console.log(number);
    }
}