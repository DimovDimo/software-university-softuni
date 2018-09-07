function getFibonator() {
    let num1 = 0;
    let num2 = 1;
    return function () {
        let currentValueOfNum2 = num2;
        num2 = currentValueOfNum2 + num1;
        num1 = currentValueOfNum2;
        return currentValueOfNum2;
    }
}