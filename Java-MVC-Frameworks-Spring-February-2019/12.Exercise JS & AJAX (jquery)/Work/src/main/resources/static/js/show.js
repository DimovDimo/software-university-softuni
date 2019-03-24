function allViruses() {
    fetch('/viruses/all-viruses')
        .then((response) => response.json())
        .then((json) => {
            $('#choice').text('All Viruses');
            $('.container #content').remove();
            $('.container').append('<div id ="content" class="mt-4"></div>');

            let table =
                 `<table class="table mt-4" >
                  <thead>
                  <tr>
                      <th scope="col">#</th>
                      <th scope="col">Name</th>
                      <th scope="col">Magnitude</th>
                      <th scope="col">Released On</th>
                      <th scope="col"></th>
                      <th scope="col"></th>
                  </tr>
                  </thead>
                  <tbody>`;

            json.forEach((virus, index) => {
                table +=
                   `<tr>
                     <th scope="row">${index + 1}</th> 
                     <td>${virus.name}</td>
                     <td>${virus.magnitude}</td>
                     <td>${virus.releasedOn}</td>
                     <td>
                     <a class="btn re-background" href="/viruses/edit/${virus.id}">Edit</a>
                     </td>
                     <td>
                     <a class="btn re-background" href="/viruses/delete/${virus.id}" >Delete</a>
                     </td>
                   </tr>`;
            });

            table += `<tbody></table>`;
            $('#content').append(table);
        });
}

function allCapitals() {
    fetch('/viruses/all-capitals')
        .then((response) => response.json())
        .then((json) => {
            $('#choice').text('All Capitals');
            $('.container #content').remove();
            $('.container').append('<div id="content" class="mt-4"></div>');

            let table =
                 `<table class="table mt-4" >
                  <thead>
                  <tr>
                      <th scope="col">#</th>
                      <th scope="col">Name</th>
                      <th scope="col">Latitude</th>
                      <th scope="col">Longitude</th>
                  </tr>
                  </thead>
                  <tbody>`;

            json.forEach((capital, index) => {
                console.log(capital);
                table +=
                   `<tr>
                   <th scope="row">${index + 1}</th>
                   <td>${capital.name}</td>
                   <td>${capital.latitude}</td>
                   <td>${capital.longitude}</td>
                   </tr>`;
            });

            table += '<tbody></table>';
            $('#content').append(table);
        });
}



