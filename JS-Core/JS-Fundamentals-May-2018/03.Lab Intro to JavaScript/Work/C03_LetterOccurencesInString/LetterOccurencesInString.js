function LetterOccurencesInString(string, letter) {
    let count = 0;
    for (let index in string) {
        if (string[index] === letter) {
            count++;
        }
    }

    console.log(count);
}