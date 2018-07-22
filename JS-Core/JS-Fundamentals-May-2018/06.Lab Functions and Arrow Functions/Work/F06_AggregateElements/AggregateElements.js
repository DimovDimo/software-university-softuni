function AggregateElements(nums) {
    console.log(aggregate(nums, 0, (a, b) => a + b));
    console.log(aggregate(nums, 0, (a, b) => a + 1 / b));
    console.log(aggregate(nums, '', (a, b) => a + b));

    function aggregate(array, initialValue, func) {
        for (let i = 0; i < array.length; i++) {
            initialValue = func(initialValue, array[i]);
        }

        return initialValue;
    }
}