function countOccurences(input) {
    let targetString = input[0];
    let searchString = input[1];
    let count = 0;
    let startIndex = searchString.indexOf(targetString);

    while (startIndex > -1) {
        count++;
        startIndex = searchString.indexOf(targetString, startIndex + 1);
    }

    console.log(count);
}

countOccurences([
    'the',
    'The quick brown fox jumps over the lay dog.'
]);

countOccurences([
    'ma',
    'Marine mammal training is the training and caring for marine life such as, dolphins, killer whales, sea lions, walruses, and other marine mammals. It is also a duty of the trainer to do mental and physical exercises to keep the animal healthy and happy.'
]);