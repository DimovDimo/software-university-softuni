function matchAllWords(text) {
    let words = text.match(/\w+/g);
    console.log(words.join('|'));
}