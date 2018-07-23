function sumByTown(input) {
    let towns = {};
    for (let i = 1; i < input.length; i+=2) {
        let name = String(input[i-1]);
        let income = Number(input[i]);
        if (Object.keys(towns).includes(name)){
            towns[name] += income;
        } else {
            towns[name] = income;
        }
    }

    console.log(JSON.stringify(towns));
}