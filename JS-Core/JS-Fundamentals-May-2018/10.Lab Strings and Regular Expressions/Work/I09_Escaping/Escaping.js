function escaping(elements) {
    String.prototype.htmlEscape = function() {
        return this.replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;');
    };

    console.log('<ul>');
    for (let element of elements) {
        let escaped = element.htmlEscape();
        console.log(`  <li>${escaped}</li>`);
    }
    console.log('</ul>');
}