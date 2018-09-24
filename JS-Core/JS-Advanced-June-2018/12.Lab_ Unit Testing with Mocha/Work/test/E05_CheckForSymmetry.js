const expect = require("chai").expect;

function isSymmetric(arr) {
    if (!Array.isArray(arr))
        return false; // Non-arrays are non-symmetric
    let reversed = arr.slice(0).reverse(); // Clone and reverse
    let equal = (JSON.stringify(arr) == JSON.stringify(reversed));
    return equal;
}

describe("isSymmetric function tests", function () {
    it("Should return true for [1,2,1]", function () {
        // Arrange
        let array = [1, 2, 1];
        // Act
        let result = isSymmetric(array);
        // Assert
        expect(result).to.be.equal(true);
    });
    it("Should return true for ['a','b','a']", function () {
        // Arrange
        let array = ['a','b','a'];
        // Act
        let result = isSymmetric(array);
        // Assert
        expect(result).to.be.equal(true);
    });
    it("Should return true for [1,'a','b','a',1]", function () {
        // Arrange
        let array = [1,'a','b','a',1];
        // Act
        let result = isSymmetric(array);
        // Assert
        expect(result).to.be.equal(true);
    });
    it("Should return true for [NaN]", function () {
        // Arrange
        let array = [NaN];
        // Act
        let result = isSymmetric(array);
        // Assert
        expect(result).to.be.equal(true);
    });
    it("Should return true for [1.5,1.5]", function () {
        // Arrange
        let array = [1.5,1.5];
        // Act
        let result = isSymmetric(array);
        // Assert
        expect(result).to.be.equal(true);
    });
    it("Should return false for [1.5,2.5]", function () {
        // Arrange
        let array = [1.5,2.5];
        // Act
        let result = isSymmetric(array);
        // Assert
        expect(result).to.be.equal(false);
    });
    it("Should return false for {}", function () {
        // Arrange
        let array = {};
        // Act
        let result = isSymmetric(array);
        // Assert
        expect(result).to.be.equal(false);
    });
    it("Should return false for 5", function () {
        // Arrange
        let array = 5;
        // Act
        let result = isSymmetric(array);
        // Assert
        expect(result).to.be.equal(false);
    });
});