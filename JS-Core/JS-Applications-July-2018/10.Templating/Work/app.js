(async () => {
    const data = await $.get('./data.json');
    const contactHtml = await $.get('./templates/contact.hbs');
    let contactTemplate = Handlebars.compile(contactHtml);
    let finalData = {contacts: data};
    let resultHtml = contactTemplate(finalData);
    $('#list').append(resultHtml);

    const detailsHtml = await $.get('./templates/details.hbs');
    const detailsTemplate = Handlebars.compile(detailsHtml);
    $('.contact').on('click', function () {
        let index = $(this).attr('data-id');
        let result = detailsTemplate(data[index]);
        $('#details > div').remove();
        $('#details').append(result);
    })
})();