function magicMatrices(matrix) {
    let sumFunction = (a, b) => a + b;
    let targetSum = matrix[0].reduce((a, b) => sumFunction(a, b));
    for (let row = 1; row < matrix.length; row++) {
        let rowSum = matrix[row].reduce((a, b) => sumFunction(a, b));
        if (targetSum !== rowSum){
            return false;
        } 
    }

    for (let coll = 0; coll < matrix[0].length; coll++) {
        let colArray = [];
        for (let row = 0; row < matrix.length; row++) {
            colArray.push(matrix[row][coll]);
        }

        let colSum = colArray.reduce((a, b) => sumFunction(a, b));
        if (targetSum !== colSum){
            return false;
        }
    }

    return true;
}

magicMatrices([
    [4, 5, 6],
    [6, 5, 4],
    [5, 5, 5]]
);