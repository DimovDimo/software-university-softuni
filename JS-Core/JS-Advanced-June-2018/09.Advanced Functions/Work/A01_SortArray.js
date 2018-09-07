function sortArray(arr, type) {
    if (type === "asc"){
        return arr.sort((a, b) => a - b);
    } else {
        return arr.sort((a, b) => b - a);
    }
}