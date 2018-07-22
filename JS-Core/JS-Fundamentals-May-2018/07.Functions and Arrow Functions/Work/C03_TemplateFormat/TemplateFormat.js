function templateFormat(data) {
    console.log('<?xml version="1.0" encoding="UTF-8"?>');
    console.log('<quiz>');
    for (let i = 0; i < data.length; i+=2) {
        let question = data[i];
        let answer = data[i+1];
        console.log('  <question>');
        console.log(`    ${question}`);
        console.log('  </question>');
        console.log('  <answer>');
        console.log(`    ${answer}`);
        console.log('  </answer>');
    }

    console.log('</quiz>');
}

templateFormat(["Dry ice is a frozen form of which gas?",
    "Carbon Dioxide",
    "What is the brightest star in the night sky?",
    "Sirius"]
);