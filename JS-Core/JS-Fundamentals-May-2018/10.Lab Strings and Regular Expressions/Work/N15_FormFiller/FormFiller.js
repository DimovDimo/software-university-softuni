function formFiller(username, email, phoneNumber, text) {
    let regexUsername = /<![A-Za-z]+!>/;
    let regexEmail = /<@[A-Za-z]+@>/;
    let regexPhoneNumber = /<\+[A-Za-z]+\+>/;

    function replaceData(username, email, phoneNumber, text) {
        return text.replace(regexUsername, username)
            .replace(regexEmail, email)
            .replace(regexPhoneNumber, phoneNumber);
    }

    let output = [];
    for (let row of text) {
        output.push(replaceData(username, email, phoneNumber, row));
    }

    console.log(output.join('\n'));
}