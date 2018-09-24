const expect = require("chai").expect;

function rgbToHexColor(red, green, blue) {
    if (!Number.isInteger(red) || (red < 0) || (red > 255))
        return undefined; // Red value is invalid
    if (!Number.isInteger(green) || (green < 0) || (green > 255))
        return undefined; // Green value is invalid
    if (!Number.isInteger(blue) || (blue < 0) || (blue > 255))
        return undefined; // Blue value is invalid
    return "#" +
        ("0" + red.toString(16).toUpperCase()).slice(-2) +
        ("0" + green.toString(16).toUpperCase()).slice(-2) +
        ("0" + blue.toString(16).toUpperCase()).slice(-2);
}

describe("rgbToHexColor function tests", function () {
    describe("falid function tests", function () {
        it("Should return #D65E5E for 214,94,94", function () {
            // Arrange
            // Act
            let result = rgbToHexColor(214,94,94);
            // Assert
            expect(result).to.be.equal('#D65E5E');
        });
    });
    describe("return undefined function tests", function () {
        it("Should return undefined for -1,11,255555", function () {
            // Arrange
            // Act
            let result = rgbToHexColor(-1,11,255555);
            // Assert
            expect(result).to.be.undefined;
        });
        it("Should return undefined for 0,5.5,7", function () {
            // Arrange
            // Act
            let result = rgbToHexColor(0,5.5,7);
            // Assert
            expect(result).to.be.undefined;
        });
        it("Should return undefined for 1,2", function () {
            // Arrange
            // Act
            let result = rgbToHexColor(1,2);
            // Assert
            expect(result).to.be.undefined;
        });
        it("Should return undefined for 1", function () {
            // Arrange
            // Act
            let result = rgbToHexColor(1);
            // Assert
            expect(result).to.be.undefined;
        });
        it("Should return undefined for empty input", function () {
            // Arrange
            // Act
            let result = rgbToHexColor();
            // Assert
            expect(result).to.be.undefined;
        });
        it("Should return undefined for NaN,84,24", function () {
            // Arrange
            // Act
            let result = rgbToHexColor(NaN,84,24);
            // Assert
            expect(result).to.be.undefined;
        });
        it("Should return undefined for NaN,84,24", function () {
            // Arrange
            // Act
            let result = rgbToHexColor(NaN,84,24);
            // Assert
            expect(result).to.be.undefined;
        });it("Should return undefined for [],{}", function () {
            // Arrange
            // Act
            let result = rgbToHexColor([],{});
            // Assert
            expect(result).to.be.undefined;
        });
    });
});