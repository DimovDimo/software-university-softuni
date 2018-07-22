function negativePositiveNumbers(input) {
    let output = [];
    for (let inputElement of input) {
        if (inputElement >= 0){
            output.push(inputElement);
        } else {
            output.unshift(inputElement);
        }
    }

    output.forEach(element => console.log(element));
}