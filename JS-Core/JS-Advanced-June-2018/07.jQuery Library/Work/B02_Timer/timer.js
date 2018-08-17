function timer() {
    let interval = null;
    let totalSeconds = 0;
    let seconds = $('#seconds');
    let minutes = $('#minutes');
    let hours = $('#hours');

    function step() {
        totalSeconds++;

        hours.text(('0' + Math.floor(totalSeconds/60/60)).slice(-2));
        minutes.text(('0' + Math.floor(totalSeconds/60)%60).slice(-2));
        seconds.text(('0' + totalSeconds%60).slice(-2));
    }

    function startTimer() {
        if (interval === null){
            interval = setInterval(step, 1000);
            console.log('startTimer');
        }
    }

    function stopTimer() {
        clearInterval(interval);
        interval = null;
        console.log(interval);
    }

    $('#start-timer').on('click', startTimer);


    $('#stop-timer').on('click', stopTimer);
}
