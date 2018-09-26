let expect = require('chai').expect;


function isOddOrEven(string) {
    if (typeof(string) !== 'string') {
        return undefined;
    }
    if (string.length % 2 === 0) {
        return "even";
    }

    return "odd";
}

describe('isOddOrEven', function () {
    // describe('even', function () {
        it('with a string abcd, should return even', function () {
            expect(isOddOrEven('abcd')
                .to.equal('even',
                    'Function did not return corect result!'))
        });

        it('with an empty string, should return even', function () {
            expect(isOddOrEven('')
                .to.equal('even',
                    'Function did not return corect result!'))
        });
    // });

    // describe('odd', function () {
        it('with a string abc, should return odd', function () {
            expect(isOddOrEven('abc')
                .to.equal('odd',
                    'Function did not return corect result!'))
        });

        it('with a string A, should return odd', function () {
            expect(isOddOrEven('A')
                .to.equal('odd',
                    'Function did not return corect result!'))
        });
    // });

    // describe('undefined', function () {
        it('with a number parameter, should return undefined', function () {
            expect(isOddOrEven(1)
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });

        it('with an object parameter, should return undefined', function () {
            expect(isOddOrEven({})
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });

        it('with an array parameter, should return undefined', function () {
            expect(isOddOrEven([])
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });

        it('with a date parameter, should return undefined', function () {
            expect(isOddOrEven(Date.now())
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });
    // });
});