function galacticElections(input) {
    let systems = {};
    for (let system of input) {
        let name = system['system'];
        let candidate = system['candidate'];
        let votes = system['votes'];
        if (!systems.hasOwnProperty(name)) {
            systems[name] = {};
            systems[name][candidate] = votes;
        } else {
            if (systems[name].hasOwnProperty(candidate)) {
                systems[name][candidate] = systems[name][candidate] + votes;
            } else {
                systems[name][candidate] = votes;
            }
        }
    }

    let sytemsNames = Object.keys(systems);
    let sytemsFavoritCandidates = {};
    for (let name of sytemsNames) {
        let candidates = Object.keys(systems[name]);
        let bestCandidate = '';
        let bestVotes = 0;
        for (let candidate of candidates) {
            if (bestVotes < systems[name][candidate]){
                bestVotes = systems[name][candidate];
                bestCandidate = candidate;
            }
        }

        let allVotes = 0;
        for (let candidate of candidates) {
            allVotes += systems[name][candidate];
        }

        sytemsFavoritCandidates[name] = {};
        sytemsFavoritCandidates[name][bestCandidate] = allVotes;
    }

    let totalCandidateVotes = {};
    let nameOfSytemsFavoritCandidates = Object.keys(sytemsFavoritCandidates);
    for (let nameOfSytemsFavoritCandidate of nameOfSytemsFavoritCandidates) {
        let candidate = Object.keys(sytemsFavoritCandidates[nameOfSytemsFavoritCandidate]);
        if (!totalCandidateVotes.hasOwnProperty(candidate)){
            totalCandidateVotes[candidate] = {};
            totalCandidateVotes[candidate]['votes'] = sytemsFavoritCandidates[nameOfSytemsFavoritCandidate][candidate];
            totalCandidateVotes[candidate]['system'] = [];
        } else {
            totalCandidateVotes[candidate]['votes'] = totalCandidateVotes[candidate]['votes'] + sytemsFavoritCandidates[nameOfSytemsFavoritCandidate][candidate];
        }

        totalCandidateVotes[candidate]['system'].push(nameOfSytemsFavoritCandidate);
    }

    let namesCandidates = Object.keys(totalCandidateVotes).sort((c1, c2) => {
        return totalCandidateVotes[c2]['votes'] - totalCandidateVotes[c1]['votes'];
    });
    let allVotes = 0;
    for (let namesCandidate of namesCandidates) {
        allVotes += totalCandidateVotes[namesCandidate]['votes'];
    }

    if (namesCandidates.length === 1){
        console.log(`${namesCandidates[0]} wins with ${allVotes} votes`);
        console.log(`${namesCandidates[0]} wins unopposed!`);
    } else {
        let winner = namesCandidates[0];
        let winnerVotes = totalCandidateVotes[winner]['votes'];
        let runner = namesCandidates[1];
        if (allVotes / 2.0 < winnerVotes){
            console.log(`${winner} wins with ${winnerVotes} votes`);
            console.log(`Runner up: ${runner}`);
            let runnerSystems = {};
            let namesRunnerSystems = totalCandidateVotes[runner]['system'];
            for (let nameRunnerSystem of namesRunnerSystems) {
                runnerSystems[nameRunnerSystem] = sytemsFavoritCandidates[nameRunnerSystem][runner];
            }

            let sortedRunnerSystems = Object.keys(runnerSystems).sort((s1, s2) => {
                return runnerSystems[s2] - runnerSystems[s1];
            });

            for (let sortedRunnerSystem of sortedRunnerSystems) {
                console.log(`${sortedRunnerSystem}: ${runnerSystems[sortedRunnerSystem]}`);
            }
        } else {
            let winner = namesCandidates[0];
            let winnerVotes = totalCandidateVotes[winner]['votes'];
            let runner = namesCandidates[1];
            let runnerVotes = totalCandidateVotes[runner]['votes'];
            let winnerPrecent = Math.floor((winnerVotes/allVotes) * 100);
            let runnerPrecent = Math.floor((runnerVotes/allVotes) * 100);

            console.log(`Runoff between ${winner} with ${winnerPrecent}% and ${runner} with ${runnerPrecent}%`);
        }
    }
}

galacticElections([ { system: 'Theta', candidate: 'Flying Shrimp', votes: 10 },
    { system: 'Sigma', candidate: 'Space Cow',     votes: 200 },
    { system: 'Sigma', candidate: 'Flying Shrimp', votes: 120 },
    { system: 'Tau',   candidate: 'Space Cow',     votes: 15 },
    { system: 'Sigma', candidate: 'Space Cow',     votes: 60 },
    { system: 'Tau',   candidate: 'Flying Shrimp', votes: 150 } ]
);

galacticElections([ { system: 'Tau',     candidate: 'Flying Shrimp', votes: 150 },
    { system: 'Tau',     candidate: 'Space Cow',     votes: 100 },
    { system: 'Theta',   candidate: 'Space Cow',     votes: 10 },
    { system: 'Sigma',   candidate: 'Space Cow',     votes: 200 },
    { system: 'Sigma',   candidate: 'Flying Shrimp', votes: 75 },
    { system: 'Omicron', candidate: 'Flying Shrimp', votes: 50 },
    { system: 'Omicron', candidate: 'Octocat',       votes: 75 } ]
);

galacticElections([ { system: 'Theta', candidate: 'Kim Jong Andromeda', votes: 10 },
    { system: 'Tau',   candidate: 'Kim Jong Andromeda', votes: 200 },
    { system: 'Tau',   candidate: 'Flying Shrimp',      votes: 150 } ]
);