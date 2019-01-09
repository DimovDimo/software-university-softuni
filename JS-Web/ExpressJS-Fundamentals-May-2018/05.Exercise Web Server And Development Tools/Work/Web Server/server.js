"use strict";

const http = require('http');
const url = require('url');

const attachFileReader = require('./config/fileReader');
const postParserMiddleware = require('./config/postParser');
const handlers = require('./handlers');
const port = 9000;

function framework(req, res) {
    req.urlData = url.parse(req.url);

    attachFileReader(res);

    postParserMiddleware(req, res)
        .then(postData => {
            for (let handler of handlers) {
                if (handler(req, res) != true) {
                    break;
                }
            }
        })
}

let server = http.createServer(framework);
server.listen(port);

console.log(`Server listening on port: ${port}`);