function usernames(emails) {
    let usernamesAndDomeins = emails.map(e => e.split('@'))
    let output = [];
    usernamesAndDomeins.forEach(splitedEmail => {
        let useraname = splitedEmail[0];
        let domainElements = splitedEmail[1].split('.')
            .map(element => element[0]).join('');
        output.push(useraname + '.' + domainElements);
    });

    console.log(output.join(', '));
}

usernames(['peshoo@gmail.com', 'todor_43@mail.dir.bg', 'foo@bar.com']);