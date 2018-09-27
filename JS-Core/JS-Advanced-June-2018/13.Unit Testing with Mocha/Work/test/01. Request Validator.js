function validateRequest(request) {

    if (!request.hasOwnProperty('method')) {
        throw new Error('Invalid request header: Invalid Method');
    }

    let validMethods = ['GET', 'POST', 'DELETE', 'CONNECT'];
    if (validMethods.indexOf(request['method']) < 0){
        throw new Error('Invalid request header: Invalid Method');
    }

    if (!request.hasOwnProperty('uri')) {
        throw new Error('Invalid request header: Invalid URI');
    }

    let patternURI = /^(\*|[A-Za-z0-9.]+)$/gm;
    if (!request['uri'].match(patternURI)) {
        throw new Error('Invalid request header: Invalid URI');
    }

    if (!request.hasOwnProperty('version')) {
        throw new Error('Invalid request header: Invalid Version');
    }

    let validVersion = ['HTTP/0.9', 'HTTP/1.0', 'HTTP/1.1', 'HTTP/2.0'];
    if (validVersion.indexOf(request['version']) < 0){
        throw new Error('Invalid request header: Invalid Version');
    }

    if (!request.hasOwnProperty('message')) {
        throw new Error('Invalid request header: Invalid Message');
    }

    let patternMessage = /^([^<>\\&'"]*)$/gm;
    if (!request['message'].match(patternMessage)) {
        throw new Error('Invalid request header: Invalid Message');
    }

    return request;
}

validateRequest({
    method: 'GET',
    uri: 'svn.public.catalog',
    version: 'HTTP/1.1',
    message: ''
});

// validateRequest({
//     method: 'OPTIONS',
//     uri: 'git.master',
//     version: 'HTTP/1.1',
//     message: '-recursive'
// });

validateRequest({
    method: 'POST',
    uri: 'home.bash',
    message: 'rm -rf /*'
});