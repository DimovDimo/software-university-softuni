const movies = require('../config/dataBase');

const movieReplacePlaceholder = '{{replaceMe}}'

const movieTemplate = `
    <div class="movie">
        <img class="moviePoster" src=${movieReplacePlaceholder} />
    </div>`;

const errorTemplate = `
    <div id="errorBox">
        <h2 id="errMsg">Please fill all fields</h2>
    </div>`;

const successTemplate = `
    <div id="succssesBox">
        <h2 id="succssesMsg">Successfully added movie.</h2>
    </div>`;

const movieDetailsTemplate = `
    <div class="content">
        <img src="{{placeholder}}" />
    </div> 
    `;

module.exports = (req, res) => {
    let pathname = req.urlData.pathname;

    if (pathname === '/movies/all') {
        switch (req.method) {
            case 'GET':
                let moviesHtml = movies
                    .map(movie => movieTemplate.replace(movieReplacePlaceholder,
                        decodeURIComponent(movie.moviePoster)))
                    .join("");

                res.view('views/viewAll.html', moviesHtml);
        }
    } else if (pathname === '/movies/add') {
        switch (req.method) {
            case 'GET':
                res.view('views/addMovie.html');
            case 'POST':
                let movieData = req.bodyData;

                if (!movieData.moviePoster || !movieData.movieTitle) {
                    res.view('views/addMovie.html', errorTemplate);
                    return;
                }

                movies.push(movieData);
                res.view('views/addMovie.html', successTemplate);
        }
    }
    else if (pathname.startsWith('/movies/details/')) {
        switch (req.method) {
            case 'GET':
                let index = pathname.substr(pathname.lastIndexOf('/') + 1);
                movie = movies[index];

                let movieHtml = movieDetailsTemplate.replace('{{placeholder}}',
                    decodeURIComponent(movie.moviePoster));

                res.view('views/details.html', movieHtml);
        }
    }
    else {
        return true;
    }
}