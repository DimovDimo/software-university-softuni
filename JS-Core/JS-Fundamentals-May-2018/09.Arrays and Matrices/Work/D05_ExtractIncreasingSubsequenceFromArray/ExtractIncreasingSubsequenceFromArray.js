function extractIncreasingSubsequenceFromArray(input) {
    let output = [input[0]];
    let biggest = input[0];
    for (let i = 1; i < input.length; i++) {
        if (biggest <= input[i]){
            output.push(input[i]);
            biggest = input[i];
        }
    }

    return output.join('\n');
}

extractIncreasingSubsequenceFromArray([
    1,
    3,
    8,
    4,
    10,
    12,
    3,
    2,
    24
]);