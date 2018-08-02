function extract(elementId) {
    let para = document.getElementById(elementId).textContent;
    let pattern = /\(([^)]+)\)/g;
    let result = [];

    let match = pattern.exec(para);
    while(match) {
        result.push(match[1]);
        match = pattern.exec(para);
    }

    return result.join('; ');
}

function allExtract() {
    let body = document.getElementById('body');
    let content = extract('content');
    let holder = extract('holder');

    body.innerHTML(content + '\n' + holder);
}
