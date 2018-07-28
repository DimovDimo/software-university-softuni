function xMLMessenger(input) {
    let sender = '';
    let recipient = '';
    let messege = '';
    let messagePattern = /^<message (.*)>(.*)<\/message>$/g;
    if (messagePattern.test(inputLine)){
        let empty = messagePattern.exec(String(inputLine));
        let match = messagePattern.exec(String(inputLine));
        let argumets = match[1].split(`" `).map(pair => pair.split(`="`));
        messege = match[2];
        for (let [key, value] of argumets) {
            value = value.split(`"`)[0];
            let isKey = /^[a-z]+$/.test(key);
            let isValue = /^[A-Za-z0-9 .]+$/.test(value.trim(`"`));
            if (isKey && isValue) {
                if (key === 'from'){
                    sender = value;
                } else if (key === 'to'){
                    recipient = value;
                }

            } else {
                console.log('Invalid message format');
                return;
            }
        }
    } else {
        console.log('Invalid message format');
        return;
    }

    if (sender === '' || recipient === ''){
        console.log('Missing attributes');
    } else {
        console.log('<article>');
        console.log(`  <div>From: <span class="sender">${sender}</span></div>`);
        console.log(`  <div>To: <span class="recipient">${recipient}</span></div>`);
        console.log('  <div>');
        console.log(`    <p>${messege}</p>`);
        console.log('  </div>');
        console.log('</article>');
    }
}

xMLMessenger(
    '<message to="Bob" from="Alice" timestamp="1497254114">Same old',
    'same old Let's go out for a beer</message>');

xMLMessenger('<message to="Bob" from="Alice" timestamp="1497254092">Hey man, what\'s up?</message>');