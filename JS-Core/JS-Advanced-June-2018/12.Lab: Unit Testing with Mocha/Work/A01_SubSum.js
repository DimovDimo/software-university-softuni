function subSum(arr, startIndex, endIndex) {
    if(Object.prototype.toString.call(arr) !== '[object Array]' ) {
        return NaN;
    }
    
    if (startIndex < 0){
        startIndex = 0;
    }

    if (endIndex >= arr.length){
        endIndex = arr.length - 1;
    }

    let sum = 0;
    for (let i = startIndex; i <= endIndex; i++) {
        if(Object.prototype.toString.call(arr[i]) !== '[object Number]' ) {
            return NaN;
        }

        sum += arr[i];
    }

    return sum;
}

console.log(subSum([1.1, 2.2, 3.3, 4.4, 5.5], -3, 1));