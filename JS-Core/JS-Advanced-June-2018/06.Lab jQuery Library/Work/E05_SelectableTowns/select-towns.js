function attachEvents() {
    $('#items > li').on('click', function () {
        if (this.hasAttribute('data-selected')) {
            $(this).removeAttr('data-selected');
            $(this).css('background-color', '#fff');
        } else {
            $(this).attr('data-selected', true);
            $(this).css('background-color', '#ddd');
        }
    });

    $('#showTownsButton').on('click', function () {
        let towns = $('#items > li[data-selected=true]')
            .toArray()
            .map(li => $(li).text())
            .join(', ');

        $('#selectedTowns').text(`Selected towns: ${towns}`);
    });
}