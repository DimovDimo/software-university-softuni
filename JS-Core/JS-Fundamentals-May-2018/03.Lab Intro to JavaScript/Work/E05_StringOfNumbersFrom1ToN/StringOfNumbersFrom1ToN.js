function StringOfNumbersFrom1ToN(number) {
    number = Number(number);
    let result = "";
    for (let i = 1; i <= number; i++) {
        result += i;
    }

    console.log(result);
}

StringOfNumbersFrom1ToN("11");