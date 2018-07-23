function scoreToHTML(input) {
    function escapeHtml(text) {
        return text.replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#39;");
    }

    console.log('<table>');
    console.log('  <tr><th>name</th><th>score</th></tr>');
    let table = JSON.parse(input);
    for (let i = 0; i < table.length; i++) {
        let row = table[i];
        let name = escapeHtml(String(row['name']));
        let score = escapeHtml(String(row['score']));
        console.log(` <tr><td>${name}</td><td>${score}</td></tr>`);
    }

    console.log('</table>');
}