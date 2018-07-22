function printArrayWithGivenDelimiter(input) {
    let delimiter = input.splice(input.length - 1);
    return input.join(delimiter);
}