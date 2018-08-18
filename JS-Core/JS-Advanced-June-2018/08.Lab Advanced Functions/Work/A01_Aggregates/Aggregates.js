function aggregates(nums) {
    function reduce(arr, func) {
        let result = arr.shift();
        for (let nextElement of arr)
            result = func(result, nextElement);
        return result;
    }

    console.log(`Sum = ${reduce(nums, (a, b) => a + b)}`);
    console.log(`Min = ${reduce(nums, (a, b) => {
        if (a <= b){
            return a;
        } else {
            return b;
        }
    })}`);
    console.log(`Max = ${reduce(nums, (a, b) => {
        if (a >= b){
            return a;
        } else {
            return b;
        }
    })}`);
    console.log(`Product = ${reduce(nums, (a, b) => a * b)}`);
    console.log(`Join = ${reduce(nums, (a, b) => a + '' + b)}`);
}