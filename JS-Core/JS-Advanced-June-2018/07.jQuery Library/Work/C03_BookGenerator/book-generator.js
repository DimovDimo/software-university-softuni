function createBook(selector, title, author, iSBN) {
    // createBook("#wrapper", "Alice in Wonderland", "Lewis Carroll", 1111);

    let container = $(selector);
    let fragment = document.createDocumentFragment();
    let divBook = $('<div>');
    let titleP = $('<p>');
    let authorP = $('<p>');
    let isbnP = $('<p>');
    let selectBtn = $('<button>Select</button>');
    let deselectBtn = $('<button>Deselect</button>');

    divBook.attr('id', 'book1');
    divBook.css('border', 'medium none');

    titleP.addClass('title');
    titleP.text(title);

    authorP.addClass('author');
    authorP.text(author);

    isbnP.addClass('isbn');
    isbnP.text(iSBN);

    selectBtn.on('click', function () {
        divBook.css('border', '2px solid blue');
    });

    deselectBtn.on('click', function () {
        divBook.css('border', '');
    });

    divBook.append(titleP)
        .append(authorP)
        .append(isbnP)
        .append(selectBtn)
        .append(deselectBtn);

    divBook.appendTo(fragment);
    container.append(fragment);
}
