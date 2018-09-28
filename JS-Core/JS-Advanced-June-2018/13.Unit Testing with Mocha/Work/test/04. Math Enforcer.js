let expect = require('chai').expect;

let mathEnforcer = {
    addFive: function (num) {
        if (typeof(num) !== 'number') {
            return undefined;
        }
        return num + 5;
    },
    subtractTen: function (num) {
        if (typeof(num) !== 'number') {
            return undefined;
        }
        return num - 10;
    },
    sum: function (num1, num2) {
        if (typeof(num1) !== 'number' || typeof(num2) !== 'number') {
            return undefined;
        }
        return num1 + num2;
    }
};

describe('mathEnforcerUnitTesting', function () {
    describe('addFive', function () {
        it('whit a non-number parameter, shold return correct result', function () {
            expect(mathEnforcer.addFive('A')
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });

        it('whit a non-number parameter, shold return correct result', function () {
            expect(mathEnforcer.addFive([])
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });

        it('whit a number parameter, shold return correct result', function () {
            expect(mathEnforcer.addFive(1)
                .to.equal(6,
                    'Function did not return corect result!'))
        });

        it('whit a number parameter, shold return correct result', function () {
            expect(mathEnforcer.addFive(-1)
                .to.equal(4,
                    'Function did not return corect result!'))
        });

        it('whit a number parameter, shold return correct result', function () {
            expect(mathEnforcer.addFive(0.5)
                .to.equal(5.5,
                    'Function did not return corect result!'))
        });
    });

    describe('subtractTen', function () {
        it('whit a non-number parameter, shold return correct result', function () {
            expect(mathEnforcer.subtractTen('A')
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });

        it('whit a non-number parameter, shold return correct result', function () {
            expect(mathEnforcer.subtractTen([])
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });

        it('whit a number parameter, shold return correct result', function () {
            expect(mathEnforcer.subtractTen(10)
                .to.equal(0,
                    'Function did not return corect result!'))
        });

        it('whit a number parameter, shold return correct result', function () {
            expect(mathEnforcer.subtractTen(-100)
                .to.equal(-110,
                    'Function did not return corect result!'))
        });

        it('whit a number parameter, shold return correct result', function () {
            expect(mathEnforcer.subtractTen(-1.9)
                .to.equal(-11.9,
                    'Function did not return corect result!'))
        });
    });

    describe('sum', function () {
        it('whit a non-number parameter, shold return correct result', function () {
            expect(mathEnforcer.sum({}, 'A')
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });

        it('whit a non-number parameter, shold return correct result', function () {
            expect(mathEnforcer.sum([], 5)
                .to.equal(undefined,
                    'Function did not return corect result!'))
        });

        it('whit a number parameter, shold return correct result', function () {
            expect(mathEnforcer.sum(10, 5)
                .to.equal(15,
                    'Function did not return corect result!'))
        });

        it('whit a number parameter, shold return correct result', function () {
            expect(mathEnforcer.sum(10, -0.5)
                .to.equal(9.5,
                    'Function did not return corect result!'))
        });
    });
});