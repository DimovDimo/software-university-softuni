function sortedList() {
    return {
        list: [],
        size: 0,
        add: function (element) {
            this.list.push(element);
            this.size++;
            this.list.sort((a, b) => a - b);
        },
        remove: function (index) {
            if (0 <= index && index < this.list.length){
                this.list.splice(index, 1);
                this.size--;
                this.size < 0 ? this.size = 0 : null ;
                this.list.sort((a, b) => a - b);
            }

        },
        get: function (index) {
            return this.list[index];
        }

    }
}