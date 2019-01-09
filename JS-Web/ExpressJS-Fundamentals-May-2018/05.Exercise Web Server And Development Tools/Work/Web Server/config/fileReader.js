const fs = require('fs');
const path = require('path');

const placeholder = '<div id="replaceMe">{{replaceMe}}</div>';

function readFile(res, pathname, dynamicContent, contentType) {
    if (!pathname) {
        throw new ReferenceError('Argument "pathname" cannot be undefined!');
    }

    pathname = path.join(__dirname, `../${pathname}`);

    if (!contentType) {
        contentType = 'text/html';
    }

    fs.readFile(pathname, "utf-8", (err, data) => {
        if (err) {
            console.dir(err);

            res.writeHead(500, {
                'Content-Type': 'text/plain'
            });
            res.end(`Could not read file with path ${pathname}`);
            return;
        }

        if (dynamicContent) {
            data = data
                .toString()
                .replace(placeholder, dynamicContent);
        }

        res.writeHead(200, {
            'Content-Type': contentType
        });
        res.write(data);
        res.end();
    });
}

module.exports = res => {
    res.view = (path, dynamicContent) => {
        readFile(res, path, dynamicContent, undefined);
    }
    res.staticFile = (path, contentType) => {
        readFile(res, path, undefined, contentType);
    }
}