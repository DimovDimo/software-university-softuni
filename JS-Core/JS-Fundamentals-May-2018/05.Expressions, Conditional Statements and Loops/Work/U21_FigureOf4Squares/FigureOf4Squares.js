function figureOf4Squares(number) {
    let rows = number % 2 === 0 ?
                    number - 1:
                    number;
    let cols = 2 * number - 1;
    for (let row = 1; row <= rows; row++) {
        let rowString = [];
        for (let col = 1; col <= cols; col++) {
            if (row === 1 ||
                row === rows ||
                row === Math.ceil(rows / 2)) {
                if (col === 1 ||
                    col === cols ||
                    col === Math.ceil(cols / 2)) {
                    rowString.push('+');
                } else {
                    rowString.push('-');
                }
            } else {
                if (col === 1 ||
                    col === cols ||
                    col === Math.ceil(cols / 2)) {
                    rowString.push('|');
                } else {
                    rowString.push(' ');
                }
            }
        }

        if (rowString.length > 0) 
        console.log(rowString.join(''));
    }
}

figureOf4Squares(4);
figureOf4Squares(5);
figureOf4Squares(6);
figureOf4Squares(7);