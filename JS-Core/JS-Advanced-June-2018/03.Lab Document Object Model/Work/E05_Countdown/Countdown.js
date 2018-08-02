function countdown(time) {
    let box = document.getElementById('time');
    let interval = setInterval(decrement, 1000);

    function decrement() {
        time--;
        box.value = Math.trunc(time/60) +
            ':' + ('0' + (time % 60)).slice(-2);
    }
}