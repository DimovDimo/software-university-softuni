// <table border="1">
//     <tr><th>Name</th><th>Town</th></tr>
// <tr><td>Eve</td><td>Sofia</td></tr>
// <tr><td>Nick</td><td>Varna</td></tr>
// <tr><td>Didi</td><td>Ruse</td></tr>
// <tr><td>Tedy</td><td>Varna</td></tr>
// </table>
// <button onclick="colorizeRows()">Colorize</button>
//
// function colorizeRows() {
//     let rows = document.querySelectorAll("table tr");
//     debugger
//     for (let i = 0; i <= rows.length; i+=2) {
//         row[i].style.background = "teal";
//     }
// }

function colorizeRows() {
    let rows = document.
    querySelectorAll("table tr");
    let index = 0;
    for (let row of rows) {
        index++;
        if (index % 2 == 0)
            row.style.background = "teal";
    }
}
