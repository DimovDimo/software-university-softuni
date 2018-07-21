function multiplicationTable(rowAndColLimit) {
    rowAndColLimit = Number(rowAndColLimit);
    console.log('<table border="1">');
    for (let row = 0; row <= rowAndColLimit; row++) {
        let rowResult = [];
        rowResult.push('  <tr>');
        for (let col = 0; col <= rowAndColLimit; col++) {
            if (row === 0 && col === 0){
                rowResult.push('<th>x</th>');
            } else if (row === 0) {
                rowResult.push(`<th>${col}</th>`);
            } else if (col === 0){
                rowResult.push(`<th>${row}</th>`);
            } else {
                rowResult.push(`<td>${row * col}</td>`);
            }
        }

        rowResult.push('</tr>');
        console.log(rowResult.join(''));
    }
    
    console.log('</table>');
}

multiplicationTable(5);