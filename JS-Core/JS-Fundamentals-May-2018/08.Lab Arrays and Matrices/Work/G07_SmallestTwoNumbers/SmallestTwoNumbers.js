function smallestTwoNumbers(input) {
    return input.sort((a, b) => a - b).slice(0, 2);
}