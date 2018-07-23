function fromJSONToTable(input) {
    console.log('<table>');
    input.forEach(data => {
        console.log('\t<tr>');
        let person = JSON.parse(data);
        console.log(`		<td>${person.name}</td>`);
        console.log(`		<td>${person.position}</td>`);
        console.log(`		<td>${person.salary}</td>`);
        console.log('\t<tr>');
    });
    console.log('</table>');
}