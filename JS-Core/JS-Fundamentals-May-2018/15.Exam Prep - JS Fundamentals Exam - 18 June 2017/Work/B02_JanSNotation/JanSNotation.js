function janSNotation(input) {
    let numbers = [];
    for (const element
        of input) {
        let type = typeof element;
        if (type === 'number'){
            numbers.push(+element);
        } else {
            if (numbers.length >= 2){
                let num2 = numbers.pop();
                let num1 = numbers.pop();
                switch (element){
                    case '+': numbers.push(num1 + num2); break;
                    case '-': numbers.push(num1 - num2); break;
                    case '*': numbers.push(num1 * num2); break;
                    case '/': numbers.push(num1 / num2); break;
                }
            } else {
                console.log('Error: not enough operands!');
                return;
            }
        }
    }

    if (numbers.length >= 2) {
        console.log('Error: too many operands!');
    } else {
        console.log(numbers[0]);
    }
}

janSNotation(
    [5,
    3,
    4,
    '*',
    '-']

);

janSNotation([
    15,
    '/']
);

janSNotation([
    3,
    4,
    '+'
]);