function ancientVSMemory(input) {
    let messages = input.reduce((line1, line2) => line1 + ' ' + line2)
        .split('32656 19759 32763 0 ')
        .map(element => element.split(' 0 ')
            .filter(e => e !== '' && e !== '0'))

    for (const message of messages) {
        if (message.length >= 2){
            let word = message[1].split(' ')
                .map(number => String.fromCharCode(Number(number)));
            console.log(word.join(''));
        }
    }
}

ancientVSMemory([
    '0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 32656 19759 32763 0',
    '5 0 71 111 115 104 111 0 0 0 0 0 0 0 0 0 32656 19759 32763 0 4 0',
    '75 105 114 111 0 0 0 0 0 0 0 0 0 0 32656 19759 32763 0 8 0 86 101',
    '114 111 110 105 107 97 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0'
]);