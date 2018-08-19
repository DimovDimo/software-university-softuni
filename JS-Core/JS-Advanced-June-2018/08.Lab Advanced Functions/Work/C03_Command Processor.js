function commandProcessor(input) {
    let processor = (function(){
        let result = '';
        return {
            'append': (stringItem) => {result += stringItem},
            'removeStart': (lenghtRemove) => {result = result.slice(lenghtRemove)},
            'removeEnd': (lenghtRemove) => {result = result.slice(0, result.length - lenghtRemove)},
            'print': () => console.log(result)
        }
    }());

    for (const inputElement of input) {
        let [command, item] = inputElement.split(' ');
        processor[command](item);
    }
}

commandProcessor(['append hello',
    'append again',
    'removeStart 3',
    'removeEnd 4',
    'print']
);