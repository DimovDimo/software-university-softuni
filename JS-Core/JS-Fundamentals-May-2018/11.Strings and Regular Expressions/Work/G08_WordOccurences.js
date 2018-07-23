function wordOccurences(text, tearget) {
    text = text.toLowerCase();
    tearget = tearget.toLowerCase();
    let targetPattern = new RegExp(`\\b${tearget}\\b`, 'g');
    let matches = text.match(targetPattern);
    if (matches){
        return matches.length;
    } else {
        return 0;
    }
}

wordOccurences('There was one. Therefore I bought it. I wouldn�t buy it otherwise.',
    'there')

wordOccurences('The waterfall was so high, that the child couldn’t see its peak.' ,
    'the');