function triangleArea(a, b, c) {
    let semiperimeter = (a + b + c) / 2;
    return Math.sqrt(semiperimeter *
        (semiperimeter - a) *
        (semiperimeter - b) *
        (semiperimeter - c));
}