function attachEvents() {
    function buttonSelected() {
        $('.selected').removeClass('selected');
        $(this).addClass('selected');
    }

    $('a.button').on('click', buttonSelected);
}
