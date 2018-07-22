function diagonalSums(matrix) {
    let sumMainDiagonal = 0;
    for (let row = 0; row < matrix.length; row++) {
        let col = row;
        sumMainDiagonal += matrix[row][col];
    }

    let sumSecondaryDiagonal = 0;
    for (let row = 0; row < matrix.length; row++) {
        let col = matrix.length - 1 - row;
        sumSecondaryDiagonal += matrix[row][col];
    }

    console.log(`${sumMainDiagonal} ${sumSecondaryDiagonal}`);
}

diagonalSums([[20, 40],
    [10, 60]]
);
diagonalSums([[3, 5, 17],
    [-1, 7, 14],
    [1, -8, 89]]

);