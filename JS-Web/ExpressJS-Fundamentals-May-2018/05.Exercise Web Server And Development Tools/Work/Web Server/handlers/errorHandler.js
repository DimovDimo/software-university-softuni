module.exports = (req, res) => {
    res.writeHead(404, {
        'Content-Type': 'text/plain'
    });
    res.end('Bad Request');
}