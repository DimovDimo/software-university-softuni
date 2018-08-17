function initializeTable() {
    $('#createLink').on('click', createCountry);
    addCountryToTable('Bulgaria', 'Sofia');
    addCountryToTable('Germany', 'Berlin');
    addCountryToTable('Russia', 'Moscow');
    hideButtons();

    function addCountryToTable(country, capital) {
        let tableRow = $('<tr>')
            .append(`<td>${country}</td>`)
            .append(`<td>${capital}</td>`)
            .append($('<td>')
                .append($('<a href="#">[Up]</a>')
                    .on('click', moveUp))
                .append($('<a href="#">[Down]</a>')
                    .on('click', moveDown))
                .append($('<a href="#">[Delete]</a>')
                    .on('click', deleteRow))
            );

        $('#countriesTable').append(tableRow);
        hideButtons();
    }

    function createCountry() {
        let country = $('#newCountryText');
        let capital = $('#newCapitalText');
        addCountryToTable(country.val(), capital.val());
        country.val('');
        capital.val('');
    }

    function moveUp() {
        let row = $(this).parent().parent();
        row.insertBefore(row.prev());
        hideButtons();
    }

    function moveDown() {
        let row = $(this).parent().parent();
        row.insertAfter(row.next());
        hideButtons();
    }

    function deleteRow() {
        $(this).parent().parent().remove();
        hideButtons();
    }
    
    function hideButtons() {
        $('#countriesTable a')
            .css('display', '');
        $('#countriesTable tr:eq(2) a:contains("Up")')
            .css('display', 'none');
        $('#countriesTable tr:last a:contains("Down")')
            .css('display', 'none');
    }
}