function chessboard(side) {
    console.log('<div class="chessboard">');
    for (let row = 0; row < side; row++) {
        console.log('<div>');
        for (let col = 0; col < side; col++) {
            if (row % 2 !== 0) {
                if (col % 2 !== 0) {
                    console.log('    <span class="black"></span>');
                } else {
                    console.log('    <span class="white"></span>');
                }
            } else {
                if (col % 2 !== 0) {
                    console.log('    <span class="white"></span>');
                } else {
                    console.log('    <span class="black"></span>');
                }
            }
        }

        console.log('</div>');
    }

    console.log('</div>');
}