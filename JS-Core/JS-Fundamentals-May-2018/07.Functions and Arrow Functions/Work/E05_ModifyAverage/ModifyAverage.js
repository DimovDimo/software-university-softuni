function modifyAverage(number) {
    let num = String(number);
    let averageValueOfAllDigits = function(num){
        num = String(num);
        let sumOfDigits = 0;
        for (let i = 0; i < num.length; i++) {
            sumOfDigits += Number(num[i]);
        }

        return sumOfDigits / String(num).length;
    };

    while (averageValueOfAllDigits(num) <= 5){
        num = String(num) + '9';
    }

    console.log(num);
}

modifyAverage(101);
modifyAverage(5835);