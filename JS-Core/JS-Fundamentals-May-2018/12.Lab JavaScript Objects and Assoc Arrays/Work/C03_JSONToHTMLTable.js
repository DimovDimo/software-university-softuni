function jSONToHTMLTable(input) {
    function escapeHtml(text) {
        return text.replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#39;");
    }

    let table = JSON.parse(input);
    console.log('<table>');
    let titles = [];
    titles.push('   <tr>');
    for (let key in table[0]) {
        titles.push(`<th>${escapeHtml(String(key))}</th>`);
    }

    titles.push('</tr>');
    console.log(titles.join(''));

    for (let i = 0; i < table.length; i++) {
        let row = table[i];
        let values = [];
        values.push('   <tr>');
        let valuesArr = Object.values(row);
        for (let value of valuesArr) {
            values.push(`<td>${escapeHtml(String(value))}</td>`);
        }
        values.push('</tr>');
        console.log(values.join(''));
    }


    console.log('</table>');
}

jSONToHTMLTable(`[{"Name":"Pesho <div>-a","Age":20,"City":"Sofia"},
{"Name":"Gosho","Age":18,"City":"Plovdiv"},{"Name":"Angel","Age":18,"City":"Veliko Tarnovo"}]`
);