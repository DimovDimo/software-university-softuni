function orderRectangles(matrix) {
    for (let i = 0; i < matrix.length; i++) {
        matrix[i] = {
            width: matrix[i][0],
            height: matrix[i][0],
            area: function () {
                return this.width * this.height;
            },
            compareTo(other) {
                let comparingByArea = other.area() - this.area();
                if (comparingByArea !== 0) {
                    return comparingByArea;
                }

                return other.width - this.width;
            }
        }
    }

    return matrix.sort((a, b) => a.compareTo(b));
}