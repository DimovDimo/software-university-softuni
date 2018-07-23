function countWordsInAText(text) {
    let words = {};
    let wordPattern = /\w+/g;
    let word = wordPattern.exec(text);
    while (word){
        let currentWord = word[0];
        if (Object.keys(words).includes(currentWord)){
            words[currentWord]++;
        } else {
            words[currentWord] = 1;
        }
        word = wordPattern.exec(text);
    }

    console.log(JSON.stringify(words));
}