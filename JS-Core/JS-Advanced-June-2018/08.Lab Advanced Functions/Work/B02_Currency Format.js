function currencyFormat(valueInput) {
    function currencyFormatter(separator, symbol, symbolFirst, value) {
        let result = Math.trunc(value) + separator;
        result += value.toFixed(2).substr(-2,2);
        if (symbolFirst) return symbol + ' ' + result;
        else return result + ' ' + symbol;
    }

    function getDollarFormatter(currencyFormatter) {
        function formatter(value) {
            currencyFormatter(',', '$', true, value);
        }

        return formatter;
    }

    return getDollarFormatter(currencyFormatter);
    // let dollarFormatterString = getDollarFormatter(currencyFormatter);
    // console.log(dollarFormatterString(5345));   // $ 5345,00
    // console.log(dollarFormatterString(3.1429)); // $ 3,14
    // console.log(dollarFormatterString(2.709));  // $ 2,71

}