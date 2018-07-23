function concatenateAndReverse(input) {
    let output = [];
    for (let i = input.length - 1; i >= 0; i--) {
        for (let stingIndex = input[i].length - 1; stingIndex >= 0; stingIndex--) {
            output.push(input[i][stingIndex]);
        }
    }

    return output.join('');
}