function extractText(text) {
    let startIndex = text.indexOf('(');
    let endIndex = text.indexOf(')', startIndex);
    let output = [];

    while (startIndex > -1 && endIndex > -1) {
        output.push(text.substring(startIndex + 1, endIndex));

        startIndex = text.indexOf('(', endIndex);
        endIndex = text.indexOf(')', startIndex);
    }

    console.log(output.join(', '));
}

extractText('Rakiya (Bulgarian brandy) is self-made liquor (alcoholic drink)');