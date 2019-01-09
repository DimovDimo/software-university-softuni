const qs = require('querystring');

module.exports = (req, res) => {
    if(req.method !== 'POST'){
        return Promise.resolve();
    }

    return new Promise(resolve => {
        let body = [];

        req.on('data', (chunk) => {
            body.push(chunk);
        }).on('end', () => {
            body = Buffer.concat(body).toString();

            req.bodyData = qs.parse(body);
            resolve();
        });
    });
}