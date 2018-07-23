function countWordsWithMaps([text]) {
    text = text.toLowerCase();
    let words = new Map();
    let wordPattern = /\w+/g;
    let word = wordPattern.exec(text);
    while (word){
        let currentWord = word[0];
        if (words.has(currentWord)){
            words.set(currentWord, words.get(currentWord) + 1);
        } else {
            words.set(currentWord, 1);
        }
        word = wordPattern.exec(text);
    }

    let sortedWordsKeys = Array.from(words.keys()).sort();

    for (let key of sortedWordsKeys) {
        console.log(`'${key}' -> ${Number(words.get(key))} times`);
    }
}

countWordsWithMaps('Far too slow, you\'re far too slow.');
countWordsWithMaps('JS devs use Node.js for server-side JS. JS devs use JS. -- JS for devs --');
countWordsWithMaps('The man was walking the dog down the road when it suddenly started barking against the other dog. Surprised he pulled him away from it.');