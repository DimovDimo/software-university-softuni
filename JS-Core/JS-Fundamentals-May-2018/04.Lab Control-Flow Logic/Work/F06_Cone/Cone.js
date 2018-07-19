function cone(radius, height) {
    let volume = (1/3) * Math.PI * radius * radius * height;
    let slantHeight = Math.sqrt(radius ** 2 + height ** 2);
    let totalArea = Math.PI * radius * slantHeight + Math.PI * radius ** 2;
    console.log(`volume = ${volume}`);
    console.log(`area = ${totalArea}`);
}