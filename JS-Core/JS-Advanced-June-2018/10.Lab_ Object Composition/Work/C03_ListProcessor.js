function listProcessor(commands) {
    let processor = (function(){
        let list = [];

        function add(item) {
            list.push(item);
        }

        function remove(item) {
            list = list.filter(e => e !== item);
        }

        function print() {
            console.log(list.join(','));
        }

        return {add, remove, print};
    }());

    for (let command of commands) {
        let tokens = command.split(' ');
        switch (tokens[0]) {
            case 'add':
                processor.add(tokens[1]);
                break;
            case 'remove':
                processor.remove(tokens[1]);
                break;
            case 'print':
                processor.print();
                break;
        }
    }
}