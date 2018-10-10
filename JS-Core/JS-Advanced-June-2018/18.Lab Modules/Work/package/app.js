// ---------------------------
// Player

    // private members
    let playerName = '';

    function logPlayer() {
        console.log(`The current player is ${playerName}.`);
    }

    function setName(newName) {
        playerName = newName;
    }

    function getName() {
        return playerName;
    }


// ---------------------------
// Game

    // private members
    let factorElement = document.getElementById('factor');
    let problemsPerGame = 3; // set default value

    function printGame() {

        logPlayer(); // player.

        // determine the number of problems to show
        setProblemCount(document.getElementById('problemCount').value);

        // create the html for the current game
        let gameForm = '';
        for (let i = 1; i <= problemsPerGame; i++) {
            gameForm += `<div class="form-group">`;
            gameForm += `<label for="answer${i}" class="col-sm-2 control-label">`;
            gameForm += `${factorElement.value} x ${i} = </label>`;
            gameForm += `<div class="col-sm-1"><input type="text" class="form-control" id="answer${i}">`;
            gameForm += `</div>`;
        }

        // add the new game to the page
        let gameElement = document.getElementById('game');
        gameElement.innerHTML = gameForm;

        // enable the calculate score button
        document.getElementById('calculate').removeAttribute('disabled');
    }

    function calculateScore() {

        let problemsInGame = getProblemCount();
        let score = 0;

        // loop trough the text boxes and calculate the number that are correct
        for (let i = 1; i <= problemsInGame; i++) {
            let answer = document.getElementById(`answer${i}`).value;
            if(i * factorElement.value == answer) {
                score++;
            }
        }

        //create a new result object to pass to the scoreboard
        let result = {
            name: getName(), // player.
            score: score,
            problems: problemsInGame,
            factor: factorElement.value
        };

        // add the result and update the scoreboard
        addResult(result); //scoreboard.
        updateScoreboard(); //scoreboard.

        // disable calculate score button
        document.getElementById('calculate').setAttribute('disabled', 'true');
    }

    function setProblemCount(newProblemCount) {
        problemsPerGame = newProblemCount;
    }

    function getProblemCount() {
        return problemsPerGame;
    }


// ---------------------------
// Scoreboard


    // private members
    let results = [];

    function addResult(newResult) {
        results.push(newResult);
    }

    function updateScoreboard() {

        let output = `<h2>Scoreboard</h2>`;

        // loop over all results and create the html for the scoreboard
        for (let result of results) {
            output += `<h4>${result.name}: ${result.score}/${result.problems} for factor ${result.factor}</h4>`
        }

        // add the updated scoreboard to the page
        let scoresElement = document.getElementById('scores');
        scoresElement.innerHTML = output;
    }


// ---------------------------
// Initialize

// add click handler to the start game button
document.getElementById('startGame').addEventListener('click', function () {
    setName(document.getElementById('playername').value); // player.
    printGame(); // game.
});

// add click handler to the calculate score button
document.getElementById('calculate').addEventListener('click', function () {
    calculateScore(); // game.
});

// set the default number of problems
document.getElementById('problemCount').value = getProblemCount(); // game.