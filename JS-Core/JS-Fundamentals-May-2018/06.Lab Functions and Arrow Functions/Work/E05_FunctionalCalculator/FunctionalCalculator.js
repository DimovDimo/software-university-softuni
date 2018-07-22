function functionalCalculator(firstNum, seconNum, operator) {
    // debugger;
    // let plus = function ([firstNum, seconNum]) {
    //     return firstNum + seconNum;
    // };
    //
    // let minus = function ([firstNum, seconNum]) {
    //     return firstNum - seconNum;
    // };
    //
    // let multiply = function ([firstNum, seconNum]) {
    //     return firstNum * seconNum;
    // };
    //
    // let divisible = function ([firstNum, seconNum]) {
    //     return firstNum / seconNum;
    // };

    switch (operator){
        case '+':
            return firstNum + seconNum;
        case '-':
            return firstNum - seconNum;
        case '*':
            return firstNum * seconNum;
        case '/':
            return firstNum / seconNum;
    }
}

console.log(functionalCalculator(2, 4, '+'));
console.log(functionalCalculator(3, 3, '/'));
console.log(functionalCalculator(18, -1, '*'));