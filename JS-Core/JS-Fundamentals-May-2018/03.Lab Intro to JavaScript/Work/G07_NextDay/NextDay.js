function nextDay(year, month, day) {
    let passedDate = new Date(year, month-1, day);
    let oneDay = 24 * 60 * 60 * 1000;
    let outputDate = new Date(passedDate.getTime() + oneDay);

    let outputYear = outputDate.getFullYear();
    let outputMonth = outputDate.getMonth() + 1;
    let outputDay = outputDate.getDate();
    console.log(`${outputYear}-${outputMonth}-${outputDay}`);
}

nextDay(2016, 9, 30);