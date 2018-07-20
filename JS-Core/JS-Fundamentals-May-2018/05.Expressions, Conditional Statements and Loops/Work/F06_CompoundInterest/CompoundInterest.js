function compoundInterest([principalSum, interestRateInPredent, compoundingPeriodInMonths, timespanInYears]) {

    let interestRate = interestRateInPredent / 100;
    let compoundingFrequency = 12 / compoundingPeriodInMonths;

    let result = principalSum *
        ((1 + interestRate/compoundingFrequency)
            ** (compoundingFrequency * timespanInYears));
    console.log(result.toFixed(2));
}

compoundInterest([1500, 4.3, 3, 6]);
compoundInterest([100000, 5, 12, 25]);