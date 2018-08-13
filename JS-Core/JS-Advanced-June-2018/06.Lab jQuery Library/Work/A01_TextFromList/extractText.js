// function extractText() {
//     let value = $('#items li').toArray()
//         .map(li => $(li).text()).join(', ');
//     $('#result').append(value);
// }

function extractText() {
    let items = $("ul#items li")
        .toArray()
        .map(li => li.textContent)
        .join(", ");
    $("#result").text(items);
}

