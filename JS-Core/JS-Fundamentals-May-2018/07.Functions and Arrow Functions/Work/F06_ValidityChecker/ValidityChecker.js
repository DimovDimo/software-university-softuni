function validityChecker([x1, y1, x2, y2]) {
    let isValidDistance = function ([x1, y1, x2, y2]) {
        let x = x1 - x2;
        let y = y1 - y2;
        let distance = Math.sqrt(x ** 2 + y ** 2);
        let cordinates = `{${x1}, ${y1}} to {${x2}, ${y2}} `;
        if (Number.isInteger(distance)) {
            return cordinates + 'is valid';
        } else {
            return cordinates + 'is invalid';
        }
    };

    console.log(isValidDistance([x1, y1, 0, 0]));
    console.log(isValidDistance([x2, y2, 0, 0]));
    console.log(isValidDistance([x1, y1, x2, y2]));
}