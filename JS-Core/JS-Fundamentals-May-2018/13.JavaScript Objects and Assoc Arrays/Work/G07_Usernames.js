function usernames(input) {
    let usernamesSet = new Set();
    for (const inputElement of input) {
        usernamesSet.add(inputElement)
    }
    // input.forEach(username => usernames.add(username));
    let usernames = Array.from(usernamesSet).sort((name1, name2) => {
        let lenghtDiff = name1.length - name2.length;
        if (lenghtDiff === 0){
            return name1.localeCompare(name2);
        } else {
            return lenghtDiff;
        }
    });
    console.log(usernames.join('\n'));
}

