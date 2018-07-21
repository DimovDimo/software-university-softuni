function rounding(args) {
    let number = Number(args[0]);
    let precision = Number(args[1]);
    if (precision > 15){
        precision = 15;
    }

    let result = number.toFixed(precision);
    if (String(result).length >= String(number).length) {
        return number;
    }

    return result;
}