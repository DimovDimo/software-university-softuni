function oddOrEven(number) {
    if (number % 1 !== 0){
        return 'invalid';
    } else if (number % 2 === 0){
        return 'even';
    } else {
        return 'odd';
    }
}