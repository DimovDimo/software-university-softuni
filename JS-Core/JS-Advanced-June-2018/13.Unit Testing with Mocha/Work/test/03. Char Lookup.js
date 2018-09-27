let expect = require('chai').expect;

function lookupChar(string, index) {
    if (typeof(string) !== 'string' || !Number.isInteger(index)) {
        return undefined;
    }
    if (string.length <= index || index < 0) {
        return "Incorrect index";
    }

    return string.charAt(index);
}

describe('lookupChar', function () {
    it('with a non-string first parameter, should return undefined', function () {
        expect(lookupChar(13, 0)
            .to.equal(undefined,
                'Function did not return corect result!'))
    });

    it('with a non-number second parameter, should return undefined', function () {
        expect(lookupChar('a', 'b')
            .to.equal(undefined,
                'Function did not return corect result!'))
    });

    it('with a floating point number second parameter, should return undefined', function () {
        expect(lookupChar('a', 3.14)
            .to.equal(undefined,
                'Function did not return corect result!'))
    });

    it('with a incorect index value, should return Incorrect index', function () {
        expect(lookupChar('a', 322)
            .to.equal('Incorrect index',
                'Function did not return corect result!'))
    });

    it('with a negative index value, should return Incorrect index', function () {
        expect(lookupChar('a', -322)
            .to.equal('Incorrect index',
                'Function did not return corect result!'))
    });

    it('with an index value equal to string lenght, should return Incorrect index', function () {
        expect(lookupChar('abc', 3)
            .to.equal('Incorrect index',
                'Function did not return corect result!'))
    });

    it('with correct parameters, should return correct value', function () {
        expect(lookupChar('abc', 0)
            .to.equal('a',
                'Function did not return corect result!'))
    });

    it('with correct parameters, should return correct value', function () {
        expect(lookupChar('abc', 1)
            .to.equal('b',
                'Function did not return corect result!'))
    });
});