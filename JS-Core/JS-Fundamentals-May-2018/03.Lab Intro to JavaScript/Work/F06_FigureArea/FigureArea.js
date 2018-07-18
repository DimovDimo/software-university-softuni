function FigureArea(w, h, W, H) {
    let result = H*W + w*h - Math.min(w, W) * Math.min(h, H);
    console.log(result);
}