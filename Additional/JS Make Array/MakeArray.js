function makeArray(count){ 
	let array = [];
	for(let i = 0; i < count; i++){
		array.push(i);
	}

	console.log(array.join(', '));
	return array;
}