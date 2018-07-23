function captureTheNumbers(text) {
    let regexNumbers = /\d+/g;
    let numbers = [];
    for (let row of text) {
        let nums = regexNumbers.exec(row);
        while (nums){
            numbers.push(nums);
            nums = regexNumbers.exec(row);
        }
    }

    console.log(numbers.join(' '));
}