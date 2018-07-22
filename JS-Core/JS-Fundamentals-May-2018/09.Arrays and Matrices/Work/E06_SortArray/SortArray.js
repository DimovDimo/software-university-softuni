function sortArray(array) {
    let stringAlphabeticalComparator = (first, second) => first.toLowerCase().localeCompare(second.toLowerCase());
    let lengtComparator = (first, second) => {
        let lenghtCompaer = first.length - second.length;
        if (lenghtCompaer === 0){
            return stringAlphabeticalComparator(first, second);
        } else {
            return lenghtCompaer;
        }
    };

    array.sort(lengtComparator);
    console.log(array.join('\n'));
}

sortArray([
    'test',
    'Deny',
    'omen',
    'Default'
]);