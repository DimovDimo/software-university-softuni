function censorship(text, targets) {
    for (let i = 0; i < targets.length; i++) {
        let target = `${targets[i]}`;
        let replaceBlacked = '-'.repeat(targets[i].length);
        while (text.indexOf(target) > -1) {
            text = text.replace(target, replaceBlacked);
        }
    }

    console.log(text);
}

censorship('roses are red, violets are blue', [', violets are', 'red']);