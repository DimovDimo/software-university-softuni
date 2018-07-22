function lastKNumbersSequence(n, k) {
    let arr = [1];
    while (arr.length < n){
        let sliced;
        if (arr.length < k) {
            sliced = arr.slice(0, k + 1);
        } else {
            sliced = arr.slice(
                arr.length - k);
        }

        let sum = sliced.reduce((a, b) => a + b, 0);
        arr.push(sum);
    }

    console.log(arr.join(' '));
}

lastKNumbersSequence(6, 3);