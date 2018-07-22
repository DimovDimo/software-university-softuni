function biggestElement(input) {
    let max = Number.NEGATIVE_INFINITY;
    for (let row = 0; row < input.length; row++) {
        for (let col = 0; col < input[row].length; col++) {
            if (input[row][col] > max){
                max = input[row][col];
            }
        }
    }

    return max;
}